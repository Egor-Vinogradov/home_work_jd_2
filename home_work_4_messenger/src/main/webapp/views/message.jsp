<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

        <title>Сообщения</title>
    </head>

    <body>
        <c:choose>
            <c:when test="${requestScope.success}">
                    <p style="color:green;">Сообщение отправленно.</p>
            </c:when>
            <c:when test="${requestScope.error}">
                    <p style="color:red;">${requestScope.message}</p>
            </c:when>
            <c:otherwise>
                <p>${sessionScope.user.fio}, отправь сообщение!</p>
            </c:otherwise>
        </c:choose>
            <form action="${pageContext.request.contextPath}/message" method="POST">
                <table>
                    <tbody>
                        <tr>
                            <td>Получатель:</td>
                            <td>
                                <input type="text" name="recipient" value="${param.recipient}">
                            </td>
                        </tr>
                        <tr>
                            <td>Текст:</td>
                            <td>
                                <input type="text" name="text" value="${param.text}">
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p><input type="submit" value="Отправить"/><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Возврат" /></p>
            </form>
        </body>

</html>