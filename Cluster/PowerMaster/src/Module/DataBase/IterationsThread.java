/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.DataBase;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class IterationsThread extends Thread {
    
    /**
     * Variável que contem a a ligação com o servido MySQL
     */
    public Connection Connection;
    /**
     * Variável que contem um comando a ser executado no servidor MySQL
     */
    private Statement Command;
    /**
     * Variável que contem o Username do servidor MySQL
     */
    private String username;
    /**
     * Variável que contem a Password do servidor de MySQL
     */
    private String password;
    /**
     * Variável o endereço de IP do servidor
     */
    private String ipAddress;
    
    public IterationsThread(String username, String password, String ipAddress){
        
    }
    
}
