namespace Paulo
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.bt_onesmax = new System.Windows.Forms.Button();
            this.tb_n_vezes_onesmax = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.bt_knapsack = new System.Windows.Forms.Button();
            this.tb_n_vezes_knapsack = new System.Windows.Forms.TextBox();
            this.bt_real = new System.Windows.Forms.Button();
            this.tb_n_vezes_real = new System.Windows.Forms.TextBox();
            this.knapk100 = new System.Windows.Forms.Button();
            this.tb_n_vezes_knapsack100 = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // bt_onesmax
            // 
            this.bt_onesmax.Location = new System.Drawing.Point(118, 22);
            this.bt_onesmax.Name = "bt_onesmax";
            this.bt_onesmax.Size = new System.Drawing.Size(75, 23);
            this.bt_onesmax.TabIndex = 0;
            this.bt_onesmax.Text = "OnesMax";
            this.bt_onesmax.UseVisualStyleBackColor = true;
            this.bt_onesmax.Click += new System.EventHandler(this.bt_onesmax_Click);
            // 
            // tb_n_vezes_onesmax
            // 
            this.tb_n_vezes_onesmax.Location = new System.Drawing.Point(12, 25);
            this.tb_n_vezes_onesmax.Name = "tb_n_vezes_onesmax";
            this.tb_n_vezes_onesmax.Size = new System.Drawing.Size(100, 20);
            this.tb_n_vezes_onesmax.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(69, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Nº de Testes";
            // 
            // bt_knapsack
            // 
            this.bt_knapsack.Location = new System.Drawing.Point(118, 51);
            this.bt_knapsack.Name = "bt_knapsack";
            this.bt_knapsack.Size = new System.Drawing.Size(75, 23);
            this.bt_knapsack.TabIndex = 3;
            this.bt_knapsack.Text = "knapsack";
            this.bt_knapsack.UseVisualStyleBackColor = true;
            // 
            // tb_n_vezes_knapsack
            // 
            this.tb_n_vezes_knapsack.Location = new System.Drawing.Point(12, 54);
            this.tb_n_vezes_knapsack.Name = "tb_n_vezes_knapsack";
            this.tb_n_vezes_knapsack.Size = new System.Drawing.Size(100, 20);
            this.tb_n_vezes_knapsack.TabIndex = 4;
            // 
            // bt_real
            // 
            this.bt_real.Location = new System.Drawing.Point(118, 106);
            this.bt_real.Name = "bt_real";
            this.bt_real.Size = new System.Drawing.Size(75, 23);
            this.bt_real.TabIndex = 5;
            this.bt_real.Text = "FuncReal";
            this.bt_real.UseVisualStyleBackColor = true;
            // 
            // tb_n_vezes_real
            // 
            this.tb_n_vezes_real.Location = new System.Drawing.Point(12, 109);
            this.tb_n_vezes_real.Name = "tb_n_vezes_real";
            this.tb_n_vezes_real.Size = new System.Drawing.Size(100, 20);
            this.tb_n_vezes_real.TabIndex = 6;
            // 
            // knapk100
            // 
            this.knapk100.Location = new System.Drawing.Point(118, 78);
            this.knapk100.Name = "knapk100";
            this.knapk100.Size = new System.Drawing.Size(75, 23);
            this.knapk100.TabIndex = 7;
            this.knapk100.Text = "knapk100";
            this.knapk100.UseVisualStyleBackColor = true;
            this.knapk100.Click += new System.EventHandler(this.knapk100_Click);
            // 
            // tb_n_vezes_knapsack100
            // 
            this.tb_n_vezes_knapsack100.Location = new System.Drawing.Point(12, 80);
            this.tb_n_vezes_knapsack100.Name = "tb_n_vezes_knapsack100";
            this.tb_n_vezes_knapsack100.Size = new System.Drawing.Size(100, 20);
            this.tb_n_vezes_knapsack100.TabIndex = 8;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(215, 168);
            this.Controls.Add(this.tb_n_vezes_knapsack100);
            this.Controls.Add(this.knapk100);
            this.Controls.Add(this.tb_n_vezes_real);
            this.Controls.Add(this.bt_real);
            this.Controls.Add(this.tb_n_vezes_knapsack);
            this.Controls.Add(this.bt_knapsack);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.tb_n_vezes_onesmax);
            this.Controls.Add(this.bt_onesmax);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button bt_onesmax;
        private System.Windows.Forms.TextBox tb_n_vezes_onesmax;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button bt_knapsack;
        private System.Windows.Forms.TextBox tb_n_vezes_knapsack;
        private System.Windows.Forms.Button bt_real;
        private System.Windows.Forms.TextBox tb_n_vezes_real;
        private System.Windows.Forms.Button knapk100;
        private System.Windows.Forms.TextBox tb_n_vezes_knapsack100;
    }
}

