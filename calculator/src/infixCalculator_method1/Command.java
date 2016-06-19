package infixCalculator_method1;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author doyenm
 */
public interface Command {

    public boolean canExecute(String str);

    public void execute(String str);
}

class Number implements Command {

    Stack stack;
    Queue queue;
    HashMap<String, Double> environment;

    public Number(Stack stack, Queue queue,
            HashMap<String, Double> environment) {
        this.stack = stack;
        this.queue = queue;
        this.environment = environment;
    }

    @Override
    public boolean canExecute(String str) {
        Double.parseDouble(str);
        return true;
    }

    @Override
    public void execute(String str) {
        this.queue.add(Double.parseDouble(str));
    }

}

class Variable implements Command {

    Stack stack;
    Queue queue;
    HashMap<String, Double> environment;

    public Variable(Stack stack, Queue queue,
            HashMap<String, Double> environment) {
        this.stack = stack;
        this.queue = queue;
        this.environment = environment;
    }

    @Override
    public boolean canExecute(String str) {
        return str.matches("[a-zA-Z]+");
    }

    @Override
    public void execute(String str) {
        if (this.environment.containsKey(str)) {
            for (Map.Entry<String, Double> e : this.environment.entrySet()) {
                if (e.getKey().equals(str)) {
                    this.queue.add(e.getValue());
                }
            }
        }
    }

}

class Operator implements Command {

    Stack stack;
    Queue queue;
    int priority;
    /**
     * True if right, false if left
     */
    boolean associativity;

    HashMap<String, Double> environment;

    public Operator(Stack stack, Queue queue,
            HashMap<String, Double> environment) {
        this.stack = stack;
        this.queue = queue;
        this.environment = environment;
    }

    @Override
    public boolean canExecute(String str) {
        if (str.equals("*") || str.equals("/")
                || str.equals("-") || str.equals("+")
                || str.equals("=")) {
            this.priority = determinePriority(str);
            this.associativity = determineAssociativity(str);
            return true;
        }
        return false;
    }

    @Override
    public void execute(String str) {
        String head;
        boolean bool = true;
        while (bool && !this.stack.empty()) {
            head = (String) this.stack.peek();
            if (head.equals("(")) {
                bool = false;
            } else {
                int priorityO2 = determinePriority(head);
                boolean associativityO2 = determineAssociativity(head);
                if (this.priority <= priorityO2) {
                    this.queue.add(this.stack.pop());
                }
            }
        }
        this.stack.add(str);
    }

    public int determinePriority(String op) {
        if (op.equals("+") || op.equals("-")) {
            return 2;
        } else {
            return 3;
        }
    }

    public boolean determineAssociativity(String op) {
        if (op.equals("=")) {
            return true;
        } else {
            return false;
        }
    }
}

class OpeningParenthesis implements Command {

    Stack stack;
    Queue queue;
    HashMap<String, Double> environment;

    public OpeningParenthesis(Stack stack, Queue queue,
            HashMap<String, Double> environment) {
        this.stack = stack;
        this.queue = queue;
        this.environment = environment;
    }

    @Override
    public boolean canExecute(String str) {
        if (str.equals("(")) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String str) {
        this.stack.push(str);
    }

}

class ClosingParenthesis implements Command {

    Stack stack;
    Queue queue;
    HashMap<String, Double> environment;

    public ClosingParenthesis(Stack stack, Queue queue,
            HashMap<String, Double> environment) {
        this.stack = stack;
        this.queue = queue;
        this.environment = environment;
    }

    @Override
    public boolean canExecute(String str) {
        if (str.equals(")")) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String str) {
        String head;
        boolean bool = true;
        while (bool && !this.stack.empty()) {
            head = (String) this.stack.pop();
            if (head.equals("(")) {
                bool = false;
            } else {
                this.queue.add(head);
            }
        }
    }

}
