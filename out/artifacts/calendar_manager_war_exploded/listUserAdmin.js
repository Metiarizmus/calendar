const deleted = document.querySelectorAll(".deleted");
const suspend = document.querySelectorAll(".suspend");

let id;
deleted.forEach((item, index) => {
    item.onclick = function () {

        id = getTable[index].firstElementChild.innerHTML;

        const urlToInviteAccepted = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/deletedUsersAdmin";

        $.ajax({
            url: urlToInviteAccepted,
            method: "POST",
            data: {"id_users_deleted": id},
            success: function(data) {
                console.log("good");
            }
        });

        window.location.href=window.location.href
    }
})

suspend.forEach((item, index) => {
    item.onclick = function () {

        id = getTable[index].firstElementChild.innerHTML;

        const urlToInviteAccepted = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/deletedUsersAdmin";

        $.ajax({
            url: urlToInviteAccepted,
            method: "POST",
            data: {"id_users_suspend": id},
            success: function(data) {
                console.log("good");
            }
        });

        window.location.href=window.location.href
    }
})