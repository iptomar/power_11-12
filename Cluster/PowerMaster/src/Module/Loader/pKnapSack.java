/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Loader;

import genetics.Individual;
import genetics.Solver;
import genetics.StopCriterion;
import genetics.algorithms.KnapSack.ModeFunction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import powermaster.GeneticEvents;
import powermaster.PowerMaster;
/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class pKnapSack extends Problem {

    public static final String ProblemName = "KnapSack";
    /**
     * Tamanho da população
     */
    private int popSize;
    public static final String PARAM_POPULATION_SIZE = "pop";
    /**
     * Tamanho do alelo
     */
    private int alelloSize;
    public static final String PARAM_ALELLO_SIZE = "alello";    
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
     * Número de items que existem no problema
     */
    private int lenght;
    public static final String PARAM_LENGHT = "lenght";
    /**
     * Verificação de penalização no problema
     */
    private int penalty;
    public static final String PARAM_PENALTY = "penalty";
    /**
     * Mode de funcionamento do problema KnapSack
     */
    private int mode;
    public static final String PARAM_MODE_FUNCTION = "mode";
    
    /**
     * Número de parametros que são necessário para que o problema OneMax seja corrido
     */
    private static int PARAM_REQUIRED = 6;//Número de parametros obrigatórios
    private int PARAM_ADDED = 0;//Número de parametros adicionados obrigatórios
    private static int PARAM_COUNT = 6;//Número de parametros existentes no problema
    /**
     * Referência para todas as linhas existentes no documento
     */
    private JSONObject data;
    /**
     * Variável que indica o estado do loader
     */
    private boolean loadStatus;
    /**
     * Estrutura de dados do problema
     */
    private int ValorPeso[][];

    /**
     * Construtor do problema OnesMax
     * @param data Informação do problema a ser carregado
     */
    public pKnapSack(JSONObject data)  throws Exception {
        super(pKnapSack.ProblemName, PARAM_COUNT);
        this.data = data;
        this.loadStatus = Load();
    }

    private boolean Load()  throws Exception{
        System.out.println("----Params Data-----");
        try{
            //parametro 1
            if (!this.containsParam(pKnapSack.PARAM_ITERATIONS)) {
                //carregar o parametro para um dicionario de parametros
                this.Iterations = this.data.getInt(pKnapSack.PARAM_ITERATIONS);
                this.addParam(pKnapSack.PARAM_ITERATIONS, this.Iterations);
                //registar parametros obrigatório
                this.PARAM_ADDED++;
                System.out.println(pKnapSack.PARAM_ITERATIONS + "+:" + this.Iterations);
            }
            //parametro 2
            if (!this.containsParam(pKnapSack.PARAM_POPULATION_SIZE)) {
                this.popSize = this.data.getInt(pKnapSack.PARAM_POPULATION_SIZE);
                this.addParam(pKnapSack.PARAM_POPULATION_SIZE, this.popSize);
                //registar parametros obrigatório
                this.PARAM_ADDED++;
                System.out.println(pKnapSack.PARAM_POPULATION_SIZE + "+:" + this.popSize);
            }
            //parametro 3
            if (!this.containsParam(pKnapSack.PARAM_ALELLO_SIZE)) {
                this.alelloSize = this.data.getInt(pKnapSack.PARAM_ALELLO_SIZE);
                this.addParam(pKnapSack.PARAM_ALELLO_SIZE, this.alelloSize);
                //registar parametros obrigatório
                this.PARAM_ADDED++;
                System.out.println(pKnapSack.PARAM_ALELLO_SIZE + "+:" + this.alelloSize);
            }            
            //parametro 4
            if (!this.containsParam(pKnapSack.PARAM_BEST_FITNESS)) {
                this.bestFitness = this.data.getInt(pKnapSack.PARAM_BEST_FITNESS);
                this.addParam(pKnapSack.PARAM_BEST_FITNESS, this.bestFitness);
                //registar parametros obrigatório
                this.PARAM_ADDED++;
                System.out.println(pKnapSack.PARAM_BEST_FITNESS + "+:" + this.bestFitness);
            }
            //-------------------------Parametros especificos-----------------------------
            //parametro 5 lenght:int
            if (!this.containsParam(pKnapSack.PARAM_LENGHT)) {
                this.lenght = this.data.getInt(pKnapSack.PARAM_LENGHT);
                this.addParam(pKnapSack.PARAM_LENGHT, this.lenght);
                this.PARAM_ADDED++;
                System.out.println(pKnapSack.PARAM_LENGHT + ":" + this.lenght);
            }
            //parametro 6 penalty:int
            if (!this.containsParam(pKnapSack.PARAM_PENALTY)) {
                this.penalty = this.data.getInt(pKnapSack.PARAM_PENALTY);
                this.addParam(pKnapSack.PARAM_PENALTY, this.penalty);
                this.PARAM_ADDED++;
                System.out.println(pKnapSack.PARAM_PENALTY + "+:" + this.penalty);
            }
            //parametro 7 mode:int
            if (!this.containsParam(pKnapSack.PARAM_MODE_FUNCTION)) {
                this.mode = this.data.getInt(pKnapSack.PARAM_MODE_FUNCTION);
                this.addParam(pKnapSack.PARAM_MODE_FUNCTION, this.mode);
                this.PARAM_ADDED++;
                System.out.println(pKnapSack.PARAM_MODE_FUNCTION + "+:" + this.mode);
            }        
        }catch(Exception e){
            this.loadStatus=false;
            e.printStackTrace();
            throw e;            
        }
        //Verificar se todos os parametros foram carregados
        if (this.PARAM_ADDED >= pKnapSack.PARAM_REQUIRED) {
                //ArrayList<Item> values = new ArrayList<Item>();
                System.out.println("-----DataStart-----");
                //Enquanto for diferente que a tag de terminação de ficheiro vai adicionado

                JSONArray values = this.data.getJSONArray("data");
                ValorPeso = new int[this.lenght][2];
                JSONArray value;
                for (int i = 0; i < this.lenght; i++) {
                    //protected   final int       VALUE   = 0;
                    //protected   final int       WEIGHT  = 1;
                    value = values.getJSONArray(i);
                    ValorPeso[i][0] = value.getInt(1);
                    ValorPeso[i][1] = value.getInt(0);
                }
                
                //mochila = new Mochila(this.maxWeight, values, this.penalty);
                System.out.println("------DataEnd------");
                this.loadStatus = true;
                return true;
            }
            return false;
        }


    /**
     * Método que retorna o NOVO solver com os parametros carregados por o ficheiro.
     * @return 
     */
    @Override
    public Solver getNewSolver() {
        if(this.loadStatus==true){
            try {
                 this.LoadOperators(data);
            } catch (JSONException ex) {
                ex.printStackTrace();
                return null;
            }

            int __sizePopulation = this.popSize;
            int __sizeAllelo = this.alelloSize;
           
            
            Individual __prototypeIndividual=new genetics.algorithms.KnapSack(3 +" "+10+" 1 2 3 1 2 3", ModeFunction.PENALTY, penalty);

//            if(this.mode==0)
//                return null;
//            if(this.mode==1)    
//                __prototypeIndividual = new genetics.algorithms.KnapSack(lenght, this.ValorPeso, ModeFunction.PENALTY, penalty);
//            if(this.mode==2)    
//                __prototypeIndividual = new genetics.algorithms.KnapSack(lenght, this.ValorPeso, ModeFunction.PSEUDO_RANDOM, penalty);    
//            if(this.mode==3)    
//                __prototypeIndividual = new genetics.algorithms.KnapSack(lenght, this.ValorPeso, ModeFunction.RANDOM, penalty);                  
            
            int __iteractions = this.Iterations;
            double __bestFitness = (double) this.bestFitness;

            StopCriterion __stopCriterion = new StopCriterion(__iteractions, __bestFitness);        
            
            Solver solver = new Solver(__sizePopulation, __sizeAllelo, __prototypeIndividual, __stopCriterion, this.getOperators(), new GeneticEvents(PowerMaster.INTERVAL_PART, 1, 1));
            return solver;
        }else{
            System.out.println("Loader not loaded!?!?! :)");
            return null;
        }
    }

    public boolean getStatus() {
        return this.loadStatus;
    }

    public int getIterations() {
        return Iterations;
    }

}
