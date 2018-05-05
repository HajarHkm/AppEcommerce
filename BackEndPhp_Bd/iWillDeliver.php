<?php

require'db_con.php';


$idcommande=$_GET['idcommande'];
$idlivreur=$_GET['idlivreur'];

$sql="UPDATE commande set idlivreur=".$idlivreur.", state='delivering' WHERE idcommande=".$idcommande ;
	
$response;



if (mysqli_query($link,$sql)){
	
	

	$response=array('idcommande'=>$_GET['idcommande'],'idlivreur'=>$_GET['idlivreur']);
echo json_encode($response);
	
}



mysqli_close($link);


?>