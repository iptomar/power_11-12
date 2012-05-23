/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebHTTP;

import genetics.GenericSolver;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import reflection.GeneticLoader;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class SolverCreator {
    public static GenericSolver CreateSolver(JSONObject input) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, JSONException{
        GeneticLoader gl = new GeneticLoader();
        GenericSolver solver = gl.getSolver();
       
        JSONArray problem = input.getJSONArray("algorithm");
        String problemName = problem.getString(0);
        System.out.println("Problema:"+problemName);
        String problemParms = problem.getString(1);
        System.out.println("Parametros Solver:"+problemParms);
        String problemStop = problem.getString(2);
        System.out.println("Critétios de paragem:"+problemStop+"\n\n");

        String paramTSP = null;
        if(problemName.contains(".TSP")){
            JSONArray dataTSP = input.getJSONArray("TSP");
            paramTSP = dataTSP.getString(0);
            System.out.println("**TSP ENCONTRADO** \n Dados:"+paramTSP+"\n\n");
        }        
        
        problem = input.getJSONArray("mutation");
        String mutationName = problem.getString(0);
        System.out.println("Mutação:"+mutationName);
        String mutationParms = problem.getString(1);
        System.out.println("Parametros de Mutação:"+mutationParms+"\n\n");

//                                problem = input.getJSONArray("operator");
//                                String operatorName = problem.getString(0);
//                                String operatorParms = problem.getString(1);     

        problem = input.getJSONArray("recombination");
        String recombinationName = problem.getString(0);
        System.out.println("Recominação:"+recombinationName);
        String recombinationParms = problem.getString(1);
        System.out.println("Parametros de Recombinação:"+recombinationName+"\n\n");

        problem = input.getJSONArray("replacement");
        String replacementName = problem.getString(0);
        System.out.println("Replacement:"+replacementName);
        String replacementParms = problem.getString(1);
        System.out.println("Parametros de Replacement:"+replacementParms+"\n\n");
        
        problem = input.getJSONArray("selection");
        String selectionName = problem.getString(0);
        System.out.println("Selection:"+selectionName);
        String selectionParms = problem.getString(1);
        System.out.println("Parametros de Replacement:"+selectionParms+"\n\n");

        System.out.println("setParameters:"+solver.setParameters(problemParms));
        System.out.println("SetSelection:"+solver.SetSelection(selectionName + " " + selectionParms));
        System.out.println("SetMutation:"+solver.SetMutation(mutationName + " " + mutationParms));        
        System.out.println("SetReplacement:"+solver.SetReplacement(replacementName + " " + replacementParms));        
        System.out.println("SetRecombination:"+solver.SetRecombination(recombinationName + " " + recombinationParms));        
        System.out.println("SetStopCrit:"+solver.SetStopCrit(problemStop));  

        System.out.println("\n\n");

        if(paramTSP!=null){
            System.out.println("SetTSPProbl:"+solver.SetTSPProbl(paramTSP)+"\n\n");
        }
            try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SolverCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return solver;
    }
}
