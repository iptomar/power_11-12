/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;


import genetics.OnesMax;
import genetics.Solver;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.exceptions.SolverException;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class SolverThread extends Thread{
    
Solver solve;
AtomicInteger numThreads;

    public SolverThread(Solver solve, AtomicInteger numThreads) {
           this.solve = solve;
           this.numThreads = numThreads;
    }
    
    public void run(){
        try {
            //Solver __newSolver = new Solver(1000, 100, new OnesMax(), 100000, 99, new GeneticEvents(PowerMaster.INTERVAL_PART));
            Solver __newSolver = solve;
            __newSolver.run();
            
            numThreads.getAndDecrement();
            System.out.println("Atomic numThreads: " + numThreads.toString());
        } catch (SolverException ex) {
            Logger.getLogger(SolverThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
