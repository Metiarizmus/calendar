<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <title>Actions</title>
    <link rel="stylesheet" href="style_user_mode.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>


<div class="content_table">
    <table border="3">
        <tr>
            <td>Название</td>
            <td>Описание</td>
            <td>Дата</td>
            <td>Тип</td>
            <td>Email</td>
        </tr>

        <c:forEach var="action" items="${action}">
            <tr class="table_chekManagerToUsers">
                <td><c:out value="${action.title}"/></td>
                <td><c:out value="${action.description}"/></td>
                <td><c:out value="${action.date}"/></td>
                <td><c:out value="${action.typeAction}"/></td>
                <td><c:out value="${action.user.email}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>


<div id="my_button">
    <form action="adminMode" method="get" >
        <p><input class="btn_back" type="submit" value="back"/></p>
    </form>
</div>


</body>
</html>