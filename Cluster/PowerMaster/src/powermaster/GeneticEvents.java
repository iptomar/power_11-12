/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Aplication;
import Module.DataBase.Operations;
import genetics.Population;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import utils.EventsSolver;
import utils.PopulationUtils;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class GeneticEvents implements EventsSolver {

    int Interval = 100;
    int nextInterval;
    int idClient;
    int idProblem;
   
    public GeneticEvents(int interval,int idClient, int idProblem) {
        this.Interval = interval;
        this.nextInterval = Interval;
        this.idClient = idClient;
        this.idProblem = idProblem;
    }

    /*@Override
    public void EventStartSolver() {
        //try {
            System.out.println("Sovler - START");
            //Aplication.nodeJS.Emit("startrun", "1", "[[");
        //} catch (JSONException ex) {
            //Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

    @Override
    public void EventIteraction(int i, Population pltn) {
        //try {
            if (i == nextInterval) {
                int best = pltn.getBestFitness();
                //Aplication.nodeJS.Emit("event", "" + i, "" + pltn.getBestFitness());
                
               
                // Testes
                try {
                
                    //db.ExecuteNonQuery("INSERT INTO teste VALUES ("+ i + ","+ best +")");
                } catch (Exception e) {

                   
                }
                
                
                System.out.println(i + "-" + best);
                nextInterval += Interval;
            }
        //} catch (JSONException ex) {
            //Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

    @Override
    public void EventFinishSolver(Population pltn) {
        //try {
            int best = pltn.getBestFitness();
            //Aplication.nodeJS.Emit("event", "" + this.nextInterval, "" + pltn.getBestFitness());
            System.out.println(this.nextInterval + "-" + best);
            //Aplication.nodeJS.Emit("startrun", "1", "]]");
            System.out.println("Sovler - END");
        //} catch (JSONException ex) {
            //Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }*/

    @Override
    public void EventStartSolver() {
        //try {
            System.out.println("Sovler - START");
            //Aplication.nodeJS.Emit("startrun", "1", "[[");
        //} catch (Exception ex) {
            //Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

    @Override
    public void EventIteraction(int i, Population pltn) {
        PopulationUtils.getBestFitness(pltn);
        PopulationUtils.getNumberIndividualsWithBestFitness(pltn);
        PopulationUtils.getHallOfFame(pltn, 10);
        
        //op.InserirIteracoes(Thread.currentThread().getName().toString(),i,this.idClient, this.idProblem, pltn.getBestFiteness(),pltn.getMediaFitness(),pltn.getNumBestFitness(),pltn.getBestIndString(),pltn.getDesvioPadrao(),1);
       
    }

    @Override
    public void EventFinishSolver(int i, Population pltn) {
        System.out.println("Genetic Event Iteration:"+i+"\n");
        System.out.println("Sovler - END");
    }
}
