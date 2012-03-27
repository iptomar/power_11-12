/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marinheiro
 */
public class Testar extends Conectar {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        Ligar();
        
        ResultSet resultados = comando.executeQuery("SELECT * FROM distritos");
        
        resultados.last(); //Vai para a ultima linha
        int num = resultados.getRow(); //Retorna o numero da linha, ou seja, vai dar o numero de linhas
        
        System.out.println("Numero registos: " + num);
        
        resultados.first(); // volta ao primeiro registo
        
        for (int i = 0; i < num; i++) {
            
            System.out.println(resultados.getString("nome"));            
            resultados.next();
            
        }
    }
    
}
