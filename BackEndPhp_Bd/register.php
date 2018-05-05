<?php

require'db_con.php';

$sql="INSERT INTO client (adressecli,datenaissance,email,loginclient,mdpclient,nomclient, prenomclient, telclient) VALUES ('adresse', '2017-07-02', '".$_GET['email']."' , '".$_GET['login']."' , '".$_GET['mdp']."', 'hhh','hh', '066')";
	
$response;

if (mysqli_query($link,$sql)){
	
	$response=array('adressecli'=> 'hhh','datenaissance'=> '2017-07-02','email'=> $_GET['email'],'loginclient'=>$_GET['login'],'mdpclient'=>$_GET['mdp'],
					'nomclient'=>'hhh','prenomclient'=>'hhh','telclient'=>'hhh');
echo json_encode($response);
	
}



mysqli_close($link);


?>