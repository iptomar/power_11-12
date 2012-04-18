/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Loader;

import genetics.Solver;
import powermaster.GeneticEvents;
import powermaster.PowerMaster;
import powermaster.VerificaParametros;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class OnesMax extends Problem {

    public static final String ProblemName = "OneMax";
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
     * Número de parametros que são necessário para que o problema OneMax seja corrido
     */
    private static int PARAM_REQUIRED = 4;//Número de parametros obrigatórios
    private int PARAM_ADDED = 0;//Número de parametros adicionados obrigatórios
    private static int PARAM_COUNT = 4;//Número de parametros existentes no problema
    /**
     * Referência para todas as linhas existentes no documento
     */
    private String data[];
    /**
     * Variável que indica o estado do loader
     */
    private boolean loadStatus;

    /**
     * Construtor do problema OnesMax
     * @param data Informação do problema a ser carregado
     */
    public OnesMax(String data[]) {
        super(OnesMax.ProblemName, PARAM_REQUIRED);
        this.data = data;
        this.loadStatus = Load();
    }

    private boolean Load() {
        //Ler a segunda linha que contem todos os parametros de arranque
        String[] parmsData = data[1].split(";");
        //Percorrer todos os parametros
        for (int i = 0; i < parmsData.length; i++) {
            //Separar o identificado do valor (=)
            String[] param = parmsData[i].split("=");
            //Verificação de parametros
            //parametro 1
            Boolean verInt=Problem.VerInt(param);
            
           if(verInt){
            if (param[0].equals(OnesMax.PARAM_ITERATIONS) && !this.containsParam(OnesMax.PARAM_ITERATIONS)) {
                //carregar o parametro para um dicionario de parametros
                this.Iterations = Integer.parseInt(param[1]);
                this.addParam(OnesMax.PARAM_ITERATIONS, this.Iterations);
                //registar parametros obrigatório
                this.PARAM_ADDED++;
                System.out.println(OnesMax.PARAM_ITERATIONS+"+:"+this.PARAM_ITERATIONS);
                continue;
            }
            //parametro 2
            if (param[0].equals(OnesMax.PARAM_POPULATION_SIZE) && !this.containsParam(OnesMax.PARAM_POPULATION_SIZE)) {
                this.popSize = Integer.parseInt(param[1]);
                this.addParam(OnesMax.PARAM_POPULATION_SIZE, this.popSize);
                //registar parametros obrigatório
                this.PARAM_ADDED++;               
                System.out.println(OnesMax.PARAM_POPULATION_SIZE+"+:"+this.popSize);
                continue;
            }
            //parametro 3
            if (param[0].equals(OnesMax.PARAM_ALELLO_SIZE) && !this.containsParam(OnesMax.PARAM_ALELLO_SIZE)) {
                this.alelloSize = Integer.parseInt(param[1]);
                this.addParam(OnesMax.PARAM_ALELLO_SIZE, this.alelloSize);
                //registar parametros obrigatório
                this.PARAM_ADDED++;              
                System.out.println(OnesMax.PARAM_ALELLO_SIZE+"+:"+this.alelloSize);
                continue;
            }
            //parametro 4
            if (param[0].equals(OnesMax.PARAM_BEST_FITNESS) && !this.containsParam(OnesMax.PARAM_BEST_FITNESS)) {
                this.bestFitness = Integer.parseInt(param[1]);
                this.addParam(OnesMax.PARAM_BEST_FITNESS, this.bestFitness);
                //registar parametros obrigatório
                this.PARAM_ADDED++;     
                System.out.println(OnesMax.PARAM_ALELLO_SIZE+"+:"+this.bestFitness);
                continue;
            }
          }else{
               return false;
           }
        }
       
        //Verificar se todos os parametros foram carregados
        if (this.PARAM_ADDED == OnesMax.PARAM_REQUIRED) {
            //Ler dados (restantes Linhas)
            //Neste caso não existe a necessidade de carregar parametros
            
            System.out.println("#### NO DATA ####");
            
            //Ler linha 2 para ver se começa com <DATASTART>
            //LER TODOS os valores por LINHA (com um while)
            //Colocar todos os valores lidos numa estrutura de dados (Unica para cada problema)
            //Este ciclo para quando chegar a tag <DATAEND>
            //O restante texto é rejeitado
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
        return new Solver(popSize, alelloSize, new genetics.OnesMax(), Iterations, bestFitness, new GeneticEvents(PowerMaster.INTERVAL_PART));
    }

    public boolean getStatus() {
        return this.loadStatus;
    }

    public int getIterations() {
        return Iterations;
    }

    public int getAlelloSize() {
        return alelloSize;
    }

    public int getBestFitness() {
        return bestFitness;
    }

    public int getPopSize() {
        return popSize;
    }
}
