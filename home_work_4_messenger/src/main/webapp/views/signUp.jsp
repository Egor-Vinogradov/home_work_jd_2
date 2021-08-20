<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

        <title>Регистрация</title>
    </head>
    <body>
    <c:choose>
        <c:when test="${requestScope.error}">
            <p style="color:red;">${requestScope.message}</p>
        </c:when>
        <c:otherwise>
            <p>Зарегестрируйтесь</p>
        </c:otherwise>
    </c:choose>
            <form action="${pageContext.request.contextPath}/signUp" method="POST">
                <table>
                    <tbody>
                        <tr>
                            <td>Логин:</td>
                            <td>
                                <input type="text" name="login">
                            </td>
                        </tr>
                        <tr>
                            <td>Пароль:</td>
                            <td>
                                <input type="password" name="pass">
                            </td>
                        </tr>
                        <tr>
                            <td>ФИО:</td>
                            <td>
                                <input type="text" name="fio">
                            </td>
                        </tr>
                        <tr>
                            <td>Дата рождения:</td>
                            <td>
                                <input type="date" name="date">
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p><input type="submit" value="Зарегестрироваться"/></p>
            </form>
        </body>
</html>