/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Genetic;

import java.util.ArrayList;

/**
 *
 * @author Catarina Neves 13877
 * 
 * 
 * Classe que realiza torneio entre elementos de uma população, dois a dois
 */
public class Tournament {

    //lista que guarda os melhores, escolhidos após competirem
    private ArrayList<Individual> popBest;

    /**
     * Construtor da classe
     */
    public Tournament(){
        popBest = new ArrayList<Individual>();
    }
    
    //compara os individuos 2 a 2, escolhe o melhor e guarda em popBest
    public ArrayList<Individual> execute(ArrayList<Individual> receiver) {
            //incrementa de 2 em 2 para não comparar elementos repetidos
            for (int i = 0; i < receiver.size(); i = i + 2) { 
                //primeiro elemento a comparar
                Individual first = receiver.get(i);       
                //segundo elemento a comparar - elemento seguinte na lista da populacao a comparar - lista receiver
                Individual second = receiver.get(i + 1);
                //comparacao entre os 2 elementos
                if (first.getFitness() >= second.getFitness()) {
                    //se o primeiro elemento tiver um fitness mais elevado, este é guardado na lista popBest
                    popBest.add(first);

                } else {
                    //se o segundo elemento tiver um fitness mais elevado, este é guardado na lista popBest
                    popBest.add(second);
                }
            }
        return popBest;
    }
}
