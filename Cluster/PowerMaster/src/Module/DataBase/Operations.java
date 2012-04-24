/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.DataBase;

/**
 *
 * @author Miranda
 */
public class Operations {

    static Database bd;

    public Operations(Database bd) {
        this.bd = bd;
    }

    public static boolean InserirIteracoes(String threadId, int itera, int idClient, int idProblem, double best, double average, int numBest, String attributes, double deviation, int type, double variance) {
        boolean inserir = false;
        try {
//            String[] parametros = new String[2];
//            parametros[0] = threadId;
//            parametros[1] = attributes;

            //VERIFICA SE OS PARAMETROS SAO INTEIROS
//            if(VerInt(parametros)){

            //INSERE NA BD E SE NAO INSERIR DA FALSE
            if (bd.ExecuteNonQuery("INSERT INTO tblIterations VALUES (" + threadId + "," + itera + "," + idClient + "," + idProblem + ",NOW()," + best + "," + average + "," + numBest + ",'" + attributes.toString() + "'," + deviation + "," + type + "," + variance + ");")) {
                inserir = true;
            } else {
                System.out.println("ERRO A INSERIR NA BD1");
                inserir = false;
            }
//             }else{
//                 return false;
//             }
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            inserir = false;
        }
        return inserir;
    }

    public static boolean InserirResult(int itera, int idClient, int idProblem, double globalAverage, double globalDeviation, double globalBest, int globalNumBest, double variance) {
        boolean inserir = false;
        try {
//             //VERIFICA SE OS PARAMETROS SAO INTEIROS


            //INSERE NA BD E SE NAO INSERIR DA FALSE
            if (bd.ExecuteNonQuery("INSERT INTO tblResults VALUES ("+ itera + "," + idClient + "," + idProblem + "," + globalAverage + "," + globalDeviation + "," + globalBest + "," + globalNumBest + "," + variance + ");")) {
                inserir = true;
            } else {
                System.out.println("ERRO A INSERIR NA BD2");
                inserir = false;
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            inserir = false;
            return inserir;
        }
        return inserir;
    }

    //VERIFICA SE OS PARAMETROS SAO NUMEROS INTEIROS
    public static Boolean VerInt(String[] linha) {
//        Boolean res = false;
//        for (int i = 0; i < linha.length; i++) {
//            try {
//                Integer.parseInt(linha[i]);
//            } catch (Exception e) {
//                System.out.println("Caracter encontrado: " + linha[i] + " erro: " + e);
//                res = false;
//                return res;
//            }
//            res = true;
//        }
//        return res;
        return false;
    }
}
