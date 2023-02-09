<?php

?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Progetto VashLabs">
    <meta name="author" content="Gabriele Scabiosi">

    <script src="./jquery-3.6.3.min.js"></script>
    <script src="./index.js"></script>

    <link rel="stylesheet" href="./index.css">
</head>

<body>
    <header>
        <h1>GestionaleTR</h1>
    </header>
    <main>
        <div>
            Inserisci massivamente i dati delle call.
        </div>
        <div class="button">
            <button id="btnAddRow">Aggiungi riga</button>
        </div>
        <div class="form">
            <form action="./insert.php" method="post">
                <div class="button" id="btnSubmit">
                    <button type="submit">Invia</button>
                </div>
            </form>
        </div>

    </main>
    <footer>

    </footer>
</body>

</html>