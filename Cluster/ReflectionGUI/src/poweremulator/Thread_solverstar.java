/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poweremulator;

import genetics.GenericSolver;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.exceptions.SolverException;
import utils.exceptions.SonsInicialitazionException;

/**
 *
 * @author Miranda
 */
public class Thread_solverstar extends Thread{
    
    GenericSolver solver;
    public Thread_solverstar(GenericSolver solve){
        solver=solve;
    }
    
    @Override
    public void run(){
        
        try {
            solver.run();
        } catch (Exception ex) {
            System.out.println("ERRO NA THREAD SOLVER_START: "+ex);
        }
    }
    
}
