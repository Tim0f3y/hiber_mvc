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
    <caption>Список пользователей</caption>
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Роль</th>
        <th>Изменить</th>
        <th>Удалить</th>
    </tr>
    <c:forEach var="list"
               items="${list}">
        <tr>
            <td>${list.id}</td>
            <td>${list.name}</td>
            <td>${list.last}</td>
            <td>${list.role}</td>
            <td><a href="/updateuser?id=${list.id}">Изменить</a></td>
            <td><a href="/delete?id=${list.id}">Удалить</a></td>
        </tr>
    </c:forEach>
    <td colspan="2"><button onclick="location.href='/add'">Добавить пользователя
    </button></td>
</table>
</body>
</html>
