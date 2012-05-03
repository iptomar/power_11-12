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
using System.Windows.Shapes;

namespace PowerComputing
{
    /// <summary>
    /// Interaction logic for Definicoes.xaml
    /// </summary>
    public partial class Definicoes : Window
    {
        public Definicoes()
        {
            InitializeComponent();
        }

        private void bt_save_definicoes_Click(object sender, RoutedEventArgs e)
        {
            Properties.Settings.Default.PortaConsola = tb_porta_consola.Text;
            Properties.Settings.Default.PortaGrafico = tb_porta_grafico.Text;

            Properties.Settings.Default.IPMaster = tb_ip.Text;

            Properties.Settings.Default.Save();
        }
    }
}
