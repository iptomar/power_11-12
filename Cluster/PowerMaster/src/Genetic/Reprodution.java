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

/**
 * Classe que irá tratar da reprodução da população
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Reprodution {
    /**
     * Variavel que irá receber a população a ser reproduzida
     */
    private ArrayList<Individual> populationBeforeRepr;
    /**
     * Variavel que irá conter a população de filhos resultante da reprodução da população
     * dos pais
     */
    private ArrayList<Individual> populationAfterRepr;
    
    /**
     * Construtor por defeito da classe, que irá receber a população pai para se 
     * reproduzir
     * @param population (ArrayList<Individual>) - População pai para ser reproduzida
     */
    public Reprodution(ArrayList<Individual> population){
        populationBeforeRepr = population;
        populationAfterRepr = new ArrayList<Individual>();
    }   
    
    //FALTA IMPLEMENTAÇÃO
    public ArrayList<Individual> execute(){
        //*******************************************************************************
        //SÓ FUNCIONA PARA O CASO DOS INDIVIDUOS TEREM APENAS UM CROMOSSOMA NO SEU GENOMA
        //*******************************************************************************
        int numGenes = populationBeforeRepr.get(0).getChromosome(0).getNumberGenes();
        int trim = numGenes/2;
        //Criação de dois novos individuos queserão os filhos da reprodução de dois pais, com o mesmo número
        //de cromossomas e de genes por cromossoma
        Individual filho1 = new Individual(populationBeforeRepr.get(0).getNumberChromosomes(), numGenes);
        Individual filho2 = new Individual(populationBeforeRepr.get(0).getNumberChromosomes(), numGenes);
        //Dois cromossomas novos que serão a junção dos cromossomas para os filhos
        Chromosome cromFilho1 = new Chromosome(numGenes);
        Chromosome cromFilho2 = new Chromosome(numGenes);
        //Caso o número de genes for número par
        if((trim*2)==numGenes){
            //Ciclo que percorre todo o array da população inicial
            for (int i = 0; i < populationBeforeRepr.size(); i++) {
                //Novos objectos do tipo Individual para receber os novos cromossomas
                filho1 = new Individual(populationBeforeRepr.get(0).getNumberChromosomes(), numGenes);
                filho2 = new Individual(populationBeforeRepr.get(0).getNumberChromosomes(), numGenes);
                //Novos objectos do tipo cromossomas para serem definidos e serem inseridos nos filhos
                //provenientes da reprodução dos pais
                cromFilho1 = new Chromosome(numGenes);
                cromFilho2 = new Chromosome(numGenes);
                //Copia os genes dos pais para os filhos (a primeira metade)
                for (int j = 0; j < trim; j++) {
                    cromFilho1.setGene(j, populationBeforeRepr.get(i).getChromosome(0).getGene(j));
                    cromFilho2.setGene(j, populationBeforeRepr.get(i+1).getChromosome(0).getGene(trim+j));
                }
                //Copia os genes dos pais para os filhos (a segunda metade)
                for (int j = trim; j < numGenes; j++) {
                    cromFilho1.setGene(j, populationBeforeRepr.get(i+1).getChromosome(0).getGene(j-trim));
                    cromFilho2.setGene(j, populationBeforeRepr.get(i).getChromosome(0).getGene(j));
                }
                //Percorre o array da população de dois em dois
                i++;
                //Faz o set dos cromossomas
                filho1.setChromosome(0, cromFilho1);
                filho2.setChromosome(0, cromFilho2);
                populationAfterRepr.add(filho1);
                populationAfterRepr.add(filho2);
            }
        }
        //Caso seja impar
        else{
            
        }
        return populationAfterRepr;
    }
}
