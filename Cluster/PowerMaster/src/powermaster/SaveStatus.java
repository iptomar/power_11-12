/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import genetics.Individual;
import genetics.Population;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import statistics.Statistics;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class SaveStatus implements Serializable {

    private String Name;
    private ArrayList<Population> pops;
    private int tipo;

    public SaveStatus(String Name, int tipo) {
        this.Name = Name;
        pops = new ArrayList<Population>();
    }

    public void AddPopulation(Population pop) {
        pops.add(pop);
    }

    public int getNumPopulations() {
        return this.pops.size();
    }

    public Population getPopulation(int index) {
        return this.pops.get(index);
    }

    public synchronized String getBestPopulation() {
        double bestOfAll = 0;
        int index = -1;//index da melhor população encontrada
        if (this.tipo == 0) {
            for (int i = 0; i < pops.size(); i++) {
                Statistics s = new Statistics(pops.get(i));
                if (s.getMediaFitnessPopulation() > bestOfAll) {
                    index = i;
                }
            }
        } else {
            bestOfAll = 1000000;
            for (int i = 0; i < pops.size(); i++) {
                Statistics s = new Statistics(pops.get(i));
                if (s.getMediaFitnessPopulation() < bestOfAll) {
                    index = i;
                }
            }            
        }
        String json = "";
        if (index >= 0) {
            json += "'data':[";
            Population p = pops.get(index);
            int i = 1;
            for (Individual individuo : p) {
                json += "[";
                json += "'" + i + "',";
                json += "'" + individuo.fitness() + "'";
                json += "]";
                if (i < p.getSizePopulation()) {
                    json += ",";
                }
                i++;
            }
            json += "]";

        }
        return json;
    }
    
    public synchronized String getBestPopulationFunction() {
        double bestOfAll = 0;
        int index = -1;//index da melhor população encontrada
        if (this.tipo == 0) {
            for (int i = 0; i < pops.size(); i++) {
                Statistics s = new Statistics(pops.get(i));
                if (s.getMediaFitnessPopulation() > bestOfAll) {
                    index = i;
                }
            }
        } else {
            bestOfAll = 1000000;
            for (int i = 0; i < pops.size(); i++) {
                Statistics s = new Statistics(pops.get(i));
                if (s.getMediaFitnessPopulation() < bestOfAll) {
                    index = i;
                }
            }            
        }
        String json = "";
        if (index >= 0) {
            json += "'data':[";
            Population p = pops.get(index);
            int i = 1;
            for (Individual individuo : p) {
                json += "[";
                json += "'" + i + "',";
                json += "'" + individuo.toString() + "'";
                json += "]";
                if (i < p.getSizePopulation()) {
                    json += ",";
                }
                i++;
            }
            json += "]";

        }
        return json;
    }    
    
}
