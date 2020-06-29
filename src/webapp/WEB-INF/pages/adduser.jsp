<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<div align="center">
    <h2>Новый пользователь</h2>
    <form:form action="save" method="post" modelAttribute="user">
        <table>
            <tr>
                <td>Имя:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Фамилия:</td>
                <td><form:input path="last"/></td>
            </tr>
            <tr>
                <td>Логин:</td>
                <td><form:input path="login"/></td>
            </tr>

            <tr>
                <td>Пароль:</td>
                <td><form:input path="password"/></td>
            </tr>
            <tr>
                <td>Роль:</td>
                <td><input type="checkbox" name="option" value="ROLE_ADMIN">Администратор<Br>
                    <input type="checkbox" name="option" value="ROLE_USER">Пользователь<Br></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
