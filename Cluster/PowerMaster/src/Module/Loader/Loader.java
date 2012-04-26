/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Loader;

import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Loader {
     static String[] linhas; 
     
    public static Problem Load(String dataIn) throws Exception {
        JSONObject data = new JSONObject(dataIn);
        if (data.getString("type").equals(pOnesMax.ProblemName)) {
            System.out.println("##### New Ones Max Problem #####");
            //Problema do tipo OnesMax
            pOnesMax oneMax = new pOnesMax(data);
            //Verificar se o objecto foi carregado com sucesso com a informação passada
            if(oneMax.getStatus()){
                //Atribnuição de Identificadores globais
                 oneMax.setProblemID(data.getInt("id"));
                oneMax.setClientID(data.getInt("client"));
                System.out.println("Cliente:"+data.getInt("client"));
                System.out.println("id:"+data.getInt("id"));
//                oneMax.setProblemID(1000);
//                oneMax.setClientID(1000);                
                System.out.println("##### OnesMax Problem Ok #####");
                return (Problem)oneMax;
            }
            System.out.println("##### OnesMax NOT LOADED #####");
        }
        if (data.getString("type").equals(pKnapSack.ProblemName)) {
            System.out.println("##### New KnapSack Problem #####");
            //Problema do tipo OnesMax
            pKnapSack knapSack = new pKnapSack(data);
            //Verificar se o objecto foi carregado com sucesso com a informação passada
            if(knapSack.getStatus()){
                //Atribnuição de Identificadores globais
                knapSack.setProblemID(data.getInt("id"));
                knapSack.setClientID(data.getInt("client"));
                System.out.println("Cliente:"+data.getInt("client"));
                System.out.println("id:"+data.getInt("id"));
//                knapSack.setProblemID(1000);
//                knapSack.setClientID(1000);
                System.out.println("##### KnapSack Problem Ok #####");
                return (Problem)knapSack;
            }
            System.out.println("##### KnapSack NOT LOADED #####");
        }        
        
        return null;
    }

}