<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
<title>Invited</title>
    <link rel="stylesheet" href="style_user_mode.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>


<div class="content_table">
    <table border="3">
        <tr>
            <td style="display:none;">Id</td>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Email</td>
            <td>Дата</td>
            <td>Название</td>
            <td>Принять</td>
            <td>Отменить</td>
        </tr>

        <c:forEach var="invite" items="${invite}">
            <tr class="table_invited">
                <td class="id" style="display:none;"><c:out value="${invite.id}"/></td>
                <td><c:out value="${invite.invitedUsers.firstName}"/></td>
                <td><c:out value="${invite.invitedUsers.lastName}"/></td>
                <td><c:out value="${invite.invitedUsers.email}"/></td>
                <td><c:out value="${invite.date}"/></td>
                <td><c:out value="${invite.title}"/></td>
                <td class="accepted">Принять</td>
                <td class="reject">Отклонить</td>
            </tr>
        </c:forEach>
    </table>
</div>


<div id="my_button">

        <p class="btn_back">back</p>

</div>

<script src="invite.js"></script>

</body>
</html>
