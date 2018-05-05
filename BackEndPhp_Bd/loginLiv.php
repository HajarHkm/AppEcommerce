<?php
require'db_con.php';


$log=$_GET['login'];
$mdp=$_GET['mdp'];

$sql="select * from livreur where loginlivreur='".$log."' and mdplivreur='".$mdp."'";
$results=mysqli_query($link,$sql);
$response;

if(mysqli_num_rows($results) > 0){
while($row=mysqli_fetch_array($results)){

$response=array('idlivreur'=>$row['idlivreur'],'loginlivreur'=>$row['loginlivreur'],'mdplivreur'=>$row['mdplivreur'],'nomlivreur'=>$row['nomlivreur'],'prenomlivreur'=>$row['prenomlivreur'],'tellivreur'=>$row['tellivreur']

	);}

/*while($row=mysqli_fetch_array($results))
{
	array_push($response,array('adressecli'=> $row['adressecli'],'datenaissance'=> $row['datenaissance'],'email'=> $row['email'],'idclient'=>$row['idclient'],'loginclient'=>$row['loginclient'],'mdpclient'=>$row['mdpclient'],
		'nomclient'=>$row['nomclient'],'prenomclient'=>$row['prenomclient'],'telclient'=>$row['telclient']

	));
}*/}


echo json_encode($response);
mysqli_close($link);
?>
