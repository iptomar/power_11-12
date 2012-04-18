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

    public Operations(){}
    
    
    public boolean InserirIteracoes(String threadId,String itera,String idClient,String idProblem,String best,String average,String numBest,String attributes,String deviation,String type){
        boolean inserir =false;
        try {
            String[] parametros = new String[10];
            parametros[0] = threadId;
            parametros[1] = itera;
            parametros[2] = idClient;
            parametros[3] = idProblem;
            parametros[4] = best;
            parametros[5] = average;
            parametros[6] = numBest;
            parametros[7] = attributes;
            parametros[8] = deviation;
            parametros[9] = type;
            //VERIFICA SE OS PARAMETROS SAO INTEIROS
            if(VerInt(parametros)){
                
                Database bd = new Database();
                //INSERE NA BD E SE NAO INSERIR DA FALSE
                if(bd.ExecuteNonQuery("INSERT INTO tblIterations VALUES "+threadId+","+itera+","+idClient+","+idProblem+",NOW(),"+best+","+average+","+numBest+","+attributes+","+deviation+","+type+"")){
                    inserir=true;
                }else{
                    System.out.println("ERRO A INSERIR NA BD");
                    inserir=false;
                }
             }else{
                 return false;
             }
        } catch (Exception e) {
            System.out.println("ERRO: "+ e);
            inserir=false;
        }
        return inserir;
    }
    
    public boolean InserirResult(String itera,String idClient,String idProblem,String globalAverage,String globalDeviation,String globalBest,String globalNumBest){
        boolean inserir =false;
        try {
            String[] parametros = new String[7];
            parametros[0] = itera;
            parametros[1] = idClient;
            parametros[2] = idProblem;
            parametros[3] = globalAverage;
            parametros[4] = globalDeviation;
            parametros[5] = globalBest;
            parametros[6] = globalNumBest;
             //VERIFICA SE OS PARAMETROS SAO INTEIROS
            if(VerInt(parametros)){
                Database bd = new Database();
                //INSERE NA BD E SE NAO INSERIR DA FALSE
                if(bd.ExecuteNonQuery("INSERT INTO tblResult VALUES "+itera+","+idClient+","+idProblem+","+globalAverage+","+globalDeviation+","+globalBest+","+globalNumBest+"")){
                    inserir=true;
                }else{
                    System.out.println("ERRO A INSERIR NA BD");
                    inserir=false;
                }
            
           
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println("ERRO: "+ e);
            inserir=false;
            return inserir;
        }
        return inserir; 
    }
    
    
    
     //VERIFICA SE OS PARAMETROS SAO NUMEROS INTEIROS
    public static Boolean VerInt(String[] linha) {
        Boolean res = false;
        for (int i = 0; i < linha.length; i++) {
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
