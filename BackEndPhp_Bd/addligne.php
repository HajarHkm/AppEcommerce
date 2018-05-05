<?php

require'db_con.php';

$sql="INSERT INTO lignecommande (idcommande,idproduit,quantity) VALUES (".$_GET['idcommande'].",".$_GET['idproduit'].",".$_GET['quantity'].")";
	
$response;



if (mysqli_query($link,$sql)){
	
	 $last_id = $link->insert_id;

	$response=array('idligne'=>$last_id,'idcommande'=>$_GET['idcommande'],'idproduit'=>$_GET['idproduit'],'quantity'=>$_GET['quantity']);
echo json_encode($response);
	
}



mysqli_close($link);


?>