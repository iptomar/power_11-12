package Genetic;

import java.util.Random;

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

/**
 * Classe que será a base de qualquer cromossoma. É uma classe genérica que poderá
 * receber qualquer valor para ser guardado.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Gene{
    /**
     * Variavel do tipo Random para gerar um boolean ao acaso.
     */
    private static final Random random = new Random();
    /**
     * Variavel value que receberá o valor de true ou false, conforme o valor
     * que o Random defina.
     */
    private boolean value;

    /**
     * Construtor por defeito da classe, que ao ser iniciada calcula ao acaso se o gene
     * é inicializado a true ou a false.
     */
    public Gene() {
        this.value = random.nextBoolean();
    }
    
    public Gene(boolean value) {
        this.value = value;
    }

    /**
     * Método que devolve o valor do gene (true ou false)
     * @return (boolean) - True caso o gene esteja definido, false caso contrário
     */
    public boolean getValue() {
        return value;
    }

    /**
     * Método que permite fazer a definição do gene
     * @param value (boolean) - True para ficar definido, false caso contrário
     */
    public void setValue(boolean value) {
        this.value = value;
    }
    
    @Override
    public String toString(){
        return "" + value;
    }
}
