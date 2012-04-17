package TMP;

import genetics.Population;
import java.util.ArrayList;

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
 * Classe que irá receber os parametrôs relativos ao saco para o problema da mochila.
 * Recebe o peso máximo da mochila e poderá receber os items que a mesma poderá conter (peso e valor de cada um)
 * ou então calcula os mesmos aleatóriamente.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Mochila {
    //ArrayList que terá todos os items que a mochila poderá transportar
    private ArrayList<Item> items;
    //Peso máximo que a mochila poderá transportar
    private int pesoMaximo;
    //Peso máximo que cada item poderá conter 
    private int maxPesoItem;
    //Valor máximo que cada item poderá ter
    private int maxValorItem;
    //Numero de items criados para a mochila
    private int numItems;
    //Variavel que fará o controlo das penalizações para os individuos no cálculo do seu fitness
    private boolean penalizacao = true;
    
    /**
     * Construtor da classe onde apenas é passado por parametro o peso máximo da mochila.
     * Os items que a mesma poderá transportar bem como os seus pesos e valores serão calculados aleatóriamente
     * @param pesoMochila - Peso máximo que a mochila poderá transportar
     */
    public Mochila(int pesoMochila){
        this.pesoMaximo = pesoMochila;
        //O máximo de peso por item nunca passará o valor máximo da capacidade da mochila
        this.maxPesoItem = Population.RANDOM_GENERATOR.nextInt(pesoMochila - 1);
        this.maxValorItem = Population.RANDOM_GENERATOR.nextInt();
        //Número de items criados aleatório
        this.numItems = Population.RANDOM_GENERATOR.nextInt();
        items = new ArrayList<Item>(numItems);
        criaItems();
    }
    /**
     * Construtor da classe onde são passado por parametros o peso máximo da mochila, o máximo de peso que cada item poderá conter,
     * o valor máximo que cada item poderá ter e o número de items que serão criados e que a mochila poderá transportar.
     * @param pesoMochila - Peso máximo de transporte da mochila
     * @param maxPeso - Peso máximo que cada item poderá ter
     * @param maxValor - Máximo valor que cada item poderá ter
     * @param numItems - Número de items a serem criados para a mochila
     */
    public Mochila(int pesoMochila, int maxPeso, int maxValor, int numItems){
        items = new ArrayList<Item>(numItems);
        this.pesoMaximo = pesoMochila;
        this.maxPesoItem = maxPeso;
        this.maxValorItem = maxValor;
        this.numItems = numItems;
        criaItems();
    }
    /**
     * Construtor da classe onde são passados por parametros o peso máximo que a mochila poderá transportar e os arrys de inteiros correspondentes
     * ao peso e valor de cada item. Os items serão criados de acordo com o peso e valor passados nos arrays.
     * @param pesoMochila - Peso máximo que a mochila poderá transportar
     * @param Peso - Array de inteiros referentes ao peso de cada item
     * @param Valor - Array de inteiros referentes ao valor de cada item
     */
    public Mochila(int pesoMochila, int[] Peso, int[] Valor){
        items = new ArrayList<Item>(Peso.length);
        this.numItems = Peso.length;
        this.pesoMaximo = pesoMochila;
        for (int i = 0; i < numItems; i++) {
            Item itemMochila = new Item();
            itemMochila.setPeso(Peso[i]);
            itemMochila.setValor(Valor[i]);
            items.add(itemMochila);
        }
    }
    /**
     * Método que cria os items aleatórios para a mochila, passando por parametro para cada
     * item o valor máximo que o mesmo poderá ter, bem como o valor máximo do mesmo
     */
    private void criaItems(){
        for (int i = 0; i < getNumItems(); i++) {
            Item itemMochila = new Item(maxPesoItem, maxValorItem);
            getItems().add(itemMochila);
        }
    }

    /**
     * Método que devolve os items que a mochila poderá conter
     * @return items - Items que a mochila poderá conter
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Método que permite definir os items que a mochila poderá transportar
     * @param items - Items a serem definidos para a mochila
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Método que devolve o peso máximo que a mochila poderá transportar
     * @return pesoMaximo - Peso máximo que pode ser transportado
     */
    public int getPesoMaximo() {
        return pesoMaximo;
    }

    /**
     * Método que devolve o número de items diferentes que a mochila contem
     * @return the numItems - Número de items diferentes
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * Método que permite saber se a penalização está ou não activa no cálculo do fitness
     * @return the penalizacao - true se a penalização estiver activa, false caso contrário
     */
    public boolean isPenalizacao() {
        return penalizacao;
    }

    /**
     * Método que permite definir se a penalização está ou não activa aquando do cálculo do fitness dos individuos
     * @param penalizacao - true para activar, false para desactivar
     */
    public void setPenalizacao(boolean penalizacao) {
        this.penalizacao = penalizacao;
    }
    
}
