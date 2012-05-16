/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebHTTP;

import genetics.GenericSolver;
import java.lang.reflect.InvocationTargetException;
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
        String problemParms = problem.getString(1);
        String problemStop = problem.getString(2);

        problem = input.getJSONArray("mutation");
        String mutationName = problem.getString(0);
        String mutationParms = problem.getString(1);

//                                problem = input.getJSONArray("operator");
//                                String operatorName = problem.getString(0);
//                                String operatorParms = problem.getString(1);     

        problem = input.getJSONArray("recombination");
        String recombinationName = problem.getString(0);
        String recombinationParms = problem.getString(1);

        problem = input.getJSONArray("replacement");
        String replacementName = problem.getString(0);
        String replacementParms = problem.getString(1);

        problem = input.getJSONArray("selection");
        String selectionName = problem.getString(0);
        String selectionParms = problem.getString(1);

        solver.setParameters(problemParms);
        solver.SetSelection(selectionName + " " + selectionParms);
        solver.SetMutation(mutationName + " " + mutationParms);
        solver.SetReplacement(replacementName + " " + replacementParms);
        solver.SetRecombination(recombinationName + " " + recombinationParms);
        solver.SetStopCrit(problemStop);

        return solver;
    }
}
