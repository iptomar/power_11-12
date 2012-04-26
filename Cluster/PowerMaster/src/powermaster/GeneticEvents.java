/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.DataBase.Database;
import Module.DataBase.Operations;
import genetics.Population;
import statistics.Statistics;
import utils.EventsSolver;
import utils.PopulationUtils;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class GeneticEvents implements EventsSolver {

    int Interval = 100;
    int nextInterval;
    int idClient;
    int idProblem;

    public GeneticEvents(int interval, int idClient, int idProblem) {
        this.Interval = interval;
        this.nextInterval = Interval;
        this.idClient = idClient;
        this.idProblem = idProblem;
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
//        if ((i % 10) == 0) {
//            System.out.println("Thread["+Thread.currentThread().getName()+"]:");
//            System.out.println("--------------------------------------------------");
//            System.out.println("Iteration:" + i);
//            System.out.println("Variance: " + statistics.getVarianciaPopulation().doubleValue());
//            System.out.println("Average:" + statistics.getMediaFitnessPopulation().doubleValue());
//            System.out.println("Deviation: " + statistics.getDesvioPadraoPopulation().doubleValue());
//            System.out.println("");
//        }

        if (i == 0) {
            boolean aux = Operations.InserirIteracoes(Thread.currentThread().getName().toString(), i, this.idClient, this.idProblem, PopulationUtils.getBestFitness(pltn), statistics.getMediaFitnessPopulation().doubleValue(), PopulationUtils.getNumberIndividualsWithBestFitness(pltn), PopulationUtils.getHallOfFame(pltn, 1).toString(), statistics.getDesvioPadraoPopulation(), 0, statistics.getVarianciaPopulation());
            System.out.println("Thread["+Thread.currentThread().getName()+"]First Iteration inserted[" + i + "]:" + aux);
        } else {
            boolean aux = Operations.InserirIteracoes(Thread.currentThread().getName().toString(), i, this.idClient, this.idProblem, PopulationUtils.getBestFitness(pltn), statistics.getMediaFitnessPopulation().doubleValue(), PopulationUtils.getNumberIndividualsWithBestFitness(pltn), PopulationUtils.getHallOfFame(pltn, 1).toString(), statistics.getDesvioPadraoPopulation(), 1, statistics.getVarianciaPopulation());
            System.out.println("Thread["+Thread.currentThread().getName()+"]Iteration inserted[" + i + "]:" + aux);
        }


    }

    @Override
    public void EventFinishSolver(int i, Population pltn) {
        Statistics statistics = new Statistics(pltn);
        boolean aux = Operations.InserirIteracoes(Thread.currentThread().getName().toString(), i, this.idClient, this.idProblem, PopulationUtils.getBestFitness(pltn), statistics.getMediaFitnessPopulation().doubleValue(), PopulationUtils.getNumberIndividualsWithBestFitness(pltn), PopulationUtils.getHallOfFame(pltn, 1).toString(), statistics.getDesvioPadraoPopulation(), 2, statistics.getVarianciaPopulation());
//        System.out.println("Thread["+Thread.currentThread().getName()+"]Last Iteration inserted[" + i + "]:" + aux);
//        
//        System.out.println("Thread["+Thread.currentThread().getName()+"]: Solver ended");
//        System.out.println("--------------------------------------------------");
//        System.out.println("Iteration:" + i);
//        System.out.println("Variance: " + statistics.getVarianciaPopulation().doubleValue());
//        System.out.println("Average:" + statistics.getMediaFitnessPopulation().doubleValue());
//        System.out.println("Deviation: " + statistics.getDesvioPadraoPopulation().doubleValue());
//        System.out.println("--------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("Hall of Fame - Top 5");
//        System.out.println("--------------------------------------------------");
//        System.out.println(PopulationUtils.getHallOfFame(pltn, 5));
    }
}
