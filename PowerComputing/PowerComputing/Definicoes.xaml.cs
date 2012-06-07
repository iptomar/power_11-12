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
using Tamir.SharpSsh;
using Tamir.SharpSsh.java.lang;

namespace PowerComputing
{
    /// <summary>
    /// Interaction logic for Definicoes.xaml
    /// </summary>
    public partial class Definicoes : Window
    {
        SshExec ssh;

        public Definicoes()
        {
            InitializeComponent();

            tb_ip.Text = Properties.Settings.Default.IPMaster;
            tb_porta_grafico.Text = Properties.Settings.Default.PortaGrafico;
            tb_porta_consola.Text = Properties.Settings.Default.PortaConsola;

            ssh = new SshExec("code.dei.estt.ipt.pt", "root");
            ssh.Password = "power2012root";

            ssh.Connect();

            string verificar_jar_correr = ssh.RunCommand("ps -aux | grep PowerMaster");

            ssh.Close();

            if (verificar_jar_correr.Contains("PowerMaster.jar"))
            {
                lbl_status.Text = "[ Online ]";
                lbl_status.Foreground = Brushes.LightGreen;
                bt_ligar_servidor.IsEnabled = false;
            }
            else
            {
                lbl_status.Text = "[ Offline ]";
                lbl_status.Foreground = Brushes.Red; 
                bt_ligar_servidor.IsEnabled = true;
            }
        }

        private void bt_save_Click(object sender, RoutedEventArgs e)
        {
            Properties.Settings.Default.PortaConsola = tb_porta_consola.Text;
            Properties.Settings.Default.PortaGrafico = tb_porta_grafico.Text;

            Properties.Settings.Default.IPMaster = tb_ip.Text;

            Properties.Settings.Default.Save();
        }

        private void bt_ligar_servidor_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                string []pts = tb_params.Text.Split(' ');
 
                SshStream oi = new SshStream("code.dei.estt.ipt.pt", "root", "power2012root");
                oi.Write("cd Beta/");
                oi.Write("nohup java -jar -server PowerMaster.jar "+pts[0]+" "+pts[1]+" &");
                oi.Write("cd Scripts/");
                oi.Write("nohup ./cpu &");

                bt_ligar_servidor.IsEnabled = false;
                lbl_status.Text = "[ Online ]";
                lbl_status.Foreground = Brushes.LightGreen;
            }
            catch (Exception ex) { MessageBox.Show(ex.ToString()); }
        }
    }
}
