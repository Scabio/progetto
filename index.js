$(document).ready(() => {
    let row = 0;
    let inputBox = '<div class="inputBox">' + 
        '<div class="input">' + '<input type="date" name="dataCall' + row + '">' + '</div>' +
        '<div class="input">' + '<input type="time" name="timeCall' + row + '">' + '</div>' +
        '<div class="input">' + '<input type="text" name="nominativo' + row + '" placeholder="Nominativo">' + '</div>' +
        '<div class="input">' + '<input type="text" name="motivazione' + row + '" placeholder="Motivazione">' + '</div>' +
        '<div class="input">' + '<input type="text" name="descrizione' + row + '" placeholder="Descrizione">' + '</div>' +
        '</div>';
    $("#btnAddRow").click( () => {
      $("#btnSubmit").before(inputBox);
      row++;
    });

  });