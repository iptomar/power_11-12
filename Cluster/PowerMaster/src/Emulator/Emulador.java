/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Emulator;



import Module.Loader.pKnapSack;
import Module.Loader.pOnesMax;
import Module.WebHTTP.WebFileDownloader;
import java.net.URL;


/**
 *
 * @author Miranda
 */
public class Emulador {
    static String[] linhas;
    String resultado;
    public Emulador(String caminho)
    {
        this.resultado=caminho;
        try {
            resultado = WebFileDownloader.Download(new URL(caminho));
            //EmuladorEcran.TextArea.append(resultado);
        } catch (Exception ex) {
            System.out.println("ERRO1: " + ex);
             EmuladorEcran.Escrever("\nERRO1: " + ex);
        }
        
    }
    
    public Emulador(){}
    
    
    public Boolean Carregar(){
        //if(Problema(resultado)){
               return true; 
        //}else{
        //       System.out.println("Algo correu mal");
        //       return false;
        //}
    }

    
    private Boolean Problema(String res){
        
        Boolean entrou=false;
        try{
        //remover todos os caracteres estranhos
        res = res.replace("\r", "");
        //Obter todas as linhas do documento
        linhas = res.split("\n");
        //Leitura da primeira linha para verificar qual o tipo de problema
        String[] primeiraLinha = linhas[0].split(";");
        String[] parmsData = linhas[1].split(";");
       
        //E OnesMax
        if (primeiraLinha[0].equals(pOnesMax.ProblemName)){
            EmuladorEcran.Escrever("Problema: "+primeiraLinha[0]);
        }
        //E Mochila
        else if(primeiraLinha[0].equals(pKnapSack.ProblemName)){
             EmuladorEcran.Escrever("Problema: "+primeiraLinha[0]);
             entrou=true;
        }
        }catch(Exception e){
            System.out.println("ERRO2: "+e);
             EmuladorEcran.Escrever("\nERRO2: "+e);
            return false;
        }
        return entrou;
    }
    
    
}
