/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import genetics.Population;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class SaveStatus implements Serializable{
    
    private String Name;
    private ArrayList<Population> pops;
    
    public SaveStatus(String Name){
        this.Name = Name;
        pops = new ArrayList<Population>();
    }
    
    public void AddPopulation(Population pop){
        pops.add(pop);
    }
    
    public int getNumPopulations(){
        return this.pops.size();
    }
    
    public Population getPopulation(int index){
        return this.pops.get(index);
    }
    
}
