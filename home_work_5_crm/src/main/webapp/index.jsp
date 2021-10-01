<%@ page import="by.it_academy.jd2.crm.service.api.IEmployersService" %>
<%@ page import="by.it_academy.jd2.crm.service.EmployersService" %>
<%@ page import="by.it_academy.jd2.crm.service.api.IPositionDepartmentService" %>
<%@ page import="by.it_academy.jd2.crm.service.PositionDepartmentService" %>
<%@ page import="by.it_academy.jd2.crm.service.hibernate.InitHibDB" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

    <title>CRM JD2</title>
</head>
<body>
<h1>CRM JD2</h1>
<h2>В базе данных:</h2>
<table class="table table-hover">
    <tr>
        <%!
            public int getCountEmployers() {
                int count;
                IEmployersService service = EmployersService.getInstance();
                count = service.getCountEmployers();
                return count;
            }

            public int getCountPosition() {
                int count;
                IPositionDepartmentService service = PositionDepartmentService.getInstance();
                count = service.getCount("position");
                return count;
            }

            public int getCountDepartment() {
                int count;
                IPositionDepartmentService service = PositionDepartmentService.getInstance();
                count = service.getCount("department");
                return count;
            }
        %>
        <td><%=getCountEmployers()%></td>
        <td><a href="${pageContext.request.contextPath}/employerslimit">Сотрудников</a></td>
    </tr>
    <tr>
        <td><%=getCountPosition()%></td>
        <td><a href="${pageContext.request.contextPath}/position">Должностей</a></td>
    </tr>
    <tr>

        <td><%=getCountDepartment()%></td>
        <td><a href="${pageContext.request.contextPath}/department">Отделов</a></td>

    </tr>
</table>
<form action="${pageContext.request.contextPath}/generate" method="post">
    <button type="submit" class="btn btn-primary" name="generate">Сгенерировать сотрудников</button>
    <button type="submit" class="btn btn-primary" name="clean">Очистить базу сотрудников</button>
</form>

</body>
</html>
