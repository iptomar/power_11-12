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
using System.Threading;

namespace WpfApplication1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

        }

        Random rand = new Random();
        Thread thr;

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            thr = new Thread(coisinho);

            thr.Start();
        }

        public void coisinho()
        {

            while (true)
            {
                coiso();
                Thread.Sleep(500);
            }

        }

        delegate void coisoCallback();
        private void coiso()
        {
            // InvokeRequired required compares the thread ID of the
            // calling thread to the thread ID of the creating thread.
            // If these threads are different, it returns true.
            if (this.rectangle3.Dispatcher.Thread != Thread.CurrentThread)
            {
                coisoCallback d = new coisoCallback(coiso);
                this.Dispatcher.Invoke(d, new object[] { });
            }
            else
            {
                //verifica se é ele mesmo



                int coiso = rand.Next(0, 100);
                rectangle3.Height = 255 - ((255 * coiso) / 100);

            }
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            try
            {
                thr.Abort();
            }
            catch (Exception)
            {

                throw;
            }

        }

    }
}
