/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Loader;

import genetics.Solver;
import java.util.HashMap;
import java.util.Map;

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
     * Variável que indica o número de parametros existentes actualmente no problema
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

    public Problem(String ProblemName, int NUM_PARAMS) {
        this.ProblemName = ProblemName;
        this.NUM_PARAMS = NUM_PARAMS;
        this.ACTUAL_NUM_PARAMS = 0;
        Parms = new HashMap<String, Object>();
    }

    /**
     * Método que permite obter uma nova instacia do objecto solver
     * @return 
     */
    public abstract Solver getNewSolver();

    /**
     * Método para adicionar parametros ao objecto Problem
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
     * Método que retorna o número de parametros adicionados ao problema
     * @return 
     */
    public int get_ACTUAL_NUM_PARAMS() {
        return ACTUAL_NUM_PARAMS;
    }

    /**
     * Método que permite procurar um parametro por a sua chave
     * @param key Chave de pesquisa
     * @return Retorno do obejcto encontrado, caso não seja encontrado este método retorna null
     */
    public Object getParms(String key) {
        return Parms.get(key);
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
