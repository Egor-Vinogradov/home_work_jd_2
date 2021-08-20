<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

        <title>Авторизация</title>
    </head>
    <body>
        <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <p>Вы уже вошли, может хотите выйти?</p>
                    <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/logOut';" value="Выйти"/></p>
                </c:when>
            <c:otherwise>
                <c:choose>
                        <c:when test="${requestScope.error}">
                            <p style="color:red;">${requestScope.message}</p>
                        </c:when>
                    <c:otherwise>
                        <p>Введите логин и пароль:</p>
                    </c:otherwise>
                </c:choose>
                        <form action="${pageContext.request.contextPath}/signIn" method="POST">
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
                                            <input type="password" name="password">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <p><input type="submit" value="Войти"/></p>
                        </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>