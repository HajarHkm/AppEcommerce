<?php

require'db_con.php';

$sql="INSERT INTO commande (idclient,adresselivraison,datecommande,idlivreur,nomlivraison,prenomlivraison,tellivraison,state) VALUES (".$_GET['idclient'].",'".$_GET['adresse']."',CURDATE(),'1','".$_GET['nom']."','".$_GET['prenom']."','".$_GET['tel']."','waiting')";
	
$response;



if (mysqli_query($link,$sql)){
	
	 $last_id = $link->insert_id;

	$response=array('idclient'=> $_GET['idclient'],'idcommande'=>$last_id,'adresselivraison'=>$_GET['adresse'],'datecommande'=>'2018-04-10','idlivreur'=>'1','nomlivraison'=>$_GET['nom'],'prenomlivraison'=>$_GET['prenom'],'tellivraison'=>$_GET['tel'],'state'=>'delivering');
echo json_encode($response);
	
}



mysqli_close($link);


?>