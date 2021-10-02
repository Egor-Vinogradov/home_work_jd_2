<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

    <title>CRM JD2 Список сотрудников</title>
</head>
<body>
<h1>CRM JD2</h1>
<h2>Сотрудники:</h2>
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">Имя</th>
            <th scope="col">Зарплата</th>
            <th scope="col">Должность</th>
            <th scope="col">Отдел</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${requestScope.search == true}">
                <form method="get" action="${pageContext.request.contextPath}/search">
                    <td></td>
                    <td>
                        <input class="form-control form-control-sm" name="name" type="text" placeholder="Введите имя" aria-label=".form-control-sm example">
                    </td>
                    <td>
                        <div class="input-group col-md-8 col-xs-12 form-inline">
                            <div class="input-group" style="width: 49%;">
                                <input name="from" class="form-control form-control-sm" type="text" placeholder="От">
                            </div>
                            <div class="input-group" style="width: 49%;">
                                <input name="to" class="form-control form-control-sm" type="text" placeholder="До">
                            </div>
                        </div>
                    </td>
                    <td>
                        <input type="submit" class="btn btn-primary" value="Поиск"/>
                    </td>
                </form>
            </c:when>
        </c:choose>
    </tbody>
    <tbody>
        <c:forEach var="employer" items="${employers}">
            <tr>
                <td><a href="${pageContext.request.contextPath}/employercard?id=${employer.getId()}">${employer.getId()}</a></td>
                <td><a href="${pageContext.request.contextPath}/employercard?id=${employer.getId()}">${employer.getName()}</a></td>
                <td><fmt:formatNumber type="number" maxIntegerDigits="15" value="${employer.getSalary()}" /></td>
                <td>${employer.getPositionName()}</td>
                <td>${employer.getDepartmentName()}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    <c:choose>
        <c:when test="${requestScope.search == true}">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/employerslimit?position=first" role="button"><<</a>
            &nbsp
            <c:forEach var="i" begin="${requestScope.firstButton}" end="${requestScope.endButton}">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/employerslimit?offset=${i}" role="button">${i}</a>
            </c:forEach>
            &nbsp
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/employerslimit?position=last" role="button">>></a>
        </c:when>
        <c:otherwise>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/search?position=first&name=${requestScope.name}&from=${requestScope.from}&to=${requestScope.to}" role="button"><<</a>
            &nbsp
            <c:forEach var="i" begin="${requestScope.firstButton}" end="${requestScope.endButton}">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/search?offset=${i}&name=${requestScope.name}&from=${requestScope.from}&to=${requestScope.to}" role="button">${i}</a>
            </c:forEach>
            &nbsp
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/search?position=last&name=${requestScope.name}&from=${requestScope.from}&to=${requestScope.to}" role="button">>></a>
        </c:otherwise>
    </c:choose>
<br/>
<br/>
    <input type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/';" value="Назад" /></p>
</body>
</html>
