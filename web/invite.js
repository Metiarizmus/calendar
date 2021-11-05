const accepted = document.querySelectorAll(".accepted");
const getTable = document.querySelectorAll(".table_invited");
const reject = document.querySelectorAll(".reject ");

let id;
accepted.forEach((item, index) => {
    item.onclick = function () {

        id = getTable[index].firstElementChild.innerHTML;

        const urlToInviteAccepted = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/acceptEvent";

        $.ajax({
            url: urlToInviteAccepted,
            method: "POST",
            data: {"id_invited_accepted": id},
            success: function(data) {
                console.log("good");
            }
        });

        window.location.href=window.location.href
    }
})

reject.forEach((item, index) => {
    item.onclick = function () {

        id = getTable[index].firstElementChild.innerHTML;

        const urlToInviteAccepted = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/acceptEvent";

        $.ajax({
            url: urlToInviteAccepted,
            method: "POST",
            data: {"id_invited_reject": id},
            success: function(data) {
                console.log("good");
            }
        });

        window.location.href=window.location.href

    }
})

// document.querySelectorAll(".btn_back").onclick = history.go(-1);

const btn_back = document.querySelectorAll(".btn_back");

btn_back.forEach(item =>{
    item.onclick = function () {
        history.go(-1);
    }
})