package Genetic;

/**
 *
 * @author Pedro
 */
public class Mutacao {

    private double p;
    private Population pop;

    Mutacao(double p, Population pop) {
        this.p = p;
        this.pop = pop;
    }

    public Population IniciarMutacao() {
        //percorre toda a polulação
        for (int i = 0; i < pop.getNumberIndividuals(); i++) {
            //vai a cada individuo da população e vai muta-lo  
            Individual indi = pop.getIndividual(i);
            Muta(indi);
        }
        return pop;
    }

    public void Muta(Individual indi) {
        Individual i = indi;
        boolean novo, aux;
        double gr;

        //vai ao cromossoma de cada individuo e percorre todos os genes
        for (int j = 0; j < 10; j++) {
            //conforme a percentagem, poderá ou não mutar o gene para true/false
            gr = (Math.random());
            aux = i.getChromosome(0).getGene(j).getValue();

            if (gr <= p) {
                if (aux == true) {
                    novo = false;
                } else {
                    novo = true;
                }

                i.getChromosome(0).getGene(j).setValue(novo);
            }
        }
    }
}
