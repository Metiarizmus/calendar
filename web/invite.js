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
            data: {"ACCEPTED": id},
            success: function(data) {
                console.log("good");
            }
        });

        location.reload();
    }
})

reject.forEach((item, index) => {
    item.onclick = function () {

        id = getTable[index].firstElementChild.innerHTML;

        const urlToInviteAccepted = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/acceptEvent";

        $.ajax({
            url: urlToInviteAccepted,
            method: "POST",
            data: {"REJECT": id},
            success: function(data) {
                console.log("good");
            }
        });

        location.reload();

    }
})


const btn_back = document.querySelectorAll(".btn_back");

btn_back.forEach(item =>{
    item.onclick = function () {
        history.go(-1);
    }
})