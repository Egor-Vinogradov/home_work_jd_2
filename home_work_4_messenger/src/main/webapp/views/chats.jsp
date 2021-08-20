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
          <table border="1">
              <tbody>
              <c:forEach items="${requestScope.chat}"
                        var="message">
                  <tr>
                      <td width="20%">${message.from}</td>
                      <td width="20%"><fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${message.sendDate}" /></td>
                      <td width="60%">${message.text}</td>
                  </tr>
              </c:forEach>
              </tbody>
          </table>
          <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Возврат" /></p>
      </body>

</html>