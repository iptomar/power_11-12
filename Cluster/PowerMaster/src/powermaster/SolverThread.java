/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Genetic.Solver;
import Module.Aplication;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class SolverThread extends Thread{
    
    private Solver teste;
    
    public SolverThread(int tamanhoPop, int Paragem, double mutation){
        System.out.println("New Thread Solver");
        teste = new Solver(tamanhoPop, Paragem, mutation);
    }
    
    public void run(){
        teste.execute();
         
    }
}
