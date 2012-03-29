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
 * Classe Chromosome que será um cromossoma (conjunto de genes) de um determinado individuo,
 * fazendo assim a sua definição.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Chromosome{
    /**
     * ArrayList de genes que será a definição de um cromossoma
     */
    private ArrayList<Gene> genotype;
    /**
     * Variavel DEFAULT_SIZE que indica que, por defeito, um cromossoma
     * terá 10 genes na sua codificação.
     */
    private static final int DEFAULT_SIZE_CHROMOSOME = 10;
    /**
     * Caso não se pretenda utilizar por defeito 10 genes, a variavel numberGenes
     * irá receber o valor de genes a utilizar por cromossoma
     */
    private int numberGenes = 0;

    /**
     * Construtor da classe em que inicializa o cromossoma como
     * tendo 10 genes de codificação.
     */
    public Chromosome() {
        this.genotype = new ArrayList<Gene>(DEFAULT_SIZE_CHROMOSOME);
        this.numberGenes = DEFAULT_SIZE_CHROMOSOME;
        this.criaGenes();
    }

    /**
     * Construtor da classe em que o número de genes a ser codificado pelo
     * cromossoma é passado por parametro.
     * @param sizeChromosome (int) - Número de genes a codificar para o cromossoma
     */
    public Chromosome(int sizeChromosome) {
        this.genotype = new ArrayList<Gene>(sizeChromosome);
        this.numberGenes = sizeChromosome;
        this.criaGenes();
    }
    
    /**
     * Método que permite saber qual o gene definido em determinada posição do cromossoma
     * @param index (int) - Index da posição no cromossoma (a começar em 0)
     * @return (Gene) - Gene definido na posição do cromossoma
     */
    public Gene getGene(int index){
        if(getGenotype().isEmpty()) return null;
        else return getGenotype().get(index);
    }
    
    /**
     * Método que permite a definição de um determinado gene em determinada posição do cromossoma
     * @param index (int) - Index da posição no cromossoma
     * @param gene (Gene) - Gene a ser definido no cromossoma
     */
    public void setGene(int index, Gene gene){
        getGenotype().set(index, gene);
    }
    
    /**
     * Método que criará automaticamente os genes aleatórios para o cromossoma
     */
    public void criaGenes(){
        Gene gene;
        /**
         * Ciclo que criará tantos genes aleatórios quantos os que sejam pretendidos,
         * adicionando-os automaticamente ao arraylist do cromossoma
         */
        for (int i = 0; i < getNumberGenes(); i++) {
            gene = new Gene();
            getGenotype().add(gene);
        }
    }
    
    /**
     * Método que devolve o número de genes a true que o cromossoma detem.
     * @return (int) - Número de genes a true.
     */
    public int genesFitness(){
        int aux = 0;
        for (int i = 0; i < getGenotype().size(); i++) {
            //Se o gene for true, incrementa a variavel aux.
            if(getGenotype().get(i).getValue()) aux++;
        }
        return aux;
    }

    /**
     * @return the genotype
     */
    private ArrayList<Gene> getGenotype() {
        return genotype;
    }

    /**
     * Método que devolve o número de genes por cromossoma
     * @return numberGenes (int) - Numero de genes do cromossoma
     */
    public int getNumberGenes() {
        return numberGenes;
    }

}
