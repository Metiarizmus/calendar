
const countEventMonth = document.getElementById("countEventMonth").getAttribute("data-countEvent");
const countAcceptedEventMonth = document.getElementById("countAcceptedEventMonth").getAttribute("data-accepted");


google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

    var data = google.visualization.arrayToDataTable([
        ['City', 'Статистика за месяц',],
        ['Количество событий', +countEventMonth],
        ['Количество подтвержденных приглашений', +countAcceptedEventMonth],
    ]);

    var options = {
        title: 'Statistics for manager',
        chartArea: {width: '50%'},
        hAxis: {
            minValue: 0
        },
    };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

    chart.draw(data, options);
}

