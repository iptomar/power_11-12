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

        //0 -> problems; 1-> results; 2-> slaves
        private int area_actual = 1;

        private DateTime currentTime;
        private const int MaxPoints = 70;
        private int tickInterval = 1000;
        private DateTime lastUpdated;

        private int NUMERO_CORES;

        private bool _LIGADO = false;

        Thread thr;

        public MainWindow()
        {
            InitializeComponent();

            //Mudar titulo
            tb_titulo.Text = Utilizador.NOME.ToUpper() + "@POWER_COMPUTING";

            //Ip e porta do socket para os graficos
            socket_graficos.IP = Properties.Settings.Default.IPMaster;
            socket_graficos.Porta = Convert.ToInt32(Properties.Settings.Default.PortaGrafico);

            //Ip e porta do socket para a consola
            socket_consola.IP = Properties.Settings.Default.IPMaster;
            socket_consola.Porta = Convert.ToInt32(Properties.Settings.Default.PortaGrafico);

            //Adicionar zoom ao grafico
            chart.Behaviour = new ZoomBehaviour();
            chart.Series.First().DataSeries = GenerateDataSeries();

            CompositionTarget.Rendering += new EventHandler(CompositionTarget_Rendering);

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
                        NUMERO_CORES = Convert.ToInt32(socket_consola.EnviaPedido("get -s cores"));

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

                Thread.Sleep(1000);

            }
        }

        public void MudaEstadoConeccao()
        {
            try
            {
                int teste = Convert.ToInt32(socket_consola.EnviaPedido("get -s cores"));

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









        private IDataSeries GenerateDataSeries()
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

        void CompositionTarget_Rendering(object sender, EventArgs e)
        {
            if ((DateTime.Now - lastUpdated).TotalMilliseconds > tickInterval)
            {
                UpdateChart();
            }
        }


        void UpdateChart()
        {
            try
            {
                if (((FrameworkElement)chart.Parent).ActualWidth == 0)
                    return;

                var priceData = (DataSeries<DateTime, double>)chart.Series[0].DataSeries;


                double currentCPU = Convert.ToDouble(socket_graficos.EnviaPedido("get -cpu")) / NUMERO_CORES;

                if (currentCPU > 100)
                {
                    currentCPU = 100;
                }
                priceData.Add(new DataPoint<DateTime, double>(currentTime, currentCPU));

                currentTime = currentTime.AddMilliseconds(tickInterval);

                if (priceData.Count > MaxPoints)
                {
                    priceData.RemoveAt(0);
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

        private void textBox2_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
            {
                tb_consola.AppendText("#powercomputing > " + tb_diz.Text + "\n");

                socket_consola.IP = tb_ip.Text;
                socket_consola.Porta = Convert.ToInt32(tb_porta.Text);

                string txt_recebido = socket_consola.EnviaPedido(tb_diz.Text);

                string[] partido = txt_recebido.Split('£');
                
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

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            thr.Abort();

            Properties.Settings.Default.Nome = Utilizador.NOME;
            Properties.Settings.Default.Save();
        }

        string[] ois;
        string[] pids;

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            string tasks = socket_graficos.EnviaPedido("get -tasks");

            string[] tasks_partidas = tasks.Split('£');

            DataTable resultados = CriaDataTable();

            for (int i = 1; i < tasks_partidas.Length; i++)
            {
                string[] teste = tasks_partidas[i].Split('?');

                 DataRow linha;
                   
                 linha = resultados.NewRow();
 
                 //gera o n£mero GUID
                 linha["PID"] = teste[0].ToString().Replace(" ", "");

                 try
                 {
                     linha["NAME"] = teste[1].ToString().Replace(" ","");
                 }
                 catch (Exception)
                 {
                     linha["NAME"] = "Não consegui";
                 }

                 resultados.Rows.Add(linha);
            }

            DataSet ds_on = new DataSet();
            ds_on.Clear();
            ds_on.Tables.Add(resultados);
            this.lb_tasks.DataContext = ds_on;

        }

        private DataTable CriaDataTable()
        {
            DataTable mDataTable = new DataTable();

            DataColumn mDataColumn;
            mDataColumn = new DataColumn();
            mDataColumn.DataType = Type.GetType("System.String");
            mDataColumn.ColumnName = "PID";
            mDataTable.Columns.Add(mDataColumn);

            mDataColumn = new DataColumn();
            mDataColumn.DataType = Type.GetType("System.String");
            mDataColumn.ColumnName = "NAME";
            mDataTable.Columns.Add(mDataColumn);

            return mDataTable;
        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

            try
            {
                if (onemax_ddl_selection.SelectedIndex.ToString() == "2")
                {
                    onemax_selection_valor2.IsEnabled = true;
                }
                else
                {
                    onemax_selection_valor2.IsEnabled = false;
                }
            }
            catch { }
        }

        private void bt_execute_onemax_Click(object sender, RoutedEventArgs e)
        {
            string[] ddl_selection = onemax_ddl_selection.SelectedItem.ToString().Split(' ');
            string[] ddl_mutation = onemax_dll_mutation.SelectedItem.ToString().Split(' ');
            string[] ddl_recombination = onemax_dll_recombination.SelectedItem.ToString().Split(' ');
            string[] ddl_replacement = onemax_dll_replacement.SelectedItem.ToString().Split(' ');

            string pedido_onemax = onemax_problem_name.Text + " " + onemax_problem_id.Text + " " + onemax_client_id.Text + " ";
            pedido_onemax += onemax_population_size.Text + " " + onemax_alello_size.Text + " " + onemax_best_fitness.Text + " " + onemax_iterations.Text + " ";

            pedido_onemax += ddl_selection[1] + " ";

            if (ddl_selection[1].ToString() == "Tournament")
            {
                pedido_onemax += onemax_selection_valor1.Text + " " + onemax_selection_valor2.Text + " ";
            }
            else
            {
                pedido_onemax += onemax_selection_valor1.Text + " ";
            }

            pedido_onemax += ddl_mutation[1] + " " + onemax_mutation_valor.Text + " ";
            pedido_onemax += ddl_recombination[1] + " ";
            pedido_onemax += ddl_replacement[1] + " " + onemax_tournament_valor1.Text + " " + onemax_tournament_valor2.Text;

            string resultado = socket_consola.EnviaPedido(pedido_onemax);

            lbl_onemax_resultado.Content = resultado;
        }

    }
}
