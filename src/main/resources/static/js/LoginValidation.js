

function validationError(event) {

    var form_tag_list = [document.login_form.username.value, document.login_form.password.value];
    var error_elements = ["userNameError", "passwordError" ];
    var error_strings = ["E-mail", "Password"];

    //Haal error berichten weg voor het geval er nog errors berichten stonden
    for (let i = 0; i < error_elements.length; i++) {
        document.getElementById(error_elements[i]).innerHTML = " ";
    }
    //Geef error voor velden die leeg zijn
    for (let i = 0; i < form_tag_list.length; i++) {
        if(form_tag_list[i] === ""){
            document.getElementById(error_elements[i]).innerHTML = error_strings[i] + " field cannot be empty";
            event.preventDefault();
        }
    }

}

login_form.addEventListener('submit', validationError);
