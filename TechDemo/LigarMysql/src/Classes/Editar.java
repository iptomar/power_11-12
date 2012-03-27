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
public class Editar extends Conectar{
     public void Edicao() throws SQLException, ClassNotFoundException {
         try{
            Ligar();

            ResultSet resultados = comando.executeQuery("UPDATE distritos SET comando='novo_valor' WHERE condição");

        } catch (SQLException e) {
            System.out.println("Impossivel editar registo!");
        }
     }
}
