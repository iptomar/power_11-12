package Genetic;

import Module.Aplication;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 *  I n s t i t u t o   P o l i t e c n i c o   d e   T o m a r
 *   E s c o l a   S u p e r i o r   d e   T e c n o l o g i a
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 * -------------------------------------------------------------------------
 * Número de Aluno: 13691 
 * E-mail: Ruben.Felix@gmail.com
 * -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 */
public class Solver {

    /**
     * Classe Population que será a classe que irá guardar a população.
     */
    private Population Popul;
    /**
     * Classe Reprodution que irá receber uma população para ser reproduzida.
     */
    private Reprodution Rep;
    /**
     * Classe Torunament que irá receber uma população e fazer um torneio entre a mesma.
     */
    private Tournament Torn;
    /**
     * Classe mutação que irá fazer a mutação da população
     */
    private Mutacao Mut;
    /**
     * Variavel que guardará o número de repetições que o fitness poderá ter até o solver convergir
     * (Condição de paragem)
     */
    private int numRepeticoesFitness;
    /**
     * Variavel que guardará o fitness da população na iteração anterior
     */
    private double fitnessAnt;
    /**
     * Variavel que guardará a taxa de mutação a efectuar aos individuos da população
     */
    private double TaxaMut = 0.0;

    /**
     * Construtor da classe que receberá o número de individuos e o número de repetições que o fitness
     * poderá ter (condição de paragem)
     * @param numIndividuos (int) - Número de individuos da população 
     * @param numRepeticoes (int) - Número de repetições do fitness até o solver convergir (Condição de paragem)
     */
    public Solver(int numIndividuos, int numRepeticoes, double mut) {
        this.Popul = new Population(numIndividuos, 1, 10);
        this.numRepeticoesFitness = numRepeticoes;
        this.TaxaMut = mut;
    }

    /**
     * Construtor da classe que receberá o número de repetições que o fitness poderá ter (condição de paragem)
     * @param numRepeticoes (int) - Número de repetições do fitness até o solver convergir (Condição de paragem)
     */
    public Solver(int numRepeticoes) {
        this.numRepeticoesFitness = numRepeticoes;
        this.Popul = new Population();
    }

    public int execute() {
        ArrayList<Individual> afterTournament = new ArrayList<Individual>();
        ArrayList<Individual> afterReproduction = new ArrayList<Individual>();
        int numRepetCons = 0;
        int aux = 1;
        do {
            fitnessAnt = Popul.fitnessPopulationAvarage();
            Torn = new Tournament();
            afterTournament = Torn.execute(Popul.getPopulation());
            Rep = new Reprodution(afterTournament);
            afterReproduction = Rep.execute();
            Popul = new Population(afterReproduction);
            Popul.addToPopulation(afterTournament);
            Mut = new Mutacao(TaxaMut, Popul);
            Popul.setPopulation(Mut.IniciarMutacao().getPopulation());
            /*System.out.println("--------------Iteração nº " + aux + "---------------");
            System.out.println("Avarage Anterior :" + fitnessAnt);*/
            if (fitnessAnt == Popul.fitnessPopulationAvarage()) {
                numRepetCons++;
            } else {
                numRepetCons = 0;
            }

            try {
                //System.out.println("INSERT INTO resultados VALUES (null, '"+(fitnessAnt)+"','"+(Popul.fitnessPopulationAvarage())+"','"+Popul.getNumberIndividuals()+"','"+(Popul.HallOfFame()[0].fitnessIndividual())+"', NOW())");
                Aplication.db.ExecuteNonQuery("INSERT INTO resultados VALUES (null, '"+(fitnessAnt)+"','"+(Popul.fitnessPopulationAvarage())+"','"+Popul.getNumberIndividuals()+"','"+(Popul.HallOfFame()[0].fitnessIndividual())+"', NOW())");
            } catch (SQLException ex) {
                ex.printStackTrace();
                //Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /*System.out.println("Avarage Actual :" + Popul.fitnessPopulationAvarage());
            System.out.println("Número de individuos: " + Popul.getPopulation().size());
            System.out.println("Melhor individuo"+ Popul.HallOfFame()[0].fitnessIndividual());*/
            aux++;
        } while (numRepetCons != numRepeticoesFitness);
        return 1;
    }
}
