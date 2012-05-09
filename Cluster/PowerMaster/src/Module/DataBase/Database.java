package Module.DataBase;

import Module.AbstractAplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo Marinheiro
 */
public class Database extends AbstractAplication {

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
    private String Database;

    //public static String ModuleName = "Database Module";
    /**
     * Construtor do objecto Database
     * Este objecto permite criar uma ligação com um servidor de MySQL
     * @param user Nome de utilizador do servidor de MySQL
     * @param password Password do utilizador do servidor de MySQL
     * @param address  Endereço publico do servidor de MySQL
     */
    public Database(String user, String password, String address, String database) {
        //Invocar o constutor da classe abstracta
        super("Database Module");

        //Atribuição de balores
        this.username = user;
        this.password = password;
        this.ipAddress = address;
        this.Database = database;

        Connect();
    }

    @Override
    public boolean getAplicationStatus() {
        return super.getAplicationStatus();
    }

    /**
     * Método interno ao objecto para a inicialização da conecção ao MySQL 
     * e inicialização de variáveis
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection = DriverManager.getConnection("jdbc:mysql://" + this.ipAddress + ":3306/" + this.Database, this.username, this.password);
            Command = Connection.createStatement();
            //Se chegar aqui o módulo foi inicializado correctamente
            this.AplicationStatus = true;
        } catch (Exception e) {
            //Se chegar a interrupção a iniciaçização do módulo foi comprometida
            this.AplicationStatus = false;
            System.out.println("Database connection failed. Database.Connect");
            e.printStackTrace();
        }
    }

    public int ExecuteCountQuery(int period, int idClient, int idProblem) throws SQLException {
        int count = 0;
        try {
            ResultSet rs = this.Command.executeQuery("SELECT * FROM tblIterations WHERE itera='" + period + "' AND idClient='" + idClient + "' AND idProblem='" + idProblem + "';");
            rs.last();
            count = rs.getRow();
            rs.close();
        } catch (Exception e) {

            if (Connection.isClosed()) {
                System.out.println("Connection Lost Database.CountQuery");
                Connect();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                return ExecuteCountQuery(period, idClient, idProblem);
            }
            e.printStackTrace();
        }
        return count;
    }

    public boolean ExecuteMedia(int period, int idClient, int idProblem) throws SQLException, Exception {
        boolean erro = false;
        try {
            ResultSet rs = this.Command.executeQuery("SELECT AVG(average) AS mediaAverage, MAX(best) AS best, AVG(deviation) AS deviation, MAX(numBest) AS numBest, AVG(variance) AS variance FROM tblIterations WHERE itera='" + period + "' AND idClient='" + idClient + "' AND idProblem='" + idProblem + "';");
            rs.first();
            String a1 = rs.getString("mediaAverage").toString();
            String a2 = rs.getString("deviation").toString();
            String a3 = rs.getString("best").toString();
            String a4 = rs.getString("numBest").toString();
            String a5 = rs.getString("variance").toString();
            rs.close();
            
            //ResultSet rs2 = this.Command.executeQuery("Select COUNT(best) AS numBest  FROM tblIterations WHERE best='"+ 60 +"' AND idClient='"+idClient+"' AND idProblem='"+idProblem+"';");
            ResultSet rs2 = this.Command.executeQuery("Select COUNT(*) AS numBest  FROM tblIterations WHERE best=" + a4 + " AND idClient=" + idClient + " AND idProblem=" + idProblem + " AND itera=" + period + ";");
            rs2.first();
            //rs2.close();
            erro = this.ExecuteNonQuery("INSERT INTO tblResults VALUES (" + period + "," + idClient + "," + idProblem + "," + a1 + "," + a2 + "," + a3 + "," + rs2.getString("numBest").toString() + "," + a5 + ");");
            rs2.close();            //erro = this.ExecuteNonQuery("INSERT INTO tblResults VALUES (" + period + "," + idClient + "," + idProblem + "," + rs.getString("mediaAverage").toString() + "," + rs.getString("deviation").toString() + "," + rs.getString("best").toString() + "," + rs.getString("numBest") + "," + rs.getString("variance") + ");");
            return erro;
        } catch (Exception e) {
            if (Connection.isClosed()) {
                System.out.println("Connection Lost Database.CountQuery");
                Connect();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                return ExecuteMedia(period, idClient, idProblem);
            }
            e.printStackTrace();
        }
        return erro;
    }

    /**
     * Método para executar uma query sem retorno no servidor MySQL
     * @param cmd Comando a ser executado no MySQL
     * @return True -> Comando executado com sucesso; False -> Ocorreu um erro na execução do comando
     * @throws SQLException 
     */
    public boolean ExecuteNonQuery(String cmd) throws SQLException {
        boolean erro = false;
        try {
            erro = Command.execute(cmd);
        } catch (SQLException e) {

            if (Connection.isClosed()) {
                System.out.println("Connection Lost Database.ExecuteNonQuery");
                Connect();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                return ExecuteNonQuery(cmd);
            }
            e.printStackTrace();
        }
        return erro;
    }

    public boolean InserirResult(int itera, int idClient, int idProblem, double globalAverage, double globalDeviation, double globalBest, int globalNumBest, double variance) throws SQLException {
        boolean erro = false;
        try {
            erro = this.ExecuteNonQuery("INSERT INTO tblResults VALUES (" + itera + "," + idClient + "," + idProblem + "," + globalAverage + "," + globalDeviation + "," + globalBest + "," + globalNumBest + "," + variance + ");");
        } catch (Exception e) {

            if (Connection.isClosed()) {
                System.out.println("Connection Lost Database.InserirResult");
                Connect();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                return InserirResult(itera, idClient, idProblem, globalAverage, globalDeviation, globalBest, globalNumBest, variance);
            }
            e.printStackTrace();
        }
        return erro;
    }

    public boolean InserirIteracoes(String threadId, int itera, int idClient, int idProblem, double best, double average, int numBest, String attributes, double deviation, int type, double variance) throws SQLException {
        boolean erro = false;
        try {
            this.ExecuteNonQuery("INSERT INTO tblIterations VALUES (" + threadId + "," + itera + "," + idClient + "," + idProblem + ",NOW()," + best + "," + average + "," + numBest + ",'" + attributes.toString() + "'," + deviation + "," + type + "," + variance + ");");
        } catch (Exception e) {
            if (Connection.isClosed()) {
                System.out.println("Connection Lost Database.InserirResult");
                Connect();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                return InserirIteracoes(threadId, itera, idClient, idProblem, best, average, numBest, attributes, deviation, type, variance);
            }
            e.printStackTrace();
        }
        return erro;
    }
}
