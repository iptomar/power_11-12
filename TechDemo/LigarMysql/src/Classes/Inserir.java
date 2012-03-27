/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cardoso
 */
public class Inserir extends Conectar{
    public void Insercao() throws SQLException, ClassNotFoundException {
        try{
            Ligar();

            ResultSet resultados = comando.executeQuery("INSERT INTO distritos VALUES ()");

        } catch (SQLException e) {
            System.out.println("Impossivel inserir registo!");
        }
        
    }
}
