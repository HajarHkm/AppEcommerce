<?php

require'db_con.php';

$state=$_GET['state'];
$id=$_GET['idlivreur'];

$sql="select * from commande where state='delivering' and idlivreur=".$id;

$results=mysqli_query($link,$sql);
$response=array();

if(mysqli_num_rows($results)>0){
while($row=mysqli_fetch_array($results))
{
	array_push($response,array('idcommande'=> $row['idcommande'],'state'=>$state, 'nom_livraison' => $row['nomlivraison'],'adresse_livraison'=> $row['adresselivraison'],'idclient'=> $row['idclient']
	));
}}
                        
echo json_encode($response);
mysqli_close($link);
?>