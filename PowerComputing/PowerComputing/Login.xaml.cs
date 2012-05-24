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
using PowerComputing.Classes;

namespace PowerComputing
{
    /// <summary>
    /// Interaction logic for Login.xaml
    /// </summary>
    public partial class Login : Window
    {
        Acesso acesso = new Acesso();

        public Login()
        {
            InitializeComponent();

            Properties.Settings.Default.PortaConsola = "666";
            Properties.Settings.Default.PortaGrafico = "667";
            Properties.Settings.Default.IPMaster = "code.dei.estt.ipt.pt";

            Properties.Settings.Default.Save();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            if (Properties.Settings.Default.GuardarCredenciais == true)
            {
                cb_lembrar.IsChecked = true;

                tb_email.Text = Properties.Settings.Default.Email;
                tb_password.Password = Properties.Settings.Default.Password;

                tb_nome.Text = Properties.Settings.Default.Nome;
            }
        }

        private void Entrar_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            bool resultado = acesso.Login(tb_email.Text, tb_password.Password);
            
            if (resultado == true)
            {
                MainWindow principal = new MainWindow();
                principal.Show();
                this.Close();
            }
            else
            {
                System.Media.SoundPlayer player = new System.Media.SoundPlayer("Sounds/access_denied.wav");
                
                player.Play();
            }
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            if (cb_lembrar.IsChecked == true)
            {
                Properties.Settings.Default.GuardarCredenciais = true;

                Properties.Settings.Default.Email = tb_email.Text;
                Properties.Settings.Default.Password = tb_password.Password;
            }
            else
            {
                Properties.Settings.Default.GuardarCredenciais = false;
            }
            //Guardar todas as definicoes
            Properties.Settings.Default.Save();
        }

        private void image2_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            Definicoes def = new Definicoes();
            def.Show();
        }

    }
}
