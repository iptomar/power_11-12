/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Loader;

import genetics.Solver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import operators.Operator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public abstract class Problem {

    /**
     * Variável que indica o número parametros previstos no problema
     */
    private final int NUM_PARAMS;
    /**
     * Variável que indica o número de parametros existentes actualmente no
     * problema
     */
    private int ACTUAL_NUM_PARAMS;
    /**
     * Nome do problema
     */
    private final String ProblemName;
    /**
     * Identificação do Problema (Task)
     */
    private int ProblemID;
    /**
     * Identificvação do cleinte que Originou o problema (Task)
     */
    private int ClientID;
    /**
     * Estrutura que contem todo os parametros adicionados. (HashTable)
     */
    private final Map<String, Object> Parms;
    /**
     * Estrura de operadores do Solver
     */
    private ArrayList<Operator> operators = new ArrayList<Operator>(4);     
    
    /**
     * Construtor do objecto problema
     * @param ProblemName Nome do problema
     * @param NUM_PARAMS Número de parametros que existem no problema
     */
    public Problem(String ProblemName, int NUM_PARAMS) {
        this.ProblemName = ProblemName;
        this.NUM_PARAMS = NUM_PARAMS;
        this.ACTUAL_NUM_PARAMS = 0;
        //Dicionário com os parametros dos dados
        Parms = new HashMap<String, Object>();
    }

    /**
     * Método que permite obter uma nova instacia do objecto solver
     *
     * @return
     */
    public abstract Solver getNewSolver();

    /**
     * Método para adicionar parametros ao objecto Problem
     *
     * @param param Identificado do parametro (String)
     * @param data Data do parametro (Objecto)
     */
    public void addParam(String param, Object data) {
        if (!Parms.containsKey(param)) {
            this.Parms.put(param, data);
            this.ACTUAL_NUM_PARAMS++;
        }
    }

    /**
     * Método que carrega os operadores para o solver
     * @param data Objecto do tipo json que contem toda informação dos operadores
     * @throws JSONException Em caso de erro é lançado uma excepção
     */
    public void LoadOperators(JSONObject data) throws JSONException{
            //Selection Loader
            JSONArray selector = data.getJSONArray("selection");
            if(selector.getString(0).equals("tournament")){
                System.out.println("selector:tournament -"+selector.getInt(1)+","+selector.getInt(2));
                operators.add( new operators.selections.Tournament(selector.getInt(1), selector.getInt(2)));
            }
            if(selector.getString(0).equals("sus")){
                System.out.println("selector:sus - "+selector.getInt(1));
                operators.add( new operators.selections.SUS(selector.getInt(1)));
            }       
            if(selector.getString(0).equals("roulette")){
                System.out.println("selector:roulette - "+selector.getInt(1));
                operators.add( new operators.selections.Roulette(selector.getInt(1)));
            } 
            
            //Recombination Loader
            JSONArray recombination = data.getJSONArray("recombination");
            if(recombination.getString(0).equals("crossover")){
                System.out.println("recombination:crossover");
                operators.add( new operators.recombinations.Crossover());
            }
            if(recombination.getString(0).equals("uniformcrossover")){
                System.out.println("recombination:uniformcrossover");
                operators.add( new  operators.recombinations.UniformCrossover());
            }       
  
            //Mutation Loader
            JSONArray mutation = data.getJSONArray("mutation");
            if(mutation.getString(0).equals("flipbit")){
                System.out.println("mutation:flipbit - "+mutation.getDouble(1));
                operators.add( new operators.mutation.Flipbit(mutation.getDouble(1)));
            }
            if(mutation.getString(0).equals("truncation")){
                System.out.println("mutation:truncation - "+mutation.getDouble(1));
//                operators.add( new  operators.replacements.Truncation(mutation.getInt(1)));
            }              
            
            //Replacements Loader
            JSONArray replacement = data.getJSONArray("replacement");
            if(replacement.getString(0).equals("tournament")){
                System.out.println("replacement:tournament - "+replacement.getInt(1)+","+replacement.getInt(2));
//                operators.add( new operators.replacements.Tournament(replacement.getInt(1),replacement.getInt(2)));
            }
            if(replacement.getString(0).equals("truncation")){
                System.out.println("replacement:truncation - "+replacement.getInt(1));
//                operators.add( new  operators.replacements.Truncation(replacement.getInt(1)));
            }           
        }
    
    
    /**
     * Método que verifica se existe um parametro na estrutura
     *
     * @param param Chave que identifica o parametro a ser verificado
     * @return True - Existe / False - Não existe
     */
    public boolean containsParam(String param) {
        return this.Parms.containsKey(param);
    }

    /**
     * Método que retorna o número de parametros adicionados ao problema
     *
     * @return
     */
    public int get_ACTUAL_NUM_PARAMS() {
        return ACTUAL_NUM_PARAMS;
    }

    /**
     * Método que permite procurar um parametro por a sua chave
     *
     * @param key Chave de pesquisa
     * @return Retorno do obejcto encontrado, caso não seja encontrado este
     * método retorna null
     */
    public Object getParms(String key) {
        return Parms.get(key);
    }

    /**
     * Método que permite ir buscar o array de operadores carregado no problema
     * @return Devolve um array list com os operadores carregados no problema
     */
    public ArrayList<Operator> getOperators() {
        return operators;
    }
    

    @Deprecated 
    public static Boolean VerInt(String[] linha) {
        Boolean res = false;
        for (int i = 1; i < linha.length; i++) {
            try {
                Integer.parseInt(linha[i]);
            } catch (Exception e) {
                System.out.println("Caracter encontrado: " + linha[i] + " erro: " + e);
                res = false;
                return res;
            }
            res = true;
        }

        return res;
    }

    public int getProblemID() {
        return ProblemID;
    }

    public void setClientID(int ClientID) {
        this.ClientID = ClientID;
    }

    public void setProblemID(int ProblemID) {
        this.ProblemID = ProblemID;
    }

    public int getClientID() {
        return ClientID;
    }
}