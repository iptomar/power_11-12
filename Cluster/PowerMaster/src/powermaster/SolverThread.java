/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;


import genetics.OnesMax;
import genetics.Solver;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.exceptions.SolverException;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class SolverThread extends Thread{
    
    
    private int pop;
    private int alello;
    private int itera;
    private int best;
    

    public SolverThread(int pop, int alello, int itera, int best) {
        this.pop = pop;
        this.alello = alello;
        this.itera = itera;
        this.best = best;
    }
    
    public void run(){
            //Solver __newSolver = new Solver(1000, 100, new OnesMax(), 100000, 99, new GeneticEvents(PowerMaster.INTERVAL_PART));
            Solver __newSolver = new Solver(pop, alello, new OnesMax(), itera, best, new GeneticEvents(PowerMaster.INTERVAL_PART));
            try {
                __newSolver.run();
            } catch (SolverException ex) {
                System.out.println("Solver Exception");
            }
         
    }
}
