<?php

require'db_con.php';

$sql="INSERT INTO lignecommande (idcommande,idproduit,Qtecomm,idlivreur,nomlivraison,prenomlivraison,tellivraison,state) VALUES (".$_GET['idcommande'].",".$_GET['idclient'].",".$_GET['quantity'].")";
	
$response;



if (mysqli_query($link,$sql)){
	
	 $last_id = $link->insert_id;

	$response=array('idligne'=>$last_id,'idcommande'=>'idcommande','idproduit'=>'idproduit','quantity'=>'quantity');
echo json_encode($response);
	
}



mysqli_close($link);


?>