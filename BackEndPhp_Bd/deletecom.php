<?php

require'db_con.php';

$sql="delete from commande where idcommande=".$_GET['idcommande'];

$response = null;

if (mysqli_query($link,$sql)){
	
	$response="deleted";
echo ($response);
	
}
else echo($response)

?>