/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Aplication;
import genetics.Population;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.omg.CORBA.portable.ApplicationException;
import utils.EventsSolver;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class GeneticEvents implements  EventsSolver {

    public GeneticEvents(){
        
    }
    
    @Override
    public void EventStartSolver() {
        try {
            Aplication.nodeJS.Emit("startrun", "1", "[[");
        } catch (JSONException ex) {
            Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public void EventIteraction(int i, Population pltn) {
        try {
            int best = pltn.getBestFitness();
            Aplication.nodeJS.Emit("event", ""+i, ""+pltn.getBestFitness());
            System.out.println(i+"-"+best);
        } catch (JSONException ex) {
            Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void EventFinishSolver(Population pltn) {
        try {
            Aplication.nodeJS.Emit("startrun", "1", "]]");
        } catch (JSONException ex) {
            Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
