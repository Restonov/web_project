function validateFirstName() {
    const first_name_valid = /^[a-z A-Z]{1,20}$/;
    const first_name = document.getElementById("first_name");
    let firstname_error = document.getElementById("firstname_error");

    if(!first_name_valid.test(first_name.value) || first_name.value==='') {
        first_name.focus();
        firstname_error.style.color = "red";
        return false;
    } else {
        firstname_error.style.color = "white";
        return false;
    }
}

function validateLastName() {
    const last_name_valid = /^[a-z A-Z]{1,20}$/;
    const last_name = document.getElementById("last_name");
    let lastname_error = document.getElementById("lastname_error");

    if(!last_name_valid.test(last_name.value) || last_name.value==='') {
        last_name.focus();
        lastname_error.style.color = "red";
        return false;
    } else {
        lastname_error.style.color = "white";
        return false;
    }
}

function validateEmail() {
    const email_valid= /^[\w\d.]+@[a-zA-Z.]+\.[A-Za-z]{1,4}$/;
    const email = document.getElementById("email");
    let email_error = document.getElementById("email_error");

    if(!email_valid.test(email.value) || email.value==='') {
        email.focus();
        email_error.style.color = "red";
        return false;
    } else {
        email_error.style.color = "white";
        return false;
    }
}

function validatePhone() {
    const phone_valid= /^[\d]{12}$/;
    const phone = document.getElementById("phone");
    let phone_error = document.getElementById("phone_error");

    if(!phone_valid.test(phone.value) || phone.value==='') {
        phone.focus();
        phone_error.style.color = "red";
        return false;
    } else {
        phone_error.style.color = "white";
        return false;
    }
}

function validateLogin() {
    const login_valid = /^[a-z A-Z]{1,20}$/;
    const login = document.getElementById("login");
    let login_error = document.getElementById("login_error");

    if(!login_valid.test(login.value) || login.value==='') {
        login.focus();
        login_error.style.color = "red";
        return false;
    } else {
        login_error.style.color = "white";
        return false;
    }
}

function validatePassword() {
    const password_valid=/^[A-Z a-z0-9!@#$%_&-*()<>]{6,12}$/;
    const password = document.getElementById("password");
    let password_error = document.getElementById("password_error");

    if(!password_valid.test(password.value) || password.value==='') {
        password.focus();
        password_error.style.color = "red";
        return false;
    } else {
        password_error.style.color = "white";
        return false;
    }
}

