/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class AbstractAplication {
    /**
     * Variável que contem o estado do modulo
     */
    public boolean AplicationStatus;
    /**
     * Variável que contem o nome do modulo
     */
    public String AplicationName;
    
    /**
     * Construtor por defeito
     */
    public AbstractAplication(){
        //Atribuição de um nome por defeito ao módulo
        this.AplicationName = "Abstract Aplication";
    }
    
    /**
     * Construtor com atrbuição do nome do módulo
     * @param AplicationName Nome do módulo
     */
    public AbstractAplication(String AplicationName){
        //Atribuição do ao módulo
        this.AplicationName = AplicationName;
    }
    
    /**
     * Selector da varável AplicationName que contem o nome do módulo
     * @return Nome do módulo
     */
    public String getAplicationName(){
        return this.AplicationName;
    }
    
     /**
     * Selector da varável AplicationStatus que contem o estado do módulo
     * @return Estado do módulo
     */   
    public boolean getAplicationStatus(){
        return this.AplicationStatus;
    }
    
    
}
