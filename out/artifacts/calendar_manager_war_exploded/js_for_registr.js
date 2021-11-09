function validateForm() {
    s = ' ';
    s = s.replace(/^\s+|\s+$/g, '');

    let name = document.forms["data_register"]["name"].value;
    if(name == s) {
        alert("your name cant be null");
        return false;
    }

    let password = document.forms["data_register"]["password"].value;
    if(password == '') {
        alert("your password cant be null");
        return false;
    }

    let email = document.forms["data_register"]["email"].value;
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (!re.test(email)){
        alert("your email not correct");
        return false;
    }
}