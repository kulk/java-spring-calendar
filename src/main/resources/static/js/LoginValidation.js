
function validationError(event) {
    var form_tag_list = [$('#id_username').val(), $('#id_password').val()];
    var error_elements = ["userNameError", "passwordError" ];
    var error_strings = ["E-mail", "Password"];

    //Haal error berichten weg voor het geval er nog errors berichten stonden
    for (let i = 0; i < error_elements.length; i++) {
        $("#" + error_elements[i]).html(" ");
    }
    //Geef error voor velden die leeg zijn
    for (let i = 0; i < form_tag_list.length; i++) {
        if(form_tag_list[i] === ""){
            $("#" + error_elements[i]).html(error_strings[i] + " field cannot be empty");
            event.preventDefault();
        }
    }

}

login_form.addEventListener('submit', validationError);
