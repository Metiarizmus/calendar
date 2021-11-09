<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="RU">
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="style_for_login.css">
</head>
<body>

<div class="wrapper">
    <div class="content">
        <div id="registration">
            <form name="data_register" method="post" action="login" onsubmit="return validateForm()" >
                <p><span class="email"> </span><input type="text" name="email" placeholder="Email"/></p>
                <p><span class="password"> </span><input type="text" name="password" placeholder="Password"/></p>
                <p><input class="btn_registr" type="submit" value="ENTER"/></p>
            </form>
        </div>
    </div>

    <div class="footer">
        <p>© 2021 Все права защищены</p>
        <p>Автор проекта: Нагорных Николай stormytt@mail.ru</p>
    </div>
</div>

<script src="js_for_registr.js"></script>
</body>
</html>