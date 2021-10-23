let nowDate = new Date(),
    nowDateNumber = nowDate.getDate(),
    nowMonth = nowDate.getMonth(),
    nowYear = nowDate.getFullYear(),
    container = document.getElementById('month-calendar'),
    monthContainer = container.getElementsByClassName('month-name')[0],
    yearContainer = container.getElementsByClassName('year-name')[0],
    daysContainer = container.getElementsByClassName('days')[0],
    monthName = ['январь','февраль','март','апрель','май','июнь','июль','август','сентябрь','октябрь','ноябрь','декабрь'];

const getOkno = document.querySelector('.okno')
const zatemnenie = document.querySelector('.zatem')
const closeBtn = document.querySelector(".closeBtn")
const html = document.querySelector('html')

let curDate = nowDate.setMonth(nowDate.getMonth() - 1); // создаем экземпляр объекта для определенной календарной даты;


function setMonthCalendar(year,month) {
    let monthDays = new Date(year, month + 1, 0).getDate(),
        monthPrefix = new Date(year, month, 0).getDay(),
        monthDaysText = '';

    monthContainer.textContent = monthName[month];
    yearContainer.textContent = year;
    daysContainer.innerHTML = '';

    if (monthPrefix > 0){
        for (let i = 1  ; i <= monthPrefix; i++){
            monthDaysText += '<li class="day"></li>';
        }
    }

    for (let i = 1; i <= monthDays; i++){
        monthDaysText += '<li class="day">' + i + '</li>';
    }

    daysContainer.innerHTML = monthDaysText;

    if (month == nowMonth && year == nowYear){
        days = daysContainer.getElementsByTagName('li');
        days[monthPrefix + nowDateNumber - 1].classList.add('date-now');
    }
}



setMonthCalendar(nowYear,nowMonth);


function btn_prevt() {
    let curDate = new Date(yearContainer.textContent,monthName.indexOf(monthContainer.textContent));

    curDate.setMonth(curDate.getMonth() - 1);

    let curYear = curDate.getFullYear(),
        curMonth = curDate.getMonth();

    setMonthCalendar(curYear,curMonth);
}



function btn_next() {

    let curDate = new Date(yearContainer.textContent,monthName.indexOf(monthContainer.textContent));

    curDate.setMonth(curDate.getMonth() + 1);

    let curYear = curDate.getFullYear(),
        curMonth = curDate.getMonth();

    setMonthCalendar(curYear,curMonth);
}



let arrayData = [];

function clickOnDay() {
    const day = document.querySelectorAll(".day");
    //arrayData = [];

    day.forEach(item => {
        item.onclick = function() {
            if (item.textContent !== "") {
                arrayData.push(+item.textContent)
                arrayData.push(monthContainer.textContent)
                arrayData.push(+yearContainer.textContent)
                openPopup();
                console.log(arrayData)
            }
        }
    })
}

setInterval(clickOnDay, 100);


const invitation = document.querySelector(".invitation");

invitation.onclick = function(){
    while(getOkno.firstChild){
        getOkno.removeChild(getOkno.firstChild)
    }

    getOkno.style.height = "50%"
    getOkno.style.width = "35%"
    getOkno.innerHTML = 'Lorem ipsum, sit orci magna pellentesque vivamus nibh magna enim mauris arcu mauris — duis. Ut donec sit pellentesque at eros elementum rutrum porta risus nulla proin eget, pharetra vulputate nulla eros diam, auctor. Mattis pellentesque commodo rutrum metus fusce bibendum urna ultricies eu elementum vitae. Lorem: auctor tellus tempus, magna pharetra enim non auctor et, lorem curabitur urna morbi arcu ipsum ornare urna curabitur ipsum eros pharetra. Fusce nec amet pellentesque mattis pellentesque nam ultricies, quisque maecenas pellentesque mauris sapien. Adipiscing, leo, congue sed leo odio sagittis enim sit — ligula eget, bibendum, morbi congue. Risus arcu porttitor — nec non rutrum vivamus ligula nec amet bibendum, ornare rutrum. Vitae nam arcu sem rutrum eu ligula nam proin porta porttitor ornare tempus ornare nulla, congue vitae molestie. Ornare in ligula sem commodo et: pellentesque magna leo at adipiscing nibh vivamus metus, congue leo. Quam urna orci nam, donec rutrum quisque, auctor: massa molestie leo ornare mattis ipsum quisque. Et ipsum ultricies eget fusce tellus urna: ligula vitae massa quam malesuada ut lectus tempus non ligula nulla et elementum a duis, ornare cursus.'
    openPopup();

    $(".sidenav a").css("text-shadow","none")

}


function openPopup() {
    getOkno.style.display = "block";
    zatemnenie.classList.add('zatemnenie');
    getOkno.classList.add('styleOkno');
    html.style.overflow = "hidden";
    closeBtn.style.opacity = 1;
    closeBtn.style.cursor = "pointer";
}

closeBtn.addEventListener("click", function () {
    closePopup();
})


function closePopup() {
    zatemnenie.classList.remove('zatemnenie')
    getOkno.classList.remove('styleOkno')
    html.style.overflow = ""
    closeBtn.style.opacity = 0
    closeBtn.style.cursor = "default"
    getOkno.innerHTML = ``
    location.reload();
}


$(document).ready(function () {
    var allBlocks = $('.tab__content');
    $(document).on('click', '.menu a', function () {
        var el = $(this), id = el.attr('href');
        allBlocks.css('display', 'none');
        $('.modal_body').find(id).css('display', 'block');
        return false;
    });
});


function check_tab1() {
    document.getElementById("tab1").style.visibility="visible"
}
function check_tab2() {
    document.getElementById("tab2").style.visibility="visible"
}
function check_tab3() {
    document.getElementById("tab3").style.visibility="visible"
}

let xhr = new XMLHttpRequest();

function send_notif() {

    var Vars = {var1: JSON.stringify(arrayData)};
    var varsData = $.param(Vars);

    var formData = $('#form_tab3').serialize();

    const data = varsData + '&' + formData;

    const urlToNotification = window.location.protocol + '//' + window.location.host+"/calendar_manager_Web_exploded/actionUser";

    $.ajax({
        type: 'POST',
        url: urlToNotification,
        data: data,
        success: function(){ alert ("сохранено") }
    })

     location.reload();
}

function send_task() {

    const Vars = {var1: JSON.stringify(arrayData)};
    const varsData = $.param(Vars);

    const formData = $('#form_tab2').serialize();

    const data = varsData + '&' + formData;

    const urlToNotification = window.location.protocol + '//' + window.location.host+"/calendar_manager_Web_exploded/actionUser";

    $.ajax({
        type: 'POST',
        url: urlToNotification,
        data: data,
        success: function(){ alert ("сохранено") }
    })

    location.reload();
}

function send_event() {

    const Vars = {var1: JSON.stringify(arrayData)};
    var varsData = $.param(Vars);

    var formData = $('#form_tab1').serialize();

    var data = varsData + '&' + formData;

    const urlToNotification = window.location.protocol + '//' + window.location.host+"/calendar_manager_Web_exploded/actionUser";

    $.ajax({
        type: 'POST',
        url: urlToNotification,
        data: data,
        success: function(res){ alert ("сохранено") }
    })

    location.reload();
}

let userEmailNow;

function get_events() {
    const urlToAllEvent = window.location.protocol + '//' + window.location.host+"/calendar_manager_Web_exploded/dateForEvent";
    const idObj = {"fake": "fake"};


    const idJson = JSON.stringify(idObj);

    xhr.open('POST',urlToAllEvent, true);
    xhr.responseType='json';

    xhr.send(idJson);

    function monthCreate(num) {

        let month;

        switch (+num) {
            case 1:
                 month = "январь";
            break;
            case 2:
                month = "февраль";
                break;
            case 3:
                month = "март";
                break;
            case 4:
                month = "апрель";
                break;
            case 5:
                month = "май";
                break;
            case 6:
                month = "июнь";
                break;
            case 7:
                month = "июль";
                break;
            case 8:
                month = "август";
                break;
            case 9:
                month = "сентябрь";
                break;
            case 10:
                month = "октябрь";
                break;
            case 11:
                month = "ноябрь";
                break;
            case 12:
                month = "декабрь";
                break;
        }

        return month;
    }

    xhr.onreadystatechange = function() {

        if (xhr.readyState != 4) return;

        let responseObj = xhr.response;

        let dateSafes = [];

        console.log(responseObj);

        userEmailNow =readCookie("user_email");
        console.log(userEmailNow)

//         for (let obj of responseObj) {
//            dateSafes.push(obj.date.split(" ")[0].split("-"));
//
//
//             console.log(monthContainer.textContent)
//             console.log(yearContainer.textContent)
//            // if(obj.typeAction === "EVENT") {
//            //
//            // } else if (obj.typeAction === "TASK") {
//            //
//            // } else {
//            //
//            // }
//         }
//
//         console.log(dateSafes)
//
// setInterval( () => {
//
//         let mon = monthCreate(dateSafes[1]);
//
//         if (yearContainer.textContent === dateSafes[0] && monthContainer.textContent === mon) {
//             console.log(mon);
//         };
//     console.log(mon);
//
// }, 100);
//

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {

        }

        xhr.onprogress = function(event) {
            alert(`Загружено ${event.loaded} из ${event.total}`);
        };
    }


    // const urlToInviteUsersData = window.location.protocol + '//' + window.location.host+"/calendar_manager_Web_exploded/InvitedEvent";
    // xhr.open('POST',urlToInviteUsersData, true);
    // xhr.responseType='json';
    //
    // xhr.send(idJson);
    //
    // xhr.onreadystatechange = function() {
    //
    //     if (xhr.readyState != 4) return;
    //
    //     let responseObj = xhr.response;
    //
    //     let dateSafes = [];
    //
    //     console.log(responseObj);
    //
    //     userEmailNow =readCookie("user_email");
    //     console.log(userEmailNow)
    //
    //
    //     if (xhr.status != 200) {
    //         alert(xhr.status + ': ' + xhr.statusText);
    //     } else {
    //
    //     }
    //
    //     xhr.onprogress = function(event) {
    //         alert(`Загружено ${event.loaded} из ${event.total}`);
    //     };
    // }

}


function readCookie(name) {

    const name_cook = name + "=";
    const spl = document.cookie.split(";");

    for(var i=0; i<spl.length; i++) {

        let c = spl[i];

        while(c.charAt(0) === " ") {

            c = c.substring(1, c.length);

        }

        if(c.indexOf(name_cook) == 0) {

            return c.substring(name_cook.length, c.length);

        }

    }

    return null;

}