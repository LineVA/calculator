package infixCalculator_method1;

import java.util.Scanner;

/**
 *
 * @author doyenm
 */
public class InfixCalculator_1 {

    public static void main(String[] args) {
        Transform transform = new Transform();
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(transform.run(scan.nextLine()));
            } catch (Exception ex) {
                System.out.println("Une erreur a eu lieu : " + ex.getClass());
                ex.printStackTrace();
            }
        }
    }
}
