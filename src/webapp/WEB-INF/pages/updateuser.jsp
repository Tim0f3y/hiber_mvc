<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<div align="center">
    <h2>Изменить пользователя</h2>
    <form:form action="update" method="post" modelAttribute="editUser">
        <table>
            <tr>
                <td>ID: </td>
                <td>${editUser.id}
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>Имя: </td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Фамилия: </td>
                <td><form:input path="last" /></td>
            </tr>
            <tr>
                <td>Роль: </td>
                <td><form:input path="role" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
