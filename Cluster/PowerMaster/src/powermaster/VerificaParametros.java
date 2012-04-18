/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

/**
 *
 * @author Miranda
 */
public class VerificaParametros {

    public VerificaParametros() {
    }

    
    //VERIFICA SE OS PARAMETROS SAO NUMEROS INTEIROS
    public static Boolean VerInt(String[] linha) {
        Boolean res = false;
        for (int i = 1; i < linha.length; i++) {
            try {
                Integer.parseInt(linha[i]);
            } catch (Exception e) {
                System.out.println("Caracter encontrado: " + linha[i] + " erro: " + e);
                res = false;
                return res;
            }
            res = true;
        }

        return res;
    }
}
