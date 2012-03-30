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
public class PowerMaster {

    private static SolverThread[] arrayThread;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Inicialização de todos os módulos do PowerMaster
        Aplication app = new Aplication();
        
        
        
        /*if(Aplication.STATUS){
        arrayThread = new SolverThread[4];
        
        for (int i = 0; i < arrayThread.length; i++) {
            arrayThread[i] = new SolverThread();
        }
        
        for (int i = 0; i < arrayThread.length; i++) {
            arrayThread[i].start();
        }
        
        }else{
            System.out.println("Module Error!!!");
            
        }*/
        //Solver teste = new Solver(1000, 10, 0.1);
        //teste.execute();        
    }
}
