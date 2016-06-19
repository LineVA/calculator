package polnishCalculator;

import java.util.Stack;

/**
 *
 * @author doyenm
 */
public interface Command {
    
    public boolean canExecute(String str);
    
    public void execute(String str);
}

class Number implements Command{
    
    Stack stack;
    
    public Number(Stack stack){
        this.stack = stack;
    }
    
    @Override
    public boolean canExecute(String str) {
            Double.parseDouble(str);
            return true;
    }

    @Override
    public void execute(String str) {
        this.stack.push(Double.parseDouble(str));
    }
    
}

class Addition implements Command{
    
    Stack stack;
    
    public  Addition(Stack stack){
        this.stack = stack;
    }

    @Override
    public boolean canExecute(String str) {
        if(str.equals("+")){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String str) {
       double right = (Double)this.stack.pop();
       double left = (Double)this.stack.pop();
       this.stack.push(left + right);
    }
    
}

class Substraction implements Command{
    Stack stack;
    
    public Substraction(Stack stack){
        this.stack = stack;
    }

       @Override
    public boolean canExecute(String str) {
        if(str.equals("-")){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String str) {
       double right = (Double)this.stack.pop();
       double left = (Double)this.stack.pop();
       this.stack.push(left - right);
    }
    
}

class Division implements Command{
    Stack stack;
    
    public Division(Stack stack){
        this.stack = stack;
    }

       @Override
    public boolean canExecute(String str) {
        if(str.equals("/")){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String str) {
       double right = (Double)this.stack.pop();
       double left = (Double)this.stack.pop();
       this.stack.push(left / right);
    }
    
}

class Multiplication implements Command{
    Stack stack;
    
    public Multiplication(Stack stack){
        this.stack = stack;
    }

       @Override
    public boolean canExecute(String str) {
        if(str.equals("*")){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String str) {
       double right = (Double)this.stack.pop();
       double left = (Double)this.stack.pop();
       this.stack.push(left * right);
    }
}