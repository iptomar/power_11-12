package Genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
 * Classe Population que será a população com tantos individuos quantos os pretendidos
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Population {

    /**
     * ArrayList que irá conter todos os individuos da população
     */
    private ArrayList<Individual> population;
    /**
     * Variavel DEFAULT_SIZE que indica que, por defeito, uma população
     * terá 1000 individuos.
     */
    private static final int DEFAULT_SIZE_POPULATION = 1000;
    /**
     * Caso não se pretenda utilizar por defeito 1000 individuos, a variavel numberIndividuals
     * irá receber o valor de individuos a utilizar na população
     */
    private int numberIndividuals = DEFAULT_SIZE_POPULATION;
    /**
     * Variavel que irá receber o valor de genes a codificar por cromossoma
     */
    private int numberGenes = 10;
    /**
     * Variavel que receberá o valor de cromossomas a codificar por cada individuo
     */
    private int numberChromosomes = 1;

    /**
     * Construtor da classe que recebe como parametros o número de individuos da população,
     * o número de cromossomas por individuo e o número de genes por cromossoma
     * @param numberIndividuals (int) - Número de individuos da população 
     * @param numberChromosomes (int) - Número de cromossomas por individuo
     * @param numberGenes (int) -  Número de genes por cromossoma
     */
    public Population(int numberIndividuals, int numberChromosomes, int numberGenes) {
        this.numberIndividuals = numberIndividuals;
        this.numberChromosomes = numberChromosomes;
        this.numberGenes = numberGenes;
        population = new ArrayList<Individual>(numberIndividuals);
        inicializationPopulation();
    }
    
    /**
     * Construtor da classe que recebe um ArrayList de individuos e define o mesmo como
     * sendo a população
     * @param Population (ArrayList<Individual>)  - População a ser definida
     */
    public Population(ArrayList<Individual> Population){
        this.population = Population;
        this.numberIndividuals = Population.size();
    }

    /**
     * Construtor da classe que recebe como parametros o número de cromossomas por individuo 
     * e o número de genes por cromossoma, sendo que o número de individuos da população é
     * o número por defeito.
     * @param numberChromosomes (int) - Número de cromossomas por individuo
     * @param numberGenes (int) -  Número de genes por cromossoma
     */
    public Population(int numberChromosomes, int numberGenes) {
        this.numberChromosomes = numberChromosomes;
        this.numberGenes = numberGenes;
        population = new ArrayList<Individual>(getNumberIndividuals());
        inicializationPopulation();
    }

    /**
     * Construtor da classe que recebe como parametros o número de genes por cromossoma, sendo que
     * os cromossomas por individuo fica definido por defeito, bem como o número de individuos na
     * população.
     * @param numberGenes (int) -  Número de genes por cromossoma
     */
    public Population(int numberGenes) {
        this.numberGenes = numberGenes;
        population = new ArrayList<Individual>(getNumberIndividuals());
        inicializationPopulation();
    }

    /**
     * Construtor da classe que inicializa a população toda com os valores por defeito em todos
     * os seus parametros.
     */
    public Population() {
        population = new ArrayList<Individual>(getNumberIndividuals());
        inicializationPopulation();
    }

    /**
     * Método que inicializa toda a poplação, definindo o número de cromossomas
     * por inidividuo e também o número de genes por cromossoma que o individuo terá
     */
    private void inicializationPopulation() {
        for (int i = 0; i < this.getNumberIndividuals(); i++) {
            this.getPopulation().add(new Individual(numberChromosomes, numberGenes));
        }
    }

    /**
     * Método que devolve um determinado individuo da população.
     * @param index (int) - Index do individuo a ser retirado do arraylist
     * @return (Individual) - Individuo da população
     */
    public Individual getIndividual(int index) {
        return getPopulation().get(index);
    }

    /**
     * Método que permite fazer a definição de um determinado individuo na população
     * @param index (int) - Index onde o individuo será inserido na população
     * @param individual (Individual) - Individuo a ser definido na população
     */
    public void setIndividual(int index, Individual individual) {
        getPopulation().set(index, individual);
    }

    /**
     * Método que retorna o fitness da população, baseado em todos os valores a true
     * de cada gene, de cada cromossoma de cada individuo da população
     * @return (int) - Valor fitness da população
     */
    public int fitnessPopulation() {
        int aux = 0;
        //Percorre todo o arraylist de individuos, retirando de cada um deles o seu
        //fitness e incrementando o valor a aux, que será no fim do arraylist o fitness
        //da população
        for (int i = 0; i < getPopulation().size(); i++) {
            aux += getPopulation().get(i).fitnessIndividual();
        }
        return aux;
    }
    
    /**
     * Método que devolve a média de fitness da população
     * @return (double) - Média de fitness da população
     */
    public double fitnessPopulationAvarage(){
        double aux = 0.0;
        aux = (fitnessPopulation() + 0.0) / (population.size() + 0.0);
        return aux;
    }
    
    /**
     * Método que permite a adição de uma população forasteira à que está definida na classe
     * @param toBeAdded (ArrayList<Individual>) - População a ser adicionada
     */
    public void addToPopulation(ArrayList<Individual> toBeAdded){
        for (int i = 0; i < toBeAdded.size(); i++) {
            population.add(toBeAdded.get(i));
        }
        fitnessPopulation();
    }

    /**
     * Método que retorna os dez melhores individuos da população
     * @return (Individual[]) - Array dos dez melhores individuos da população
     */
    public Individual[] HallOfFame() {
        //Ordena a população pelo seu fitness
        ordenaIndividuosFitness();
        //Array que receberá os 10 melhores individuos da população pelo seu valor de fitness
        Individual[] IndividuosHOF = new Individual[10];
        for (int i = 0; i < 10; i++) {
            IndividuosHOF[i] = getPopulation().get(i);
        }
        return IndividuosHOF;
    }
    
    /**
     * Método que retorna os melhores individuos da população, com base no seu fitness. O range dos
     * individuoes é passado por parametro.
     * @param range (int) - Range alcançado pelo método.
     * @return (Individual[]) - Array dos melhores individuos da população
     */
    public Individual[] HallOfFame(int range) {
        //Ordena a população pelo seu fitness
        ordenaIndividuosFitness();
        //Array que receberá os 10 melhores individuos da população pelo seu valor de fitness
        Individual[] IndividuosHOF = new Individual[range];
        for (int i = 0; i < range; i++) {
            IndividuosHOF[i] = getPopulation().get(i);
        }
        return IndividuosHOF;
    }

    /**
     * Método que permite a ordenação dos individuos da população por valor de fitness
     */
    private void ordenaIndividuosFitness() {
        //Ordena a população pelo fitness de cada individuo
        Collections.sort(getPopulation(), new Comparator() {
            @Override
            public int compare(Object Ind1, Object Ind2) {
                if (((Individual)Ind1).fitnessIndividual() < ((Individual) Ind2).getFitness()) return -1;
                else if (((Individual)Ind1).fitnessIndividual() == ((Individual) Ind2).getFitness()) return 0;
                else return 1;
            }
        });
        //Faz o reverse da lista para que os primeiros individuoes sejam o que tem maior fitness e não
        //o contrário
        Collections.reverse(getPopulation());
    }

    @Override
    public String toString() {
        //Variavel que contem a mudança de linha do sistema
        String NL = System.getProperty("line.separator");
        //Stringbuilder que receberá a informação
        StringBuilder value = new StringBuilder();



        return value.toString();
    }

    /**
     * Método que devolve o número de individuos da população
     * @return numberIndividuals (int) - Número de individuos da população
     */
    public int getNumberIndividuals() {
        return numberIndividuals;
    }

    /**
     * Método que devolve a população
     * @return (ArrayList<Individual>) - População
     */
    public ArrayList<Individual> getPopulation() {
        return population;
    }

    /**
     * Método que permite a definição da população
     * @param population (ArrayList<Individual>) - População a ser definida
     */
    public void setPopulation(ArrayList<Individual> population) {
        this.population = population;
    }

}
