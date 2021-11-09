<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <title>ListUsersAdmin</title>

    <link rel="stylesheet" href="style_list_usersAdmin.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>


<div class="content_table">
    <table border="3">
        <tr>
            <td style="display: none">Id</td>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Email</td>
            <td>Блокировка</td>
            <td>Удалить</td>
        </tr>

        <c:forEach var="users" items="${users}">
            <tr class="table_users">
                <td class="id" style="display: none"><c:out value="${users.id}"/></td>
                <td><c:out value="${users.firstName}"/></td>
                <td><c:out value="${users.lastName}"/></td>
                <td><c:out value="${users.email}"/></td>
                <td class="suspend" style="cursor: pointer"><c:out value="${users.suspend}"/></td>
                <td class="deleted" style="cursor: pointer">Удалить</td>
            </tr>
        </c:forEach>
    </table>
</div>


<input type="checkbox" id="side-checkbox" />
<div class="side-panel">
    <label class="side-button-2" for="side-checkbox">+</label>
    <div class="side-title">Добавить нового пользователя:</div>

    <div id="registration">
        <form id="form_add_user" name="data_register" method="post" action="registration">
            <p><span class="firstName"><input type="text" name="firstName" placeholder="first name"> </span></p>
            <p><span class="lastName"> <input type="text" name="lastName" placeholder="last name"></span></p>
            <p><span class="email"> <input type="text" name="email" placeholder="Email"/></span></p>
            <p><span class="password"><input type="text" name="password" placeholder="Password"/> </span></p>
        </form>
        <p><button class="regUser" onclick="addUser();">REGISTRATION</button></p>
    </div>

    <div class="side-title">Приглашение на почту:</div>

    <div>
        <form id="form_mail_id" name="form_mail" action="inviteUser"  method="post" >
            <p><input type="text" name="address" placeholder="your mail address"></p>
            <p><input class="inviteUser" type="submit" value="Отправить"/></p>
        </form>

    </div>

</div>
<div class="side-button-1-wr">
    <label class="side-button-1" for="side-checkbox">
        <div class="side-b side-open">Add user</div>
        <div class="side-b side-close">Закрыть</div>
    </label>
</div>

<script src="listUserAdmin.js"></script>
<script src="js_for_registr.js"></script>
</body>
</html>
