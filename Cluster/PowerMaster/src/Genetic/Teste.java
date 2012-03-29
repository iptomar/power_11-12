/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Genetic;

import java.util.ArrayList;

/* -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 *  I n s t i t u t o   P o l i t e c n i c o   d e   T o m a r
 *   E s c o l a   S u p e r i o r   d e   T e c n o l o g i a
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 * -------------------------------------------------------------------------
 * Número de Aluno: 13691 
 * E-mail: Ruben.Felix@gmail.com
 * -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 */
public class Teste {
    public static void main(String[] args) {
        
        Solver teste = new Solver(1000, 10, 0.1);
        teste.execute();
        
        
//        Population Pop1 = new Population(100, 1, 10);
//        Population Pop2 = new Population(100, 1, 10);
//        Pop1.addToPopulation(Pop2.getPopulation());
//        
//        System.out.println("Population size after added: " + Pop1.getPopulation().size());
        
//        Individual Ind1 = new Individual(1, 6);
//        Chromosome Crom = new Chromosome(6);
//        Gene geneT = new Gene(true);
//        Gene geneF = new Gene(false);
//        Crom.setGene(0, geneF);
//        Crom.setGene(1, geneT);
//        Crom.setGene(2, geneT);
//        Crom.setGene(3, geneT);
//        Crom.setGene(4, geneT);
//        Crom.setGene(5, geneF);
//        Ind1.setChromosome(0,  Crom);
//        
//        Individual Ind2 = new Individual(1, 6);
//        Crom = new Chromosome(6);
//        Crom.setGene(0, geneT);
//        Crom.setGene(1, geneT);
//        Crom.setGene(2, geneT);
//        Crom.setGene(3, geneT);
//        Crom.setGene(4, geneT);
//        Crom.setGene(5, geneF);
//        Ind2.setChromosome(0,  Crom);
//        
//        Individual Ind3 = new Individual(1, 6);
//        Crom = new Chromosome(6);
//        Crom.setGene(0, geneF);
//        Crom.setGene(1, geneT);
//        Crom.setGene(2, geneT);
//        Crom.setGene(3, geneF);
//        Crom.setGene(4, geneF);
//        Crom.setGene(5, geneF);
//        Ind3.setChromosome(0,  Crom);
//        
//        Individual Ind4 = new Individual(1, 6);
//        Crom = new Chromosome(6);
//        Crom.setGene(0, geneF);
//        Crom.setGene(1, geneF);
//        Crom.setGene(2, geneT);
//        Crom.setGene(3, geneF);
//        Crom.setGene(4, geneF);
//        Crom.setGene(5, geneT);
//        Ind4.setChromosome(0,  Crom);
//        
//        Individual Ind5 = new Individual(1, 6);
//        Crom = new Chromosome(6);
//        Crom.setGene(0, geneT);
//        Crom.setGene(1, geneT);
//        Crom.setGene(2, geneF);
//        Crom.setGene(3, geneF);
//        Crom.setGene(4, geneT);
//        Crom.setGene(5, geneF);
//        Ind5.setChromosome(0,  Crom);
//        
//        Individual Ind6 = new Individual(1, 6);
//        Crom = new Chromosome(6);
//        Crom.setGene(0, geneT);
//        Crom.setGene(1, geneF);
//        Crom.setGene(2, geneF);
//        Crom.setGene(3, geneF);
//        Crom.setGene(4, geneF);
//        Crom.setGene(5, geneF);
//        Ind6.setChromosome(0,  Crom);
//        
//        ArrayList<Individual>popRep = new ArrayList<Individual>(); 
//        Population pop1 = new Population(6, 1, 6);
//        pop1.setIndividual(0, Ind1);
//        pop1.setIndividual(1, Ind2);
//        pop1.setIndividual(2, Ind3);
//        pop1.setIndividual(3, Ind4);
//        pop1.setIndividual(4, Ind5);
//        pop1.setIndividual(5, Ind6);
//        
//        Reprodution rep = new Reprodution(pop1.getPopulation());
//        popRep = rep.execute();
//        System.out.println("----------------------------------- População BEFORE Reproduction -----------------------------------");
//        for (int i = 0; i < pop1.getPopulation().size(); i++) {
//            System.out.println("----------- Individuo número " + i + " -----------");
//            for (int j = 0; j < 6; j++) {
//                System.out.println("--- Genoma " + j + ": " + pop1.getPopulation().get(i).getChromosome(0).getGene(j).toString());
//            }
//        }
//        System.out.println("");
//        System.out.println("----------------------------------- População AFTER Reproduction -----------------------------------");
//        for (int i = 0; i < popRep.size(); i++) {
//            System.out.println("----------- Individuo número " + i + " -----------");
//            for (int j = 0; j < 6; j++) {
//                System.out.println("--- Genoma " + j + ": " + popRep.get(i).getChromosome(0).getGene(j).toString());
//            }
//        }
//        
////        for (int i = 0; i < pop1.HallOfFame().length; i++) {
////            System.out.println("Fitness " + (i+1) + "º individuo: " + pop1.HallOfFame()[i].fitnessIndividual());
////        }
////        double avarage = ((pop1.fitnessPopulation() + 0.0) / (pop1.getNumberIndividuals() + 0.0));
////        System.out.println("Média de fitness da população: " + avarage);
    }
}
