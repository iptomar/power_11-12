/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;


import genetics.OnesMax;
import genetics.Solver;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class SolverThread extends Thread{
    
    private Solver teste;
    
    public SolverThread(){
        System.out.println("New Thread Solver");
   
    }
    
    public void run(){
            Solver __newSolver = new Solver(1000, 100, new OnesMax(), 100000, 99, new GeneticEvents());
            __newSolver.run();
         
    }
}
