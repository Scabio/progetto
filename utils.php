<?php

function chkParams($i) {
    $flag = false;
    if(array_key_exists("dataCall".$i, $_POST) && array_key_exists("timeCall".$i, $_POST) && array_key_exists("nominativo".$i, $_POST) && array_key_exists("motivazione".$i, $_POST) && array_key_exists("descrizione".$i, $_POST)){
        $flag = true;
    }
    return $flag;
}