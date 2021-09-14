<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

    <title>CRM JD2 Карточка сотрудника</title>
</head>
<body>
<h1>CRM JD2</h1>
<h2>Карточка сотрудника</h2>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Показатель</th>
        <th scope="col">Значение</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>id</td>
        <td>${employer.getId()}</td>
    </tr>
    <tr>
        <td>Имя</td>
        <td>${employer.getName()}</td>
    </tr>
    <tr>
        <td>Зарплата</td>
        <td>${employer.getSalary()}</td>
    </tr>
    <tr>
        <td>Должность</td>
        <td>${employer.getPositionName()}</td>
    </tr>
    <tr>
        <td>Отдел</td>
        <td>${employer.getDepartmentName()}</td>
    </tr>
    </tbody>
</table>
<input type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/employer';" value="Назад" /></p>
</body>
</html>
