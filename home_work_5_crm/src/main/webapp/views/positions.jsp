<%@ page import="by.it_academy.jd2.crm.service.api.IPositionDepartmentService" %>
<%@ page import="by.it_academy.jd2.crm.service.PositionDepartmentService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

    <title>CRM JD2 Список должностей</title>
</head>
<body>
<h1>CRM JD2</h1>
<h2>Должности:</h2>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Наименование</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="position" items="${positions}">
            <tr>
                <td>${position.getId()}</td>
                <td>${position.getName()}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    <input type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/';" value="Назад" /></p>
</body>
</html>
