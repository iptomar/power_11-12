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
 * Classe Individual, referente a cada individuo de uma determinada população.
 * Cada individuo é definido e diferenciado pelos seus cromossomas.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Individual {
    /**
     * ArrayList de cromossomas que especifica um determinado individuo.
     */
    private ArrayList<Chromosome> genome;
    /**
     * Variavel DEFAULT_SIZE que indica que, por defeito, um individuo
     * terá 1 cromossoma na sua definição genética.
     */
    private static final int DEFAULT_SIZE_GENOME = 1;
    /**
     * Variavél que receberá um número especifico de cromossoma que cada individuo
     * terá, caso não se pretenda o valor por defeito.
     */
    private int numberChromosomes = 0;
    /**
     * Número de genes por cromossoma, que por defeito, será 10.
     */
    private int numberGenes = 10;
    /**
     * Variavel que recebe o número fitness do individuo
     */
    private int fitness;
    
    /**
     * Construtor da classe que cria um novo arraylist de cromossomas
     * que por defeito, será de apenas 1.
     */
    public Individual() {
        this.numberChromosomes = DEFAULT_SIZE_GENOME;
        genome = new ArrayList<Chromosome>(getNumberChromosomes());
        this.inicializationChromosomes();
    }
    
    /**
     * Construtor da classe que recebe o número de cromossomas que o individuo
     * terá.
     * @param numberChromosomes (int) - Número de cromossomas que o individuo vai ter 
     */
    public Individual(int numberChromosomes) {
        this.numberChromosomes = numberChromosomes;
        genome = new ArrayList<Chromosome>(numberChromosomes);
        this.inicializationChromosomes();
    }
    
    /**
     * Construtor da classe que recebe o número de cromossomas que o individuo terá, bem como
     * os genes por cromossoma.
     * @param numberChromosomes (int) - Número de cromossomas do individuo
     * @param numberGenes (int) - Número de genes por cromossoma
     */
    public Individual(int numberChromosomes, int numberGenes){
        this.numberChromosomes = numberChromosomes;
        this.numberGenes = numberGenes;
        genome = new ArrayList<Chromosome>(numberChromosomes);
        this.inicializationChromosomes();
    }

    /**
     * Método que permite a inicialização do genoma do individuo, inicializando
     * os cromossomas que o mesmo irá ter.
     */
    private void inicializationChromosomes() {
        for (int i = 0; i < this.getNumberChromosomes(); i++) {
            this.genome.add(new Chromosome(numberGenes));
        }
        //Actualiza o fitness do individuo
        fitnessIndividual();
    }
    
    /**
     * Método que permite retirar um determinado cromossoma do individuo
     * @param index (int) - Index do cromossoma pretendido do genoma do individuo
     * @return (Chromosome) - Cromossoma do individuo
     */
    public Chromosome getChromosome(int index) {
        return genome.get(index);
    }

    /**
     * Método que permite definir um determinado cromossoma de um determinado individuo
     * @param index (int) - Index do cromossoma a ser definido no ArrayList
     * @param cromosome (Chromosome) - Cromossoma a ser definido
     */
    public void setChromosome(int index, Chromosome cromosome) {
        genome.set(index, cromosome);
        //Actualiza o fitness do individuo
        fitnessIndividual();
    }
    /**
     * Método que devolve o número de genes que estão a true por parte do individuo
     * @return (int) - Número de genes a true do individuo
     */
    public int fitnessIndividual(){
        int aux = 0;
        //Ciclo que percorre todos os cromossomas do individuo, retirando o número de genes
        //a true de cada um e adicionando o valor de genes verdadeiros ao fitness do individuo
        for (int i = 0; i < genome.size(); i++) {
            aux += genome.get(i).genesFitness();
        }
        fitness = aux;
        return aux;
    }

    /**
     * Método que devolve o fitness do individuo
     * @return fitness (int) - Fitness do individuo
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * Devolve o número de cromossomas do individuo
     * @return numberChromosomes (int) - Número de cromossomas do individuo
     */
    public int getNumberChromosomes() {
        return numberChromosomes;
    }
}
