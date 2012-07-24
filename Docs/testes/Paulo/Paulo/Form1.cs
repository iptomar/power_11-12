using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Threading;

namespace Paulo
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent(); 
        }

            private int RandomNumber()
            {
                Random random = new Random();
                return random.Next(142890752, 999999999);
            }

        private void bt_onesmax_Click(object sender, EventArgs e)
        {
            MySocket socket_onesmax = new MySocket();
            socket_onesmax.Porta = 8080;
            socket_onesmax.IP = "code.dei.estt.ipt.pt";


            for (int i = 0; i < Convert.ToInt32(tb_n_vezes_onesmax.Text); i++)
            {


                string pedido_onemax = "load|{\"id\":" + RandomNumber ()+ ",\"client\":\"38\",\"algorithm\":[\"genetics.algorithms.OnesMax\",\"250 1 1 500 OnesMax\",\"499 1 300 0\"],\"mutation\":[\"operators.mutation.Flipbit\",\"0.025\"],\"operator\":[\"operators.recombinations.PMX\",\"0\"],\"recombination\":[\"operators.recombinations.UniformCrossover\",\"0.75\"],\"replacement\":[\"operators.replacements.Truncation\",\"250\"],\"selection\":[\"operators.selections.SUS\",\"250\"],\"nome\":\"teste\",\"desc\":\"testes tecnicos das equipas\"}";
                socket_onesmax.EnviaPedido(pedido_onemax, false);//false nao retorna texto

                Thread.Sleep(500);
            }
        }

        private void bt_knapsack_Click(object sender, EventArgs e)
        {
            MySocket socket_knapsack = new MySocket();
            socket_knapsack.Porta = 8080;
            socket_knapsack.IP = "code.dei.estt.ipt.pt";


            for (int i = 0; i < Convert.ToInt32(tb_n_vezes_knapsack.Text); i++)
            {
                string pedido_knapsack = "load|{\"id\":" + RandomNumber() +",\"client\":\"38\",\"algorithm\":[\"genetics.algorithms.K50\",\"100 1 1 50 K50\",\"1921 1 100 0\"],\"mutation\":[\"operators.mutation.Flipbit\",\"0.025\"],\"operator\":[\"operators.recombinations.PMX\",\"0\"],\"recombination\":[\"operators.recombinations.UniformCrossover\",\"0.75\"],\"replacement\":[\"operators.replacements.Truncation\",\"100\"],\"selection\":[\"operators.selections.SUS\",\"100\"],\"nome\":\"teste\",\"desc\":\"testes tecnicos das equipas\"}";
                socket_knapsack.EnviaPedido(pedido_knapsack, false);// nao retorna texto

                Thread.Sleep(500);
            }
        }

        private void bt_real_Click(object sender, EventArgs e)
        {
            MySocket socket_real = new MySocket();
            socket_real.Porta = 8080;
            socket_real.IP = "code.dei.estt.ipt.pt";


            for (int i = 0; i < Convert.ToInt32(tb_n_vezes_real.Text); i++)
            {
                
                string pedido_real = "load|{\"id\":" + RandomNumber() + ",\"client\":\"38\",\"algorithm\":[\"genetics.algorithms.functions.FunctionRealShiftedAckley\",\"12 2 3 45 FunctionRealShiftedAckley\",\"-138.0 1 120 1\"],\"mutation\":[\"operators.mutation.Flipbit\",\" \"],\"operator\":[\"operators.recombinations.PMX\",\"0\"],\"recombination\":[\"operators.recombinations.UniformCrossover\",\"0.75\"],\"replacement\":[\"operators.replacements.Tournament\",\"200 2\"],\"selection\":[\"operators.selections.Tournament\",\"200 2\"],\"nome\":\"teste\",\"desc\":\"testes tecnicos das equipas\",\"filen\":\"\"}";
                socket_real.EnviaPedido(pedido_real, false);//false nao retorna texto

                Thread.Sleep(500);
            }
        }

        private void knapk100_Click(object sender, EventArgs e)
        {
            MySocket socket_knapk100 = new MySocket();
            socket_knapk100.Porta = 8080;
            socket_knapk100.IP = "code.dei.estt.ipt.pt";


            for (int i = 0; i < Convert.ToInt32(tb_n_vezes_knapsack100.Text); i++)
            {
                                              
                string pedido_knapk100 = "load| {\"id\":" + RandomNumber() + ",\"client\":\"38\",\"algorithm\":[\"genetics.algorithms.K100\",\"200 1 1 100 K100\",\"1561 1 300 0\"],\"mutation\":[\"operators.mutation.Flipbit\",\"0.025\"],\"operator\":[\"operators.recombinations.PMX\",\"0\"],\"recombination\":[\"operators.recombinations.UniformCrossover\",\"0.75\"],\"replacement\":[\"operators.replacements.Truncation\",\"200\"],\"selection\":[\"operators.selections.SUS\",\"200\"],\"nome\":\"teste\",\"desc\":\"testes tecnicos das equipas\"}";
                socket_knapk100.EnviaPedido(pedido_knapk100, false);//false nao retorna texto

                Thread.Sleep(500);
            }
        }

    



    }
}
