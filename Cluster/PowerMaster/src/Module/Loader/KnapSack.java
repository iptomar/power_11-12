/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Loader;

import genetics.Solver;
import java.util.ArrayList;
import powermaster.GeneticEvents;
/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class KnapSack extends Problem {

    public static final String ProblemName = "KnapSack";
    /**
     * Tamanho da população
     */
    private int popSize;
    public static final String PARAM_POPULATION_SIZE = "pop";
    /**
     * Número maximo de Iterações
     */
    private int Iterations;
    public static final String PARAM_ITERATIONS = "iterations";
    /**
     * Condição de paragem. Melhor fitness individuo
     */
    private int bestFitness;
    public static final String PARAM_BEST_FITNESS = "best";
    /**
     * Capacidade maxima que a mochila pode contem
     */
    private int maxWeight;
    public static final String PARAM_MAX_WEIGHT = "weight";
    /**
     * Valor maximo de peso que cada item pode ter
     */
    private int maxItemWeight;
    public static final String PARAM_MAX_ITEM_WEIGHT = "max_item_weight";
    /**
     * Valor maximo de valor que cada item pode ter
     */
    private int maxItemValue;
    public static final String PARAM_MAX_ITEM_VALUE = "max_item_value";
    /**
     * Número de items que existem no problema
     */
    private int numItems;
    public static final String PARAM_NUM_ITEMS = "numItems";
    /**
     * Verificação de penalização no problema
     */
    private boolean penalty = true;
    public static final String PARAM_PENALTY = "penalty";
    /**
     * Número de parametros que são necessário para que o problema OneMax seja corrido
     */
    private static int PARAM_REQUIRED = 5;//Número de parametros obrigatórios
    private int PARAM_ADDED = 0;//Número de parametros adicionados obrigatórios
    private static int PARAM_COUNT = 8;//Número de parametros existentes no problema
    /**
     * Referência para todas as linhas existentes no documento
     */
    private String data[];
    /**
     * Variável que indica o estado do loader
     */
    private boolean loadStatus;
    /**
     * Estrutura de dados do problema
     */
    //private Mochila mochila;

    /**
     * Construtor do problema OnesMax
     * @param data Informação do problema a ser carregado
     */
    public KnapSack(String data[]) {
        super(KnapSack.ProblemName, PARAM_COUNT);
        this.data = data;
        this.loadStatus = Load();
    }

    private boolean Load() {
        System.out.println("----Params Data-----");
        //Ler a segunda linha que contem todos os parametros de arranque
        String[] parmsData = data[1].split(";");
        //Percorrer todos os parametros
        for (int i = 0; i < parmsData.length; i++) {
            //Separar o identificado do valor (=)
            String[] param = parmsData[i].split("=");
            //Verificação de parametros
            //parametro 1 (Obrigatório)
            if (param[0].equals(KnapSack.PARAM_ITERATIONS) && !this.containsParam(KnapSack.PARAM_ITERATIONS)) {
                //carregar o parametro para um dicionario de parametros
                this.Iterations = Integer.parseInt(param[1]);
                this.addParam(KnapSack.PARAM_ITERATIONS, this.Iterations);
                //registar parametros obrigatório
                this.PARAM_ADDED++;
                System.out.println(KnapSack.PARAM_ITERATIONS + "+:" + this.Iterations);
                continue;
            }
            //parametro 2
            if (param[0].equals(KnapSack.PARAM_POPULATION_SIZE) && !this.containsParam(KnapSack.PARAM_POPULATION_SIZE)) {
                this.popSize = Integer.parseInt(param[1]);
                this.addParam(KnapSack.PARAM_POPULATION_SIZE, this.popSize);
                //registar parametros obrigatório
                this.PARAM_ADDED++;
                System.out.println(KnapSack.PARAM_POPULATION_SIZE + "+:" + this.popSize);
                continue;
            }
            //parametro 3
            if (param[0].equals(KnapSack.PARAM_BEST_FITNESS) && !this.containsParam(KnapSack.PARAM_BEST_FITNESS)) {
                this.bestFitness = Integer.parseInt(param[1]);
                this.addParam(KnapSack.PARAM_BEST_FITNESS, this.bestFitness);
                //registar parametros obrigatório
                this.PARAM_ADDED++;
                System.out.println(KnapSack.PARAM_BEST_FITNESS + "+:" + this.bestFitness);
                continue;
            }
            //parametro 4
            if (param[0].equals(KnapSack.PARAM_MAX_WEIGHT) && !this.containsParam(KnapSack.PARAM_MAX_WEIGHT)) {
                this.maxWeight = Integer.parseInt(param[1]);
                this.addParam(KnapSack.PARAM_MAX_WEIGHT, this.maxWeight);
                //registar parametros obrigatório
                this.PARAM_ADDED++;
                System.out.println(KnapSack.PARAM_MAX_WEIGHT + "+:" + this.maxWeight);
                continue;
            }
            //parametro 5
            if (param[0].equals(KnapSack.PARAM_MAX_ITEM_WEIGHT) && !this.containsParam(KnapSack.PARAM_MAX_ITEM_WEIGHT)) {
                this.maxItemWeight = Integer.parseInt(param[1]);
                this.addParam(KnapSack.PARAM_MAX_ITEM_WEIGHT, this.maxItemWeight);
                System.out.println(KnapSack.PARAM_MAX_ITEM_WEIGHT + ":" + this.maxItemWeight);
                continue;
            }
            //parametro 6
            if (param[0].equals(KnapSack.PARAM_MAX_ITEM_VALUE) && !this.containsParam(KnapSack.PARAM_MAX_ITEM_VALUE)) {
                this.maxItemValue = Integer.parseInt(param[1]);
                this.addParam(KnapSack.PARAM_MAX_ITEM_VALUE, this.maxItemValue);
                System.out.println(KnapSack.PARAM_MAX_ITEM_VALUE + ":" + this.maxItemValue);
                continue;
            }
            //parametro 7
            if (param[0].equals(KnapSack.PARAM_NUM_ITEMS) && !this.containsParam(KnapSack.PARAM_NUM_ITEMS)) {
                this.numItems = Integer.parseInt(param[1]);
                this.addParam(KnapSack.PARAM_NUM_ITEMS, this.numItems);
                System.out.println(KnapSack.PARAM_NUM_ITEMS + ":" + this.numItems);
                continue;
            }
            //parametro 8
            if (param[0].equals(KnapSack.PARAM_PENALTY) && !this.containsParam(KnapSack.PARAM_PENALTY)) {
                this.penalty = Boolean.parseBoolean(param[1]);
                this.addParam(KnapSack.PARAM_PENALTY, this.penalty);
                this.PARAM_ADDED++;
                System.out.println(KnapSack.PARAM_PENALTY + "+:" + this.penalty);
                continue;
            }
        }
        //Verificar se todos os parametros foram carregados
        if (this.PARAM_ADDED >= KnapSack.PARAM_REQUIRED) {

            //Verificar se na linha 2 do documento existe a tag de inicio de dados
            if (this.data[2].equals("<DataStart>")) {

                int line = 3;
                //ArrayList<Item> values = new ArrayList<Item>();
                System.out.println("-----DataStart-----");
                //Enquanto for diferente que a tag de terminação de ficheiro vai adicionado
                while (!this.data[line].trim().equals("<DataEnd>")) {
                    //Ler o novo item
                    String[] item = this.data[line].split(";");
                    //adicionar o novo item
                    int peso =Integer.parseInt(item[0]);
                    int valor = Integer.parseInt(item[1]);
                    System.out.println(peso+ "-" +valor);
                    //values.add(new Item(peso, valor,this.penalty));
                    //passar para a proxima linha
                    line++;
                }
                //mochila = new Mochila(this.maxWeight, values, this.penalty);
                System.out.println("------DataEnd------");
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Método que retorna o NOVO solver com os parametros carregados por o ficheiro.
     * @return 
     */
    @Override
    public Solver getNewSolver() {
        //return new SolverKnapSack(popSize, new genetics.KnapSack(mochila), Iterations, bestFitness,mochila, new GeneticEvents(PowerMaster.INTERVAL_PART,1,1));
        //return new Solver(popSize, 100, new genetics.KnapSack(mochila), Iterations, bestFitness, new GeneticEvents(PowerMaster.INTERVAL_PART,1,1));
        return null;
    }

    public boolean getStatus() {
        return this.loadStatus;
    }

    public int getIterations() {
        return Iterations;
    }

    public int getBestFitness() {
        return bestFitness;
    }

    public int getPopSize() {
        return popSize;
    }
}
