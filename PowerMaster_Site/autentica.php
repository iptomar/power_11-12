<?
//variavel log recebe um login qualquer q vc deseja
$log = "boss";

//variavel pas recebe uma semnha  qualquer q vc deseja
$pas = "boss";


//Alinha abaixo verifica se os dados que o usuario digitou estao certos.

if (($login == $log) && ($senha == $pas))
{
   /*
   se o ususario digitou certo o script manda pra a pagina dos usuarios
   vc pode modificar pra qual pagina desejar desde que seja somente o nome pagina_certa.html  azul */
   header("Location: pagina_certa.html");
}
else
{
   
/*

se o cara digitou errado ele leva pra uma pagina de erro informando q a  senha ta errado vc tbm pode modificar essa para qual pagina de erro desejar, nao esqueca de modificar somente o nome pagina_erro.html

*/
   
header("Location: pagina_erro.html");
}
?>