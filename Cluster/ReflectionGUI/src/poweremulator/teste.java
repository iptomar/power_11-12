/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poweremulator;

import genetics.GenericSolver;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import reflection.GeneticLoader;

/**
 *
 * @author Miranda
 */
public class teste {
    
      private static GeneticLoader loader = new GeneticLoader();
      static GenericSolver solver;
      
      public static void main(String[] args) {
        try {
            solver = loader.getSolver();
            solver.setParameters("100 1 1 50 K50");
            solver.SetSelection("SUS"+" "+"100");
            solver.SetMutation("Flipbit"+" "+"0.75");
            solver.SetReplacement("Truncation"+" "+"100");
            solver.SetRecombination(".UniformCrossover"+" "+"0.75");
            solver.SetStopCrit("1000 1920");
            System.out.println("INICIEI");
            
            Thread_solverstar trabalhar = new Thread_solverstar(solver);
            trabalhar.start();
        } catch (Exception ex) {
            System.out.println("ERRO NO TESTE: "+ex);
        }
    }
}
