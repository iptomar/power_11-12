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
public class Apagar extends Conectar {

    public void Apaga() throws SQLException, ClassNotFoundException {
        try {
            Ligar();
            ResultSet resultados = comando.executeQuery("DELETE FROM distritos WHERE condição");
        } catch (SQLException e) {
            System.out.println("Impossível apagar dados!");
        }

    }
}
