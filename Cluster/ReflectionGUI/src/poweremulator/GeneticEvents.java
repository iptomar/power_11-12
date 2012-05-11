/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poweremulator;


import genetics.Population;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import statistics.Statistics;
import utils.EventsSolver;
import utils.PopulationUtils;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class GeneticEvents implements EventsSolver {

    int Interval = 100;

    JTextArea text;

    public GeneticEvents(JTextArea text) {
        this.text=text;
    }


    @Override
    public void EventStartSolver() {

        //System.out.println("Thread["+Thread.currentThread().getName()+"]: Sovler started");
//        try {
//            Aplication.nodeJS.Emit("startrun", "1", "[[");
//        } catch (Exception ex) {
//            Logger.getLogger(GeneticEvents.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public void EventIteraction(int i, Population pltn) {
        // De 10 em 10 vai mostrar estatistica
        Statistics statistics = new Statistics(pltn);
        if ((i % 10) == 0) {
            System.out.println("Thread["+Thread.currentThread().getName()+"]:");
            System.out.println("--------------------------------------------------");
            System.out.println("Iteration:" + i);
            System.out.println("Variance: " + statistics.getVarianciaPopulation().doubleValue());
            System.out.println("Average:" + statistics.getMediaFitnessPopulation().doubleValue());
            System.out.println("Deviation: " + statistics.getDesvioPadraoPopulation().doubleValue());
            System.out.println("BEST: " + PopulationUtils.getBestFitness(pltn));
    }
            if ((i % 5) == 0) {        
            EmuladorEcran.Escrever("Thread["+Thread.currentThread().getName()+"]:");
            EmuladorEcran.Escrever("--------------------------------------------------");
            EmuladorEcran.Escrever("Iteration:" + i);
            EmuladorEcran.Escrever("Variance: " + statistics.getVarianciaPopulation().doubleValue());
            EmuladorEcran.Escrever("Average:" + statistics.getMediaFitnessPopulation().doubleValue());
            EmuladorEcran.Escrever("Deviation: " + statistics.getDesvioPadraoPopulation().doubleValue());
            EmuladorEcran.Escrever("BEST: " + PopulationUtils.getBestFitness(pltn)+"\n");
            
            
            }
//            System.out.println("");
//        }

//        if (i == 0) {
//            db.InserirIteracoes(Thread.currentThread().getName().toString(), i, this.idClient, this.idProblem, PopulationUtils.getBestFitness(pltn), statistics.getMediaFitnessPopulation().doubleValue(), PopulationUtils.getNumberIndividualsWithBestFitness(pltn), PopulationUtils.getHallOfFame(pltn, 1).toString(), statistics.getDesvioPadraoPopulation(), 0, statistics.getVarianciaPopulation());
//            //            System.out.println("Thread["+Thread.currentThread().getName()+"]First Iteration inserted[" + i + "]:" + aux);
//        } else {
//            db.InserirIteracoes(Thread.currentThread().getName().toString(), i, this.idClient, this.idProblem, PopulationUtils.getBestFitness(pltn), statistics.getMediaFitnessPopulation().doubleValue(), PopulationUtils.getNumberIndividualsWithBestFitness(pltn), PopulationUtils.getHallOfFame(pltn, 1).toString(), statistics.getDesvioPadraoPopulation(), 1, statistics.getVarianciaPopulation());
//        } 


    }

    @Override
    public void EventFinishSolver(int i, Population pltn) {
        Statistics statistics = new Statistics(pltn);

  
                System.out.println("Thread["+Thread.currentThread().getName()+"]Last Iteration inserted[" + i + "]:" );
                
                System.out.println("Thread["+Thread.currentThread().getName()+"]: Solver ended");
                System.out.println("--------------------------------------------------");
                System.out.println("Iteration:" + i);
                System.out.println("Variance: " + statistics.getVarianciaPopulation().doubleValue());
                System.out.println("Average:" + statistics.getMediaFitnessPopulation().doubleValue());
                System.out.println("Deviation: " + statistics.getDesvioPadraoPopulation().doubleValue());
                System.out.println("--------------------------------------------------");
                System.out.println("");
        
                System.out.println("Hall of Fame - Top 5");
                System.out.println(PopulationUtils.getHallOfFame(pltn, 5));

                
                EmuladorEcran.Escrever("Thread["+Thread.currentThread().getName()+"]: Solver ended");
                EmuladorEcran.Escrever("--------------------------------------------------");
                EmuladorEcran.Escrever("Iteration:" + i);
                EmuladorEcran.Escrever("Variance: " + statistics.getVarianciaPopulation().doubleValue());
                EmuladorEcran.Escrever("Average:" + statistics.getMediaFitnessPopulation().doubleValue());
                EmuladorEcran.Escrever("Deviation: " + statistics.getDesvioPadraoPopulation().doubleValue());
                EmuladorEcran.Escrever("--------------------------------------------------\n");
                EmuladorEcran.Escrever("Hall of Fame - Top 5"); 
                EmuladorEcran.Escrever(PopulationUtils.getHallOfFame(pltn, 5).toString());

      
      
    }
}
