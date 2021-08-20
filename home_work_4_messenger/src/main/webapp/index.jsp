<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="ru">
      <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

        <title>Messenger</title>
      </head>

      <body>
          <h1>Messenger</h>
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <h3>Привет, ${sessionScope.user.fio}!</h3>
                </c:when>
                <c:otherwise>
                    <h3>Привет, пользователь!</h3>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/signUp';" value="Зарегистрироваться" /></p>
                    <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/signIn';" value="Войти" /></p>
                </c:when>
                <c:otherwise>
                    <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/chats';" value="Просмотреть свои сообщения" /></p>
                    <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/message';" value="Отправить сообщения" /></p>
                    <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/logOut';" value="Выйти" /></p>
                </c:otherwise>
            </c:choose>
      </body>


 </html>