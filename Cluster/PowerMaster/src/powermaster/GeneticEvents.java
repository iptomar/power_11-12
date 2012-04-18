/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Aplication;
import Module.DataBase.Database;
import genetics.Population;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import utils.EventsSolver;

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

    @Override
    public void EventStartSolver() {
        try {
            System.out.println("Sovler - START");
            Aplication.nodeJS.Emit("startrun", "1", "[[");
        } catch (JSONException ex) {
            Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void EventIteraction(int i, Population pltn) {
        try {
            if (i == nextInterval) {
                int best = pltn.getBestFitness();
                Aplication.nodeJS.Emit("event", "" + i, "" + pltn.getBestFitness());
                
               
                // Testes
                try {
                  Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD","code.dei.estt.ipt.pt");
                    db.ExecuteInsertIteration(Thread.currentThread().getName().toString(), i, idClient, idProblem, pltn.getBestFitness(), 0, 0, "", 0, 0);
                } catch (Exception ex) {
                    Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                System.out.println(i + "-" + best);
                nextInterval += Interval;
            }
        } catch (JSONException ex) {
            Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void EventFinishSolver(Population pltn) {
        try {
            int best = pltn.getBestFitness();
            Aplication.nodeJS.Emit("event", "" + this.nextInterval, "" + pltn.getBestFitness());
            System.out.println(this.nextInterval + "-" + best);
            Aplication.nodeJS.Emit("startrun", "1", "]]");
            System.out.println("Sovler - END");
        } catch (JSONException ex) {
            Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
