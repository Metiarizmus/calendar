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
  <title>Диаграммы</title>

  <link rel="stylesheet" href="managerBar.css">

</head>
<body>

<div id="countEventMonth" data-countEvent="${countEventMonth}" />
<div id="countAcceptedEventMonth" data-accepted="${countAcceptedEventMonth}" />

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<div id="chart_div"></div>

<div class="users_online">Онлайн: ${countInSystem}</div>

<script src="managerChartsJs.js"></script>
</body>
</html>
