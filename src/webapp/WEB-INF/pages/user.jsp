<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1"
       align="center">
    <caption>Данные пользователя</caption>
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Роль</th>
    </tr>
    <c:forEach var="list"
               items="${list}">
        <tr>
            <td>${list.id}</td>
            <td>${list.name}</td>
            <td>${list.last}</td>
            <td>${list.login}</td>
            <td>${list.password}</td>
            <td>${list.role}</td>
        </tr>
    </c:forEach>
    <td colspan="2"><button onclick="location.href='/logout'">Выйти из системы</button></td>
</table>
</body>
</html>
