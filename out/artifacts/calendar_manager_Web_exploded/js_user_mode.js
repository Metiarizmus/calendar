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
                getOkno.style.display = "block";
                zatemnenie.classList.add('zatemnenie');
                getOkno.classList.add('styleOkno');
                html.style.overflow = "hidden";
                closeBtn.style.opacity = 1;
                closeBtn.style.cursor = "pointer";
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