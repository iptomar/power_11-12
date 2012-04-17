/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Loader;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Loader {
    
    public static Problem Load(String dataIn) {
        //remover todos os caracteres estranhos
        dataIn = dataIn.replace("\r", "");
        //Obter todas as linhas do documento
        String[] linhas = dataIn.split("\n");
        //Leitura da primeira linha para verificar qual o tipo de problema
        String[] primeiraLinha = linhas[0].split(";");
        //Verificação do tipo de problema (Linha 1)
        if (primeiraLinha[0].equals(OnesMax.ProblemName)) {
            System.out.println("##### New Ones Max Problem #####");
            //Problema do tipo OnesMax
            OnesMax oneMax = new OnesMax(linhas);
            //Verificar se o objecto foi carregado com sucesso com a informação passada
            if(oneMax.getStatus()){
                //Atribnuição de Identificadores globais
                oneMax.setProblemID(Integer.parseInt(primeiraLinha[1]));
                oneMax.setClientID(Integer.parseInt(primeiraLinha[2]));
                System.out.println("##### OnesMax Problem Ok #####");
                return (Problem)oneMax;
            }
            System.out.println("##### OnesMax NOT LOADED #####");
        }
        if (primeiraLinha[0].equals(KnapSack.ProblemName)) {
            System.out.println("##### New KnapSack Problem #####");
            //Problema do tipo OnesMax
            KnapSack knapSack = new KnapSack(linhas);
            //Verificar se o objecto foi carregado com sucesso com a informação passada
            if(knapSack.getStatus()){
                //Atribnuição de Identificadores globais
                knapSack.setProblemID(Integer.parseInt(primeiraLinha[1]));
                knapSack.setClientID(Integer.parseInt(primeiraLinha[2]));
                System.out.println("##### KnapSack Problem Ok #####");
                return (Problem)knapSack;
            }
            System.out.println("##### KnapSack NOT LOADED #####");
        }        
        
        return null;
    }
}