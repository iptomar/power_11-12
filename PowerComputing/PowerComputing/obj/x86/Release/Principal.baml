   M S B A M L   `   `   `  ���� Z  VVisiblox.Charts, Version=2.2.0.38099, Culture=neutral, PublicKeyToken=1543c03f04c4461bJ6clr-namespace:Visiblox.Charts;assembly=Visiblox.ChartsVisiblox.Charts  | xSystem.Windows.Controls.DataVisualization.Toolkit, Version=3.5.40128.1, Culture=neutral, PublicKeyToken=31bf3856ad364e35�{clr-namespace:System.Windows.Controls.DataVisualization.Charting;assembly=System.Windows.Controls.DataVisualization.Toolkit2System.Windows.Controls.DataVisualization.Charting L HControls.Gravatar, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null>1clr-namespace:Controls;assembly=Controls.GravatarControls  PowerComputing   PowerComputing.MainWindow   =my6clr-namespace:Visiblox.Charts;assembly=Visiblox.Charts  W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35U QWPFToolkit, Version=3.5.40128.1, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     @d2http://schemas.microsoft.com/expression/blend/2008     Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     �chartingToolkit{clr-namespace:System.Windows.Controls.DataVisualization.Charting;assembly=System.Windows.Controls.DataVisualization.Toolkit     Amy11clr-namespace:Controls;assembly=Controls.Gravatar     -     � Title$  Power Computing��$	��515��$	��928�� � WindowStyle$ SingleBorderWindow=� � 
ResizeMode$ CanMinimize=� � Icon$; 5/PowerComputing;component/Images/power_icon_black.png�� � WindowStartupLocation$ CenterScreen=� � 	Resources  �� 
TargetType �� 	Resources �� Color	 �� Opacity   problems_to_results
 �� TargetProperty �� 
TargetName9  3System.Windows.Media.Animation.EasingDoubleKeyFrame O� KeyTime O� Value  problems_to_master  results_to_problems  results_to_master  master_to_problems  master_to_results �� Property �� Value  connection_lost_slide_down R� KeyTime  System.Windows.Visibility   Visible R� Value  connection_lost_slide_up   Hidden��%�  '��       &	  Z     &	 �     &	 �    &	 J    &	 �    &	 �    '�� @    &	 t    &	 �    �� " �� ���� $
 Gray��$	 .4Q�(��       * SystemColors.HighlightBrushKey+)�� .��U� $U
 O(UIElement.RenderTransform).(TransformGroup.Children)[3].(TranslateTransform.X)�$
 grid��.\�  $ 0��$ -2Q�  $ 0:0:0.5��$
 -932Q��� .��U� $U
 O(UIElement.RenderTransform).(TransformGroup.Children)[3].(TranslateTransform.X)�$
 grid��.\�  $ 0��$ -2Q�  $ 0:0:0.5��$ -1862Q��� .��U� $U
 O(UIElement.RenderTransform).(TransformGroup.Children)[3].(TranslateTransform.X)�$
 grid��.\��� $ 0��$
 -932Q��� $ 0:0:0.5��$ -2Q��� .��U� $U
 O(UIElement.RenderTransform).(TransformGroup.Children)[3].(TranslateTransform.X)�$
 grid��.\��� $ 0��$
 -932Q��� $ 0:0:0.5��$ -1862Q��� .��U� $U
 O(UIElement.RenderTransform).(TransformGroup.Children)[3].(TranslateTransform.X)�$
 grid��.\��� $ 0��$ -1862Q��� $ 0:0:0.5��$ -2Q��� .��U� $U
 O(UIElement.RenderTransform).(TransformGroup.Children)[3].(TranslateTransform.X)�$
 grid��.\��� $ 0��$ -1862Q��� $ 0:0:0.5��$
 -932Q��� " ��.���  �@�FocusVisualStyle Z� �� .��U� $U
 O(UIElement.RenderTransform).(TransformGroup.Children)[3].(TranslateTransform.Y)�$ grelha_erro��.\�  $ 0��$ 1Q�  $ 0:0:0.5��$	 370Q�T� $
 (UIElement.Visibility)�$ 	grelha_bg��..�i� $ 0:0:0.1��# Z U� $
 (UIElement.Opacity)�$ 	grelha_bg��.\�  $ 0:0:0.1��$ 0Q�  $ 0:0:0.5��$ 0.495Q��� .��U� $U
 O(UIElement.RenderTransform).(TransformGroup.Children)[3].(TranslateTransform.Y)�$ grelha_erro��.\��� $ 0��$	 370Q��� $ 0:0:0.5��$ 1Q�T� $
 (UIElement.Visibility)�$ 	grelha_bg��..�i� $ 0��# Z i� $ 0:0:0.5��# Z i� $ 0:0:0.7��# Z U� $
 (UIElement.Opacity)�$ 	grelha_bg��.\�  $ 0��$	 0.5Q�  $ 0:0:0.5��$ 0Q� � Triggers :�  :� RoutedEvent$# UIElement.MouseLeftButtonDown�� :� 
SourceName$ image1��.[��� -    ��Name$. (connection_lost_slide_up_BeginStoryboard��#��[ .���  � RowDefinitions �� $��0.113* ��� $��0.041* ��� $��0.846* �.+��� -    ��Name$ 	tb_titulo��$��Right=�$��0,0,59,7q����	#FF4C4C4C$��13.333(�$��18.17��$��Bottom=���  � Language$ pt-pt�$�POWER COMPUTING��� $��24.35��$	��Top=�.��� ���	#FFE5E5E5$	��100��$	��100��� $��24.35��$	��Top=�.��� ���	#FFE5E5E5$��25��$��40��� $��24.35��$	��Top=�.��� ���	#FFE5E5E5$	��100��$	��100��� -    �Name$
 grid��$��8,6.967,-1880,0q�$��1�� J� RenderTransformOrigin$ 0.5,0.5&�$��2��z�V� .~��� �� �� T�  � ColumnDefinitions �� $��0.333* ��� $��0.333* ��� $��0.333* �.+��� -   $��tb_info_alg��$��355,21.002,29.78,98q� �� TextWrapping$
 Wrap=�$��Informação do algoritmo��$��11(� �� TextAlignment$ Justify=�  �� 
IsReadOnly  . $��1E-06q�! J� Opacity$	! 0.6Q�� $��0��$��0,0,0,0q�.+�� -   $ knapsack�����	#FFCD4900$��81��$��3,19.668,0,0q�$	��167��$
��Left=�$	��Top=�" � Cursor$
" Hand��.+��� -   $��image_knap_sack��$��20��$��0,4.665,4.665,0q�$
��Fill=�$	��Top=�$/��)/PowerComputing;component/Images/next.png��$��Right=�$��20��$y�Hidden=��� $
��Left=�$��4.895,0,0,4.669q�$
��Wrap=�$��KnapSack��
�������$��26.667(�$��Bottom=�� -   $ onemax�����	#FF02AEE0$��81��$��174,19.668,0,0q�$	��167��$
��Left=�$	��Top=�$
" Hand��.+��� -	   # ��Name$# image_one_max��$��20��$��0,4.665,4.665,0q�$
��Fill=�$	��Top=�$��Images/next.png��$��Right=�$��20��$y�Hidden=��� $
��Left=�$��4.895,0,0,4.669q�$
��Wrap=�
�������$��26.667(�$��Bottom=��� $ pt-pt�$�OneMax��� -
   $ k5�����	#FF56B10E$��81��$��3,104.668,0,0q�$��81��$
��Left=�$	��Top=�$
" Hand��.+��� -   $# image_k5��$��20��$��0,4.669,4.663,0q�$
��Fill=�$	��Top=�$��Images/next.png��$ -1.553,0.52&�$��Right=�$��20��$y�Hidden=��� $
��Left=�$��4.895,0,0,4.669q�$
��Wrap=�
�������$��26.667(�$��Bottom=��� $ pt-pt�$�K5��� -   $	 k50�����	#FF024685$��81��$��89,104.668,0,0q�$��81��$
��Left=�$	��Top=�$
" Hand��.+��� -   $# 	image_k50��$��20��$��0,4.669,4.663,0q�$
��Fill=�$	��Top=�$��Images/next.png��$ -1.553,0.52&�$��Right=�$��20��$y�Hidden=��� $
��Left=�$��4.895,0,0,4.669q�$
��Wrap=�
�������$��26.667(�$��Bottom=��� $ pt-pt�$	�K50��� -   $	 tsp�����	#FFB51F3C$��3,189.668,0,154.334q�$	��338��$
��Left=�$
" Hand��.+��� -   $# 	image_tsp��$��20��$��0,4.75,4.75,0q�$
��Fill=�$	��Top=�$��Images/next.png��$��Right=�$��20��$y�Hidden=��� $
��Left=�$��4.895,0,0,4.669q�$
��Wrap=�
�������$��26.667(�$��Bottom=��� $ pt-pt�$�TSP - ���� $�Travelling salesman��� -   $
 k100�����	#FF18984E$��81��$��174,104.668,0,0q�$��81��$
��Left=�$	��Top=�$
" Hand��.+��� -   $# 
image_k100��$��20��$��0,4.669,4.663,0q�$
��Fill=�$	��Top=�$��Images/next.png��$ -1.553,0.52&�$��Right=�$��20��$y�Hidden=��� $
��Left=�$��4.895,0,0,4.669q�$
��Wrap=�
�������$��26.667(�$��Bottom=��� $ pt-pt�$
�K100���� -   $��image2��$��Right=�$��
0,0,36,108q�$
��Fill=�$��25��$,��&/PowerComputing;component/Images/i.png��$��25��$��Bottom=�� $��1��.+�� -   $ grid_chart_params��.+�   Visiblox.Charts.Chart  -   $  Name$$ chart_params��$	��150��$	��350��
�������   Visiblox.Charts.ChartBase%   LegendVisibility$% 	Collapsed=�&   XAxis&     Visiblox.Charts.LinearAxis  '   YAxis'   ~   xVisiblox.Charts.AxisBase`1[[System.Double, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089]](   ShowMajorGridlines( . �   ~Visiblox.Charts.AxisCommonBase`1[[System.Double, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089]])   LabelFormatString$	) 0'%��*   Series* '   !Visiblox.Charts.StackedLineSeries  -	   'Visiblox.Charts.ChartMultipleSeriesBase+ 	  Series.+  
   Visiblox.Charts.LineSeries
  -   $��params��, 
  
LineStroke
, �  ��- 
  LineStrokeThickness$- 2Q��� $��2��$��-9.504,27.5,8.004,-1.5q�.@��� $��Performance��
�������.��� 
�������.+�� -   $ 	chart_cpu��$��0��$��0,0,20,0q�.+�  -   $$ chart��$	��340��
�������$% 	Collapsed=�& "   Visiblox.Charts.DateTimeAxis  �   zVisiblox.Charts.AxisBase`1[[System.DateTime, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089]].   ShowMinorTicks. .  /   ShowMajorGridlines/ .  '   $	) 0'%��0   ShowMinorTicks0 . ( . 1   Range1 !   Visiblox.Charts.DoubleRange     yVisiblox.Charts.RangeBase`1[[System.Double, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089]]2   Minimum$2 -1Q�3   Maximum$	3 101Q�*   .+ 
  -   $	��CPU��
, �  ��$- 2Q�  .+ 
  -   $	��MEM��
, ��  �$- 2Q�$
��Left=�$��879.48��� -   $ grid_percentagens��$
��Left=�$��112.667��
�������$
��36.6��$	��Top=�$��39.262,17.348,0,0q�.+��� -   $ lbl_cpu��$��5.5,0,44.5,0q�$
��Wrap=�$	��Top=��� $ pt-pt�$
�CPU:���� -   $ lbl_mem��$��5.5,17.39,44.5,0q�$
��Wrap=�$	��Top=�$��16.96���� $ pt-pt�$
�MEM:���� 
���  ��$��1��$��6.5,15.96,0,0q�
���  ��$	��Top=�$
��Left=�$��22.893���� ���	#FF1300FF$��1��$��6.682,0,0,2.25q����	#FF1300FF$��Bottom=�$
��Left=�$
��28.5���� $��Console��
�������.��� 
�������.+��� -   4 ��Name$4 
tb_consola��$��344.042��$��1.000001q�$	��Top=����	#FF48832C$��Consolas)�$��14.667(�$ Justify=����	#FFEBE8E8  . $ 0.5,0.5&�"5 �� VerticalScrollBarVisibility$
5 Auto=����	#00000000$��-5,0,5,0q��� -   $4 tb_diz��$��29��$
��Left=�$��1.000001q�$��Bottom=�$��924.833��
����������	#FF48832C$��Consolas)�$��14.667(�$��-3.5,0,0,-1.75q���Z� ���� � -   $ 	grelha_bg��$��-12,-3.65,-10,-8.35q����	#FF4C4C4C$��3��$! 0Q�$y�Hidden=�� -    $ grelha_erro��$��314,-221.5,312.5,0q�$	��Top=�$	��169��
�������$ 0.5,0.5&�z�V� .~��� �� �� T� .+��� 
����������	#FF333333$
��Left=�$��295.5��$��2���� $��79.501,106.998,79.502,0q�$
��Wrap=����	#FFD06B23$��18.667(�$	��Top=�$��29.83���� $ pt-pt�$�Connection Lost���� -!   $# image1��$��78��$��108.75,23.665,108.75,0q�$
��Fill=�$	��Top=�$/��)/PowerComputing;component/Images/lost.png��$��78���� $��57.507,0,58.66,20.208q�$
��Wrap=�$��Bottom=�$��10.667(����	#FF4C4C4C�� $ pt-pt�$+�%Please check your internet connection��� $��Right=�$��0,6.599,12.832,8.431q�$��40��
���   �$��40��.+�  Controls.Gravatar  -"   $��	gravatar1��$��39��$��39��$	��0.5q��� -#   $ tb_nome��$��Right=�$��0,1,58,0q����	#FF4C4C4C$��25(�$��32��$	��Top=��� $ pt-pt�$
�Nome���� -$   $
��Left=�$��9,6,0,0q�$
��Wrap=�$��LOGOUT��$	��Top=����	#FF4C4C4C$
��Bold"�$��13(�$
" Hand���� -%   $
" Hand��$��13(�$
��Bold"����	#FF4C4C4C$��Right=�$��0,6,782.5,0q�$��SETTINGS��$
��Wrap=�$	��Top=��� -&   $ results��$
��Left=�$��158,-26.681,0,4q�$��1��$
��Wrap=�$��29.333(�$��87.003�����	#FF4C4C4C$
" Hand���� $ pt-pt�$�results���� -'   $ master��$
��Left=�$��266,-26.681,0,4q�$��1��$
��Wrap=�$��29.333(�$��95�����	#FF4C4C4C$
" Hand���� $ pt-pt�$�master���� -(   $ problems��$
��Left=�$��8,-26.681,0,4q�$��1��$
��Wrap=�$��29.333(�$��132.25�����	#FF4C4C4C$
" Hand���� $ pt-pt�$�problems������ 6 �� ImageSource$36 -/PowerComputing;component/Images/windows8.png��