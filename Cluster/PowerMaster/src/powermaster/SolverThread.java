/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import genetics.GenericSolver;
import genetics.Individual;
import genetics.Population;
import genetics.Solver;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.PopulationUtils;

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

    public Population Stop(){  
        Population p = solver.getPopulation();
        solver.StopSolver();
        return p;
    }
    
    public Population getPopulation(){
        return solver.getPopulation();
    }
    
    public Collection<Individual> getUniqueIndividuals(double fitness){
        return PopulationUtils.getUniqueIndividuals(this.solver.getPopulation(),fitness);
    }
    
    public void run() {
        try {
//            GenericSolver __newSolver = solver;
//            __newSolver.run();
            
            solver.run();
            numThreads.getAndDecrement();
            System.out.println("Atomic numThreads: " + numThreads.toString());
        } catch (Exception ex) {
            numThreads.getAndDecrement();
            System.out.println("Force Stop: " + numThreads.toString());
            Logger.getLogger(SolverThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
