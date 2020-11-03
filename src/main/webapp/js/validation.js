function validateFirstName() {
    const first_name_valid = /^[a-z A-Z]+$/;
    const first_name = document.getElementById("first_name");

    if(!first_name_valid.test(first_name.value) || first_name.value==='') {
        first_name.focus();
        document.getElementById("firstname_error").innerHTML="Please, enter your First name (alphabet only)";
        return false;
    } else {
        document.getElementById("firstname_error").innerHTML="";
        return false;
    }
}

function validateLastName() {
    const last_name_valid = /^[a-z A-Z]+$/;
    const last_name = document.getElementById("last_name");

    if(!last_name_valid.test(last_name.value) || last_name.value==='') {
        last_name.focus();
        document.getElementById("lastname_error").innerHTML="Please, enter your Last name (alphabet only)";
        return false;
    } else {
        document.getElementById("lastname_error").innerHTML="";
        return false;
    }
}

function validateEmail() {
    const email_valid= /^[\w\d.]+@[a-zA-Z.]+\.[A-Za-z]{1,4}$/;
    const email = document.getElementById("email");

    if(!email_valid.test(email.value) || email.value==='') {
        email.focus();
        document.getElementById("email_error").innerHTML="Please, enter your Email";
        return false;
    } else {
        document.getElementById("email_error").innerHTML="";
        return false;
    }
}

function validateLogin() {
    const login_valid = /^[a-z A-Z]+$/;
    const login = document.getElementById("login");

    if(!login_valid.test(login.value) || login.value==='') {
        login.focus();
        document.getElementById("login_error").innerHTML="Please, enter valid Login (alphabet only)";
        return false;
    } else {
        document.getElementById("login_error").innerHTML="";
        return false;
    }
}

function validatePassword() {
    const password_valid=/^[A-Z a-z0-9!@#$%_&-*()<>]{6,12}$/;
    const password = document.getElementById("password");

    if(!password_valid.test(password.value) || password.value==='') {
        password.focus();
        document.getElementById("password_error").innerHTML="Password must Be 6 to 12 and allowed !@#$%&*()<> character";
        return false;
    } else {
        document.getElementById("password_error").innerHTML="";
        return false;
    }
}

