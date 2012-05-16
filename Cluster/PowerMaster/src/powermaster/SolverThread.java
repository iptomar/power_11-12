/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import genetics.GenericSolver;
import genetics.Solver;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class SolverThread extends Thread {

    GenericSolver solver;
    AtomicInteger numThreads;

    /**
     * 
     * @param solve
     * @param numThreads 
     * 
     */
    public SolverThread(GenericSolver solve, AtomicInteger numThreads) {
        this.solver = solve;
        this.numThreads = numThreads;
    }

    public void Stop(){
        //solver.getPopulation();
        //solver.Stop();
    }
    
    public void run() {
        try {
//            GenericSolver __newSolver = solver;
//            __newSolver.run();
            
            solver.run();
            numThreads.getAndDecrement();
            System.out.println("Atomic numThreads: " + numThreads.toString());
        } catch (Exception ex) {
            Logger.getLogger(SolverThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
