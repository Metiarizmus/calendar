let nowDate = new Date(),
    nowDateNumber = nowDate.getDate(),
    nowMonth = nowDate.getMonth(),
    nowYear = nowDate.getFullYear(),
    container = document.getElementById('month-calendar'),
    monthContainer = container.getElementsByClassName('month-name')[0],
    yearContainer = container.getElementsByClassName('year-name')[0],
    daysContainer = container.getElementsByClassName('days')[0],
    monthName = ['январь', 'февраль', 'март', 'апрель', 'май', 'июнь', 'июль', 'август', 'сентябрь', 'октябрь', 'ноябрь', 'декабрь'];

const getOkno = document.querySelector('.okno')
const zatemnenie = document.querySelector('.zatem')
const closeBtn = document.querySelector(".closeBtn")
const html = document.querySelector('html')
const invitation = document.querySelector(".invitation");
const content = document.querySelector(".content")

$(".sidenav a").css("text-shadow", "none")
//date
let dateSafesRem = [];
let dateSafesEvent = [];
let dateSafesTask = [];
//day
let dayRemArray = [];
let dayTaskArray = [];
let dayEventArray = [];
//
let currentObjOnMonth = [];

//request
let reqObj = [];

//array for click on day
let arrayData = [];

let curDate = nowDate.setMonth(nowDate.getMonth() - 1); // создаем экземпляр объекта для определенной календарной даты;


function setMonthCalendar(year, month) {
    let monthDays = new Date(year, month + 1, 0).getDate(),
        monthPrefix = new Date(year, month, 0).getDay(),
        monthDaysText = '';

    monthContainer.textContent = monthName[month];
    yearContainer.textContent = year;
    daysContainer.innerHTML = '';

    if (monthPrefix > 0) {
        for (let i = 1; i <= monthPrefix; i++) {
            monthDaysText += '<li class="day"></li>';
        }
    }

    dayRemArray = [];
    dayTaskArray = [];
    dayEventArray = [];

    currentObjOnMonth = [];


    for (let obj of reqObj) {
        if (obj.date.split(" ")[0].split("-")[1] == month + 1) {
            currentObjOnMonth.push(obj);

        }
    }


    console.log("current obj on month ", currentObjOnMonth)

    for (let obj of dateSafesRem) {
        let monthRem = parseInt(obj[1]);
        if (monthRem == month + 1) {
            dayRemArray.push(parseInt(obj[2]));
        }
    }

    for (let obj of dateSafesTask) {
        let monthTask = parseInt(obj[1]);
        if (monthTask == month + 1) {
            dayTaskArray.push(parseInt(obj[2]));
        }
    }

    for (let obj of dateSafesEvent) {
        let monthEvent = parseInt(obj[1]);
        if (monthEvent == month + 1) {
            dayEventArray.push(parseInt(obj[2]));
        }
    }
    let z = 0;

    for (let i = 1; i <= monthDays; i++) {
        for (let dayTask of dayTaskArray) {
            if (dayTask == i) {
                monthDaysText += `<li id="${i}" class="day task">` + i + `</li>`;
                z = 1;
            }
        }

        for (let dayNotif of dayRemArray) {
            if (dayNotif == i) {
                monthDaysText += `<li id="${i}" class="day rem">` + i + `</li>`;
                z = 1;
            }
        }

        for (let dayEvent of dayEventArray) {
            if (dayEvent == i) {
                monthDaysText += `<li id="${i}" class="day event">` + i + `</li>`;
                z = 1;
            }
        }

        if (z != 1) {
            monthDaysText += `<li id="${i}" class="day">` + i + `</li>`;
        }
        z = 0;
    }

    daysContainer.innerHTML = monthDaysText;

    if (month == nowMonth && year == nowYear) {
        days = daysContainer.getElementsByTagName('li');
        days[monthPrefix + nowDateNumber - 1].classList.add('date-now');
    }

}

setMonthCalendar(nowYear, nowMonth);

let xhr1 = new XMLHttpRequest();

const urlToAllEvent = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/dateForEvent";
const idObj = {"fake": "fake"};


const idJson = JSON.stringify(idObj);

xhr1.open('POST', urlToAllEvent, true);
xhr1.responseType = 'json';

xhr1.send(idJson);

xhr1.onreadystatechange = function () {

    if (xhr1.readyState != 4) return;

    let responseObj = xhr1.response;
    reqObj = xhr1.response;

    console.log("request object ", reqObj);

    userEmailNow = readCookie("user_email");

    for (let obj of responseObj) {
        if (obj.typeAction === "REMINDER") {
            dateSafesRem.push(obj.date.split(" ")[0].split("-"));
        } else if (obj.typeAction === "TASK") {
            dateSafesTask.push(obj.date.split(" ")[0].split("-"));
        } else if (obj.typeAction === "EVENT") {
            dateSafesEvent.push(obj.date.split(" ")[0].split("-"));
        }
    }
    setMonthCalendar(nowYear, nowMonth);
    //search date

    console.log("dateSafesRem: " + dateSafesRem);
    console.log("dateSafesTask: " + dateSafesTask);
    console.log("dateSafesEvent: " + dateSafesEvent);


    if (xhr1.status != 200) {
        alert(xhr1.status + ': ' + xhr1.statusText);
    }

    xhr1.onprogress = function (event) {
        alert(`Загружено ${event.loaded} из ${event.total}`);
    };

}


function btn_prevt() {
    let curDate = new Date(yearContainer.textContent, monthName.indexOf(monthContainer.textContent));

    curDate.setMonth(curDate.getMonth() - 1);

    let curYear = curDate.getFullYear(),
        curMonth = curDate.getMonth();

    setMonthCalendar(curYear, curMonth);
}

function btn_next() {

    let curDate = new Date(yearContainer.textContent, monthName.indexOf(monthContainer.textContent));

    curDate.setMonth(curDate.getMonth() + 1);

    let curYear = curDate.getFullYear(),
        curMonth = curDate.getMonth();

    setMonthCalendar(curYear, curMonth);
}


let arrayDataCopy = [];

function clickOnDay() {
    const day = document.querySelectorAll(".day");
    arrayDataCopy = [];

    let z = 0;
    day.forEach(item => {

        item.onclick = function () {
            arrayData.push(+item.textContent);
            arrayData.push(monthContainer.textContent);
            arrayData.push(+yearContainer.textContent);
            arrayDataCopy = [...arrayData];

            console.log("arrayDataCopy ", arrayDataCopy)

            for (let obj of currentObjOnMonth) {
                if (arrayDataCopy[0] == parseInt(obj.date.split(" ")[0].split("-")[2])) {
                    z = 1;

                    while (getOkno.firstChild) {
                        getOkno.removeChild(getOkno.firstChild)
                    }

                    getOkno.style.height = "50%"
                    getOkno.style.width = "35%"
                    getOkno.innerHTML = `

                    <div class="p_innerHtml">
                    
                    <p>Заголовок: ${obj.title}</p>
                    <p class="descr_innerHTML">Описание: ${obj.description}</p>
                    <p>Точная дата и время: ${obj.date}</p>
                    <p style="font-weight:bold">Тип события: ${obj.typeAction}</p>
                    
                    </div>
                    `

                    if (obj.typeAction != "TASK") {
                        $(".descr_innerHTML").css("display", "none");
                    }
                    openPopup();

                }
            }
            if (z == 1) {

            } else if (item.textContent !== "") {

                openPopup();
                console.log(arrayData)
            }

        }

    })

}

setInterval(clickOnDay, 100);

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
    const allBlocks = $('.tab__content');
    $(document).on('click', '.menu a', function () {
        const el = $(this), id = el.attr('href');
        allBlocks.css('display', 'none');
        $('.modal_body').find(id).css('display', 'block');
        return false;
    });
});


function check_tab1() {
    document.getElementById("tab1").style.visibility = "visible"
}

function check_tab2() {
    document.getElementById("tab2").style.visibility = "visible"
}

function check_tab3() {
    document.getElementById("tab3").style.visibility = "visible"
}


function send_notif() {

    const Vars = {var1: JSON.stringify(arrayData)};
    const varsData = $.param(Vars);

    const formData = $('#form_tab3').serialize();

    const data = varsData + '&' + formData;

    const urlToNotification = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/actionUser";

    $.ajax({
        type: 'POST',
        url: urlToNotification,
        data: data,
        success: function () {
            alert("сохранено")
        }
    })

    window.location.reload()
}

function send_task() {

    const Vars = {var1: JSON.stringify(arrayData)};
    const varsData = $.param(Vars);

    const formData = $('#form_tab2').serialize();

    const data = varsData + '&' + formData;

    const urlToNotification = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/actionUser";

    $.ajax({
        type: 'POST',
        url: urlToNotification,
        data: data,
        success: function () {
            alert("сохранено")
        }
    })

    window.location.reload()
}

function send_event() {

    const Vars = {var1: JSON.stringify(arrayData)};
    const varsData = $.param(Vars);

    const formData = $('#form_tab1').serialize();

    const data = varsData + '&' + formData;

    const urlToNotification = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/actionUser";

    $.ajax({
        type: 'POST',
        url: urlToNotification,
        data: data,
        success: function (data) {
            alert("Такого пользователя нет в системе мы отправили приглашение на email!")
            closePopup();
        }
    })
}

let xhr2 = new XMLHttpRequest();


const urlToInviteUsersData = window.location.protocol + '//' + window.location.host + "/calendar_manager_Web_exploded/InvitedEvent";
xhr2.open('POST', urlToInviteUsersData, true);
xhr2.responseType = 'json';

xhr2.send(idJson);

xhr2.onreadystatechange = function () {

    if (xhr2.readyState != 4) return;

    let responseObj2 = xhr2.response;

    console.log("respons 2 ", responseObj2);

    let k = 0;

    for (let obj of responseObj2) {
        k++;
    }

    if (k > 0) {
        $(".sidenav a").css("text-shadow", "0 0 20px #fff, 0 0 30px #ff4da6, 0 0 40px #ff4da6, 0 0 50px #ff4da6, 0 0 60px #ff4da6, 0 0 70px #ff4da6, 0 0 80px #ff4da6");
    }

    const sup = document.querySelector(".sup");
    sup.innerText = "+" + k;

    userEmailNow = readCookie("user_email");
    console.log(userEmailNow)

    if (xhr2.status != 200) {
        alert(xhr2.status + ': ' + xhr2.statusText);
    } else {

    }

    xhr2.onprogress = function (event) {
        alert(`Загружено ${event.loaded} из ${event.total}`);
    };
}


function readCookie(name) {

    const name_cook = name + "=";
    const spl = document.cookie.split(";");

    for (var i = 0; i < spl.length; i++) {

        let c = spl[i];

        while (c.charAt(0) === " ") {

            c = c.substring(1, c.length);

        }

        if (c.indexOf(name_cook) == 0) {

            return c.substring(name_cook.length, c.length);

        }

    }

    return null;

}