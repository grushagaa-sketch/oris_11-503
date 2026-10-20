<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Аня
  Date: 17.10.2026
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>добавление ${count} пользователей в базу данных</title>
</head>
<body>
<form action="/save" method="POST">

    <c:forEach var="i" begin="1" end="${count}">
        <h3>Пользователь ${i}</h3>
        <input type="text" name="name_${i}" placeholder="Имя" required><br>
        <input type="text" name="surname_${i}" placeholder="Фамилия" required><br>
        <input type="number" name="age_${i}" placeholder="Возраст" required><br>
        <input type="text" name="city_${i}" placeholder="Город"><br>
        <input type="text" name="email_${i}" placeholder="Email"><br>
        <input type="text" name="phone_${i}" placeholder="Телефон"><br>
        <input type="text" name="profession_${i}" placeholder="Профессия"><br>
        <hr>
    </c:forEach>
    <button type="submit">Сохранить всех</button>
</form>
</body>
</html>
