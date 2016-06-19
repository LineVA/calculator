package infixCalculator_method1;

import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import polnishCalculator.PolnishStack;

/**
 *
 * @author doyenm
 */
public class Transform {

    Stack<String> stack = new Stack();
    Queue<String> queue = new LinkedList();
    HashMap<String, Double> environment = new HashMap<>();

    List<Command> commands = asList(new Operator(stack, queue, environment),
            new OpeningParenthesis(stack, queue, environment),
            new ClosingParenthesis(stack, queue, environment),
            new Variable(stack, queue, environment),
            new Number(stack, queue, environment));

    public boolean hasAssign(String[] calculation) {
        if (calculation.length >= 2) {
            return calculation[0].matches("[a-zA-Z]+") && calculation[1].equals("=");
        } else {
            return false;
        }
    }

    public void compute(String[] calculation) throws Exception {
        int i = 0;

        while (i < calculation.length) {
            for (Command command : commands) {
                if (command.canExecute(calculation[i])) {
                    command.execute(calculation[i]);
                    break;
                }
            }
            i++;
        }
        this.finish();
    }

    public void finish() throws Exception {
        String str;
        Command op = new Operator(this.stack, this.queue, this.environment);
        while (!this.stack.empty()) {
            str = this.stack.pop();
            if (op.canExecute(str)) {
                this.queue.add(str);
            } else {
                throw new Exception("Format incorrect");
            }
        }
    }

    public double result() throws Exception {
        String finalStack = "";
        boolean bool = true;
        String head;
        while (bool) {
            head = String.valueOf(this.queue.peek());
            if (!head.equals("null")) {
                this.queue.poll();
                finalStack += head + " ";
            } else {
                bool = false;
            }
        }
        PolnishStack polnish = new PolnishStack();
        return polnish.run(finalStack);
    }

    public String run(String cmd) throws Exception {
        String[] calculation = cmd.split(" ");
        boolean assign = false;
        assign = this.hasAssign(calculation);
        if (assign) {
            this.compute(Arrays.copyOfRange(calculation, 2, calculation.length));
            double result = this.result();
            this.environment.put(calculation[0], result);
            return calculation[0] + " <- " + result;
        } else {
            this.compute(calculation);
            return "" + this.result();
        }
    }

}
