   M S B A M L   `   `   `  ����L  HControls.Gravatar, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null>1clr-namespace:Controls;assembly=Controls.GravatarControls  5   �    PowerComputing   PowerComputing.Login   5   	   W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35U QWPFToolkit, Version=3.5.40128.1, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @my1clr-namespace:Controls;assembly=Controls.Gravatar     5   �   -   5          � Title$  Power Computing��5   	   $	��280��6!   $	��500��6.    � 
ResizeMode$ CanMinimize=�6:    � Icon$; 5/PowerComputing;component/Images/power_icon_black.png��6S    � WindowStartupLocation$ CenterScreen=�6�   .��5      � ��6   Z�  � RowDefinitions 5   
   �� 5      $
��199* �6   �� 5      $	��26* �6   �� 5	      $	��16* �6    � ColumnDefinitions 5   
   �� 5      $��0.368* �6   �� 5      $��0.04* �6   �� 5      $��0.141* �6   �� 5      $��0.451* �6   .+�5   
   �� $��15.652��6   $��53.141,0,33.073,52q�6$   $
��Wrap=�6@   $��Bottom=�6T    J� RenderTransformOrigin$ 0.5,0.5&�6o   $��Arial)�6�   
�������$��	Ken Jeong��6�   �� 5   
   -   5         ��Name$ tb_email��5   �    �� CharacterCasing$ Normal=�6   }�.  $��11(�6@   $��Italic$�6N   ���	#FF292727$��23��6x   $
��Left=�6�   	 �� 
IsReadOnly	 .  $��0.125,60,0,0q�6�   $��rmlm1@iol.pt��6�   $	��Top=�6�   $��Center=�6  $	��239��6(  
 �� SelectionBrush
 �	#FF292727
�������$��2��6b  $��2��6r  G� 5   
   -   5         G�Name$ tb_password��5   y   $��11(�6   $��23��6$   ���	#FF292727$
��Left=�6G   $��0.125,89,0,87q�6b    G� Password$ pc2012��6�   $��Center=�6�   $	��239��6�    G� SelectionBrush �	#FF292727
�������$��2��6�   $��2��6  �� 5   
   -   5         ��Name$ label1��5   �   $��Esqueceu a sua password?��6    � Cursor$
 Hand��63   $��11(�6A   
�����_�$��29��6f   $
��Left=�6r   $��
15,116,0,0q�6�   $	��Top=�6�   $	��152��6�   $��1��6�   $��3��6�   �� 5   
   -   5         ��Name$ 
cb_lembrar��5   �   $%��Lembrar o meu e-mail e password��6   $��11(�6=   ���	#FF292727$��16��6b   $
��Left=�6n   $��0.125,149,0,0q�6�   $	��Top=�6�   $��2��6�   $��2��6�   �� 5   
   -   5        $ cb_entrar_auto��5   �   $��Entrar automaticamente��6   $��11(�64   ���	#FF292727$��16��6Y   $
��Left=�6e   $��	1,172,0,0q�6�   $	��Top=�6�   $��2��6�   $��148.206��6�   $��2��6�   �� 5   
   $��0.125,11,61.12,0q�6   $
��Wrap=�6.   $	��Top=�6B   $��29.333(�6Z   ���	#FF292727$��2��6�   $��2��6�   �� 6�    � Language$ pt-pt�6�   $�Iniciar Sessão��6�   6�   �� 5   
   -   5         ��Name$ boder_avatar��5      ���	#FF292727$��8,8,8,22q�6?   $��38.896,22,25,52q�6Z   ._�5         Controls.Gravatar  -   5        $��avatar��5   '   $	��105��6   $	��105��65   5      �� 5    
   -	   5        $��tb_nome��5    �   $��47.141,0,33.073,56q�6   $
��Wrap=�60   $��Bottom=�6D   
�������$��[Nome]��6r   6�   �� 5!   
   -
   5        $��2��5!      
����������	#FF413F3F$��1��6D   $
 Hand��6Q   $��2��6_   6s   �� 5"   
   -   5        $��2��5"      $
��Auto��6$   $
��Auto��61   $
��Wrap=�6?   ���	#FF413F3F$��1��6j   $��Entrar��6w   $��Center=�6�   $��Center=�6�   $
 Hand��6�   $��	18,4,18,6q�6�   6�   �� 5#   
   -   5        $��image2��5#   Z   $��3��6   $��1��6    $
��Left=�6-   $��12,4,0,4q�6H   $
��Fill=�6h   $��15��6w   $B��</PowerComputing;component/Images/glyphicons_280_settings.png��6�   $
 Hand��6�   $��15��6  5$      5%      