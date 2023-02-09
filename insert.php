<?php
require("utils.php");

$urlToRedirect = "http://localhost:8000/";
$dbName = "test";
$user = "user";
$pass = "pass";

if($_SERVER["REQUEST_METHOD"] != "POST") {
    header("Location:".$urlToRedirect);
    exit();
}
try {
    $dbh = new PDO('mysql:host=localhost;dbname='.$dbName, $user, $pass);
    for($i=0;chkParams($i);$i++){
        try{
            $j=1;
            $stmt = $dbh->prepare("INSERT INTO chiamate (datacall, idCliente, motivazione, durata, descrizione) VALUES (?, (SELECT iD FROM clienti WHERE nominativo=? ), ?, ?, ?");
            $stmt->bindParam($j++, $_POST["dataCall".$i]);
            $stmt->bindParam($j++, $_POST["timeCall".$i]);
            $stmt->bindParam($j++, $_POST["nominativo".$i]);
            $stmt->bindParam($j++, $_POST["motivazione".$i]);
            $stmt->bindParam($j++, $_POST["descrizione".$i]);
            $stmt->execute();
        } catch (SQLException $e) {
            echo $e->getMessage();
        } 
    }
} catch (SQLException $e) {
    echo $e->getMessage();
} 
?>