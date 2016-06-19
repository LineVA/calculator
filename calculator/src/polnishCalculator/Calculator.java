/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polnishCalculator;

import java.util.Scanner;

/**
 *
 * @author doyenm
 */
public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PolnishStack polnish = new PolnishStack();
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(polnish.run(scan.nextLine()));
            } catch (Exception ex) {
                System.out.println("Une erreur a eu lieu : " + ex.getClass());
                ex.printStackTrace();
            }
        }
    }

}
