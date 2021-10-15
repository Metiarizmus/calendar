<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Calendar</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style_user_mode.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

<%session.getAttribute("id_user"); %>

<div id="month-calendar">
    <!-- Блок с заголовками (наименования месяца и года) и навигацией (на месяц вперед и назад) -->
    <ul class="month">
        <div class="arrow">
            <li class="prev"><button><img src="left_row.png" width="50px" onclick="btn_prevt()"></button></li>
            <li class="next"><button><img src="right_row.png" width="50px" onclick="btn_next()"></button></li>
        </div>
        <li class="month-name"></li>
        <li class="year-name"></li>
    </ul>
    <ul class="weekdays">
        <li>Пн</li>
        <li>Вт</li>
        <li>Ср</li>
        <li>Чт</li>
        <li>Пт</li>
        <li>Сб</li>
        <li>Вс</li>
    </ul>
    <!-- табличная часть, дни месяца -->
    <ul class="days">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li>5</li>
        <li>6</li>
        <li>7</li>
        <li>8</li>
        <li>9</li>
        <li>10</li>
        <li>11</li>
        <li>12</li>
        <li>13</li>
        <li>14</li>
        <li>15</li>
        <li>16</li>
        <li>17</li>
        <li>18</li>
        <li>19</li>
        <li>21</li>
        <li>22</li>
        <li>23</li>
        <li>24</li>
        <li>25</li>
        <li>26</li>
        <li>27</li>
        <li>28</li>
        <li>29</li>
        <li>30</li>
        <li>31</li>
    </ul>
</div>

<div class="zatem">
    <div class="okno">

        <ul class="menu">
            <li><a href="#tab1" data-toggle="modal" onclick="check_tab1()" data-target="#myModal">Мероприятие</a></li>
            <li><a href="#tab2" data-toggle="modal" onclick="check_tab2()"  data-target="#myModal">Задача</a></li>
            <li><a href="#tab3" data-toggle="modal" onclick="check_tab3()" data-target="#myModal">Уведомление</a></li>
        </ul>

        <div class="modal_body">

            <div id="tab1" class="tab__content">

                <form action="" method="post">
                    <p><input type="text" name="title" placeholder="Введите название"></p>
                    <p>Время мероприятия:<input type="time" name="time"></p>
                    <p><input type="text" name="add_guest" placeholder="Добавьте гостей"></p>

                    <p><input id="event_table" class="action_save" type="submit" value="сохранить"/></p>
                </form>

            </div>

            <div id="tab2" class="tab__content">
                <form action="" method="post">

                    <p><input type="text" name="title" placeholder="Введите название"></p>
                    <p>Время задачи:<input type="time" name="time"></p>
                    <p><textarea name="description" id="" cols="60" rows="4" placeholder="Добавьте описание задачи"></textarea></p>

                    <p><input id="task_table" class="action_save" type="submit" value="сохранить"/></p>
                </form>

            </div>

            <div id="tab3" class="tab__content">
                <form id="form_tab3" action="actionUser" method="post">

                    <p><input type="text" name="title" placeholder="Введите название"></p>
                    <p>Время напоминания:<input type="time" name="time"></p>

                </form>

                <p>
                    <button id="notif_table" class="action_save" type="submit" value="сохранить" onclick="send_notif()">сохр</button>
                </p>
            </div>


        </div>

    </div>
</div>


<button class="closeBtn">
    Close
</button>





<script src="js_user_mode.js"></script>
</body>
</html>
