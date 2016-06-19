package polnishCalculator;

import static java.util.Arrays.asList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author doyenm
 */
public class PolnishStack {

    Stack<Double> stack = new Stack();

    List<Command> commands = asList(new Addition(stack), new Substraction(stack),
            new Division(stack), new Multiplication(stack), new Number(stack));

    public void compute(String[] calculation) {
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
    }

    public double result() throws Exception {
        double result = this.stack.pop();
        if (stack.empty()) {
            return result;
        } else {
            throw new Exception("Après extraction du résultat, la pile n'est "
                    + "pas vide.");
        }
    }

    public double run(String cmd) throws Exception {
        String[] calculation = cmd.split(" ");
            this.compute(calculation);
            return this.result();
    }
}
