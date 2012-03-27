package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rodrigo Marinheiro
 */
public class Conectar {

    public static Connection coneccao;
    public static Statement comando;
    
    
    public static void Ligar() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            coneccao = DriverManager.getConnection("jdbc:mysql://localhost:3306/localidades", "root", "");
            comando = coneccao.createStatement();
    }
    

}
