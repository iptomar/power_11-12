/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;


import genetics.OnesMax;
import genetics.Solver;
import java.util.concurrent.atomic.AtomicInteger;

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
            //Solver __newSolver = new Solver(1000, 100, new OnesMax(), 100000, 99, new GeneticEvents(PowerMaster.INTERVAL_PART));
            Solver __newSolver = solve;
            __newSolver.run();
            
            numThreads.getAndDecrement();
            System.out.println("Atomic numThreads: " + numThreads.toString());
    }
}
