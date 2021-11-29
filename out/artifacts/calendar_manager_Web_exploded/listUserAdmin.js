const deleted = document.querySelectorAll(".deleted");
const suspend = document.querySelectorAll(".suspend");
const getTable = document.querySelectorAll(".table_users");

let id;
deleted.forEach((item, index) => {
    item.onclick = function () {

        id = getTable[index].firstElementChild.innerHTML;

        const urlToInviteAccepted = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/deletedUsersAdmin";

        $.ajax({
            url: urlToInviteAccepted,
            method: "POST",
            data: {"DELETED": id},
            success: function(data) {
                console.log("good");
            }
        });

        location.reload();
    }
})

suspend.forEach((item, index) => {
    item.onclick = function () {

        id = getTable[index].firstElementChild.innerHTML;

        const urlToInviteAccepted = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/deletedUsersAdmin";

        if (+item.textContent === 0) {
            $.ajax({
                url: urlToInviteAccepted,
                method: "POST",
                data: {"SUSPEND": id},
                success: function (data) {
                    console.log("good");
                }
            });
            alert("you have blocked a user");
        } else if (+item.textContent === 1) {
            $.ajax({
                url: urlToInviteAccepted,
                method: "POST",
                data: {"UNSUSPEND": id},
                success: function (data) {
                    console.log("good");
                }
            });
            alert("you have unblocked a user");
        }
        location.reload();

    }
})


function addUser() {
    var Vars = {parametr: 1};
    var varsData = $.param(Vars);

    var formData = $('#form_add_user').serialize();

    var data = varsData + '&' + formData;

    const urlToRegistration = window.location.protocol + '//' + window.location.host+"/calendar_manager_Web_exploded/registration";


    $.ajax({
        url: urlToRegistration,
        method: "POST",
        data: data,
        error: function(res){
            alert ("this email yet exist in the system")
        }

    })

   // location.reload();

}

