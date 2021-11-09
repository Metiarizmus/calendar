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
            <td>Удалить</td>
        </tr>

        <c:forEach var="manager" items="${manager}">
            <tr class="table_users">
                <td class="id" style="display: none"><c:out value="${manager.id}"/></td>
                <td><c:out value="${manager.firstName}"/></td>
                <td><c:out value="${manager.lastName}"/></td>
                <td><c:out value="${manager.email}"/></td>
                <td class="deleted" style="cursor: pointer">Удалить</td>
            </tr>
        </c:forEach>
    </table>
</div>




<script src="listUserAdmin.js"></script>

</body>
</html>

