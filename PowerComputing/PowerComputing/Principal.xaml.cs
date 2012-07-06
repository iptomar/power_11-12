using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Media.Animation;
using System.IO;
using System.Net.Sockets;
using System.Threading;
using System.Collections;
using System.Windows.Controls.DataVisualization.Charting;
using Visiblox.Charts;
using System.Data;
using PowerComputing.Classes;
using Tamir.SharpSsh;

namespace PowerComputing
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        BrushConverter bc = new BrushConverter();
        Brush brush;

        Storyboard animacao;
        int painel_erro_aberto;


        MySocket socket_consola = new MySocket();
        MySocket socket_graficos = new MySocket();
        
        MySocket socket_pedidos = new MySocket();

        //0 -> problems; 1-> results; 2-> slaves
        private int area_actual = 0;

        private DateTime currentTime;
        private const int MaxPoints = 70;
        private int tickInterval = 1000;
        private DateTime lastUpdated;
        DateTime prim= DateTime.Now;
        private int NUMERO_CORES;

        private bool _LIGADO = false;

        Thread thr;

        private string info_knapsack;
        private string info_onemax;
        private string info_k5;
        private string info_k50;
        private string info_k100;
        private string info_tsp;

        public MainWindow()
        {
            InitializeComponent();


            gravatar1.Email = Utilizador.EMAIL;

            tb_nome.Text = Utilizador.NOME;
            
            //Ip e porta do socket para os graficos
            socket_graficos.IP = Properties.Settings.Default.IPMaster;
            socket_graficos.Porta = Convert.ToInt32(Properties.Settings.Default.PortaGrafico);

            //Ip e porta do socket para a consola
            socket_consola.IP = Properties.Settings.Default.IPMaster;
            socket_consola.Porta = Convert.ToInt32(Properties.Settings.Default.PortaGrafico);

            //Ip e porta do socket para pedidos
            socket_pedidos.IP = Properties.Settings.Default.IPMaster;
            socket_pedidos.Porta = 8080;

            //Adicionar zoom ao grafico
            chart.Behaviour = new ZoomBehaviour();

            var stack = chart.Series[0] as IChartMultipleSeries;
            var stack2 = chart.Series[1] as IChartMultipleSeries;

            stack.Series[0].DataSeries = GenerateDataCPU();
            stack2.Series[0].DataSeries = GenerateDataMEM();

            //Get Infos
            info_knapsack = socket_consola.EnviaPedido("get -info KnapSack", true).Replace('€', '\n');
            info_onemax = socket_consola.EnviaPedido("get -info OnesMax", true).Replace('€', '\n');
            info_k5 = socket_consola.EnviaPedido("get -info K5", true).Replace('€', '\n');
            info_k50 = socket_consola.EnviaPedido("get -info K50", true).Replace('€', '\n');
            info_k100 = socket_consola.EnviaPedido("get -info K100", true).Replace('€', '\n');
            info_tsp = socket_consola.EnviaPedido("get -info TSP", true).Replace('€', '\n');
            
            CompositionTarget.Rendering += new EventHandler(CompositionTargetCPU_Rendering);

            thr = new Thread(VerificarLigacao);
            thr.Start();
        }

        private void VerificarLigacao()
        {
            painel_erro_aberto = 0;

            while (true)
            {

                //MessageBox.Show("estato no LIGAR(): " + _LIGADO);
                if (_LIGADO == true)
                {
                    try
                    {

                        //Buscar numero de cores do master
                        NUMERO_CORES = Convert.ToInt32(socket_consola.EnviaPedido("get -s cores", true));

                        if (painel_erro_aberto == 1)
                        {
                            Mexe();
                            //MessageBox.Show("Liguei-me e mexi-me; estado painel: " + painel_erro_aberto);
                        }

                        _LIGADO = true;
                    }
                    catch
                    {
                        if (painel_erro_aberto != 1)
                        {
                            Mexe();
                            //MessageBox.Show("Não me liguei e mexi-me; estado painel: " + painel_erro_aberto);
                        }

                        _LIGADO = false;
                    }
                }

                MudaEstadoConeccao();

                Thread.Sleep(2000);

            }
        }

        public void MudaEstadoConeccao()
        {
            try
            {
                int teste = Convert.ToInt32(socket_consola.EnviaPedido("get -s cores", true));

                _LIGADO = true;

            }
            catch { _LIGADO = false; }
        }

        private delegate void MexeCallback();
        private void Mexe()
        {
            if (this.tb_consola.Dispatcher.Thread != Thread.CurrentThread)
            {
                MexeCallback d = new MexeCallback(Mexe);
                this.Dispatcher.Invoke(d, new object[] { });
            }
            else
            {
                if (painel_erro_aberto == 0)
                {
                    animacao = (Storyboard)FindResource("connection_lost_slide_down");
                    animacao.Begin();

                    painel_erro_aberto = 1;
                }
                else
                {
                    animacao = (Storyboard)FindResource("connection_lost_slide_up");
                    animacao.Begin();

                    painel_erro_aberto = 0;
                }
            }
        }

        private IDataSeries GenerateDataCPU()
        {
            var data = new DataSeries<DateTime, double>();

            // Set the starting date a year earlier & set initial price
            this.currentTime = DateTime.Now;
            double price = 100.00;

            // Create initial data for the charts and assign it to them
            for (int i = 0; i < 100; i++)
            {
                currentTime = currentTime.AddMilliseconds(tickInterval);

                // Generate the price
                price = 0.0;
                data.Add(new DataPoint<DateTime, double>(currentTime, price));
            }

            return data;
        }


        private IDataSeries GenerateDataMEM()
        {
            var data = new DataSeries<DateTime, double>();

            // Set the starting date a year earlier & set initial price
            this.currentTime = DateTime.Now;
            double price = 100.00;

            // Create initial data for the charts and assign it to them
            for (int i = 0; i < 100; i++)
            {
                currentTime = currentTime.AddMilliseconds(tickInterval);

                // Generate the price
                price = 0.0;
                data.Add(new DataPoint<DateTime, double>(currentTime, price));
            }

            return data;
        }

        void CompositionTargetCPU_Rendering(object sender, EventArgs e)
        {
            if ((DateTime.Now - lastUpdated).TotalMilliseconds > tickInterval)
            {
                UpdateChartCPU();
                UpdateChartMEM();
            }
        }


        void UpdateChartCPU()
        {
            try
            {
                if (((FrameworkElement)chart.Parent).ActualWidth == 0)
                    return;

                var stackedLineSeries = chart.Series[0] as IChartMultipleSeries;

                var cpu = (DataSeries<DateTime, double>)stackedLineSeries.Series[0].DataSeries;
                

                //MessageBox.Show(stackedLineSeries.Series[0].GetType()+" | " + stackedLineSeries.Series[1].GetType() + " | " + stackedLineSeries.Series[2].GetType());
                string []dados = socket_graficos.EnviaPedido("get -cpumem", true).ToString().Split('£');

                double currentCPU = Convert.ToInt32(Convert.ToDouble(dados[0].ToString().Replace('.',',')));
                double currentMEM= Convert.ToDouble(dados[1]);


                if (currentCPU > 100.0)
                {
                    currentCPU = 100.0;
                   
                }

                lbl_cpu.Text = "CPU: " + currentCPU + "%";

                cpu.Add(new DataPoint<DateTime, double>(currentTime, currentCPU));
                

                currentTime = currentTime.AddMilliseconds(tickInterval);

                if (cpu.Count > MaxPoints)
                {
                    cpu.RemoveAt(0);
                    
                }
                lastUpdated = DateTime.Now;
            }
            catch { }
        }


        void UpdateChartMEM()
        {
            try
            {
                if (((FrameworkElement)chart.Parent).ActualWidth == 0)
                    return;

                var stackedLineSeries = chart.Series[1] as IChartMultipleSeries;

                var mem = (DataSeries<DateTime, double>)stackedLineSeries.Series[0].DataSeries;

                //MessageBox.Show(stackedLineSeries.Series[0].GetType()+" | " + stackedLineSeries.Series[1].GetType() + " | " + stackedLineSeries.Series[2].GetType());
                string[] dados = socket_graficos.EnviaPedido("get -cpumem", true).ToString().Split('£');

                double currentCPU = Convert.ToInt32(Convert.ToDouble(dados[0].ToString().Replace(',', '.')) / NUMERO_CORES);
                double currentMEM = Convert.ToDouble(dados[1]);


                lbl_mem.Text = "MEM: " + currentMEM + "%";

                if (currentMEM > 100.0)
                {
                    
                    currentMEM = 100.0;
                }

                mem.Add(new DataPoint<DateTime, double>(currentTime, currentMEM));

                currentTime = currentTime.AddMilliseconds(tickInterval);

                if (mem.Count > MaxPoints)
                {  
                    mem.RemoveAt(0);
                }
                lastUpdated = DateTime.Now;
            }
            catch { }
        }


        private void Button_MouseEnter(object sender, System.Windows.Input.MouseEventArgs e)
        {
            TextBlock tb = sender as TextBlock;
            brush = (Brush)bc.ConvertFrom("#1F6884");
            tb.Foreground = brush;
        }

        private void Button_MouseLeave(object sender, System.Windows.Input.MouseEventArgs e)
        {
            TextBlock tb = sender as TextBlock;
            brush = (Brush)bc.ConvertFrom("#4C4C4C");
            tb.Foreground = brush;
        }

        /// <summary>
        /// Metodo de animação entre paineis
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button_Animation(object sender, MouseButtonEventArgs e)
        {
            TextBlock tb = sender as TextBlock;

            switch (tb.Name)
            {
                case "problems":
                    if (area_actual == 1)
                    {
                        animacao = (Storyboard)FindResource("results_to_problems");
                        animacao.Begin();
                    }
                    else if (area_actual == 2)
                    {
                        animacao = (Storyboard)FindResource("master_to_problems");
                        animacao.Begin();
                    }
                    area_actual = 0;
                    break;
                case "results":
                    if (area_actual == 0)
                    {
                        animacao = (Storyboard)FindResource("problems_to_results");
                        animacao.Begin();
                    }
                    else if (area_actual == 2)
                    {
                        animacao = (Storyboard)FindResource("master_to_results");
                        animacao.Begin();
                    }
                    area_actual = 1;
                    break;
                case "master":
                    if (area_actual == 0)
                    {
                        animacao = (Storyboard)FindResource("problems_to_master");
                        animacao.Begin();
                    }
                    else if (area_actual == 1)
                    {
                        animacao = (Storyboard)FindResource("results_to_master");
                        animacao.Begin();
                    }
                    area_actual = 2;
                    break;
                default:
                    break;
            }
        }

        /// <summary>
        /// Metodo que executa pedidos pela consola
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void textBox2_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
            {
                tb_consola.AppendText("#powercomputing > " + tb_diz.Text + "\n");

                socket_consola.IP = Properties.Settings.Default.IPMaster;
                socket_consola.Porta = Convert.ToInt32(Properties.Settings.Default.PortaConsola);

                string txt_recebido = socket_consola.EnviaPedido(tb_diz.Text, true);

                string bom = txt_recebido.Replace('€', '\n');

                string[] partido = bom.Split('£');
                
                for (int i = 0; i < partido.Length; i++)
                {
                    tb_consola.AppendText(partido[i].ToString() + "\n");
                }
                

                tb_diz.Clear();

                tb_consola.ScrollToEnd();
            }
            else if (e.Key == Key.Up)
            {
                try
                {
                }
                catch { }
            }
            else if (e.Key == Key.Down)
            {
                try
                {

                }
                catch { }
            }
        }

        /// <summary>
        /// Metodo executado ao fechar a janaela
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            thr.Abort();

            Properties.Settings.Default.Nome = Utilizador.NOME;
            Properties.Settings.Default.Save();
        }

        /// <summary>
        /// Hover rectangulos
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Grid_MouseEnter(object sender, MouseEventArgs e)
        {
            Grid gr = sender as Grid;
            tb_info_alg.Foreground = Brushes.White;

            switch (gr.Name)
            {
                case "knapsack":
                    image_knap_sack.Visibility = Visibility.Visible;
                    tb_info_alg.Text = info_knapsack;
                    tb_info_alg.Background = knapsack.Background;
                    break;
                case "onemax":
                    image_one_max.Visibility = Visibility.Visible;
                    tb_info_alg.Text = info_onemax;
                    tb_info_alg.Background = onemax.Background;
                    break;
                case "k5":
                    image_k5.Visibility = Visibility.Visible;
                    tb_info_alg.Text = info_k5;
                    tb_info_alg.Background = k5.Background;
                    break;
                case "k50":
                    image_k50.Visibility = Visibility.Visible;
                    tb_info_alg.Text = info_k50;
                    tb_info_alg.Background = k50.Background;
                    break;
                case "k100":
                    image_k100.Visibility = Visibility.Visible;
                    tb_info_alg.Text = info_k100;
                    tb_info_alg.Background = k100.Background;
                    break;
                case "tsp":
                    image_tsp.Visibility = Visibility.Visible;
                    tb_info_alg.Text = info_tsp;
                    tb_info_alg.Background = tsp.Background;
                    break;
                default:
                    break;
            }
        }

        private void image_knap_sack_MouseLeave(object sender, MouseEventArgs e)
        {
            Grid gr = sender as Grid;

            switch (gr.Name)
            {
                case "knapsack":
                    image_knap_sack.Visibility = Visibility.Hidden;
                    break;
                case "onemax":
                    image_one_max.Visibility = Visibility.Hidden;
                    break;
                case "k5":
                    image_k5.Visibility = Visibility.Hidden;
                    break;
                case "k50":
                    image_k50.Visibility = Visibility.Hidden;
                    break;
                case "k100":
                    image_k100.Visibility = Visibility.Hidden;
                    break;
                case "tsp":
                    image_tsp.Visibility = Visibility.Hidden;
                    break;
                default:
                    break;
            }
        }

        /// <summary>
        /// Enviar Problemas
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void onemax_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            Grid gr = sender as Grid;

            switch (gr.Name)
            {
                case "knapsack":
                    break;
                case "onemax":
                    string pedido_knapsack = "load|{\"id\":132890752,\"client\":\"1\",\"algorithm\":[\"genetics.algorithms.OnesMax\",\"1000 1 1 500 OnesMax\",\"499 1 300 0\"],\"mutation\":[\"operators.mutation.Flipbit\",\"0.025\"],\"operator\":[\"operators.recombinations.PMX\",\"0\"],\"recombination\":[\"operators.recombinations.UniformCrossover\",\"0.75\"],\"replacement\":[\"operators.replacements.Truncation\",\"1000\"],\"selection\":[\"operators.selections.SUS\",\"1000\"],\"nome\":\"teste\",\"desc\":\"testes tecnicos das equipas\"}";
                    socket_pedidos.EnviaPedido(pedido_knapsack, false);
                    animacao = (Storyboard)FindResource("problems_to_results");
                    animacao.Begin();
                    area_actual = 1;
                    break;
                case "k5":
                    string pedido_k5 = "load|{\"id\":132890752,\"client\":\"1\",\"algorithm\":[\"genetics.algorithms.OnesMax\",\"1000 1 1 500 OnesMax\",\"499 1 300 0\"],\"mutation\":[\"operators.mutation.Flipbit\",\"0.025\"],\"operator\":[\"operators.recombinations.PMX\",\"0\"],\"recombination\":[\"operators.recombinations.UniformCrossover\",\"0.75\"],\"replacement\":[\"operators.replacements.Truncation\",\"1000\"],\"selection\":[\"operators.selections.SUS\",\"1000\"],\"nome\":\"teste\",\"desc\":\"testes tecnicos das equipas\"}";
                    socket_pedidos.EnviaPedido(pedido_k5, false);
                    animacao = (Storyboard)FindResource("problems_to_results");
                    animacao.Begin();
                    area_actual = 1;
                    break;
                case "k50":

                    break;
                case "k100":

                    break;
                case "tsp":

                    break;
                default:
                    break;
            }
        }

    }
}
