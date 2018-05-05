<?php

require'db_con.php';

$category=$_GET['category'];

$sql="select * from produit,catalogue where produit.typeproduit='".$category."' and YEAR(catalogue.datecatalogue) = YEAR(CURDATE()) and produit.idcatalogue=catalogue.idcatalogue";

$results=mysqli_query($link,$sql);
$response=array();

if(mysqli_num_rows($results)>0)
{
	while($row=mysqli_fetch_array($results))
	{
		array_push($response,array('nomproduit'=> $row['nomproduit'],'idproduit'=> $row['idproduit'],'prix'=> $row['prix'],'idcatalogue'=>$row['idcatalogue'],'typeproduit'=>$row['typeproduit'],'imageproduit'=>$row['imagepath'])

		);
	}
}
                        
echo json_encode($response);
mysqli_close($link);
?>