
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
<h1>CRM JD2</h1>
<h2>Добавление сотрудника</h2>
<form id="1" action="${pageContext.request.contextPath}/api/new" method="post">
<table class="table table-hover">
    <tbody>
        <tr>
            <td>Имя:</td>
            <td>
                <input class="form-control" type="text" name="name" placeholder="Введите имя..." aria-label="default input example">
            </td>
        </tr>
        <tr>
            <td>Зарплата:</td>
            <td>
                <input class="form-control" type="number" name="salary" placeholder="Введите зарплату..." aria-label="default input example">
            </td>
        </tr>
        <tr>
            <td>Отдел:</td>
            <td>
                <select class="form-select" aria-label="Default select example" name="department">
                    <option selected>Выберите отдел...</option>
                        <c:forEach var="department" items="${departments}">
                            <option value="${department.getId()}">${department.getName()}</option>
                        </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Должность:</td>
            <td>
                <select class="form-select" aria-label="Default select example" name="position">
                    <option selected>Выберите должность...</option>
                    <c:forEach var="position" items="${positions}">
                        <option value="${position.getId()}">${position.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </tbody>
</table>
    <input type="submit" class="btn btn-primary" value="Добавить сотрудника" /></p>
</form>
<script type="text/javascript">
    document.getElementById('1').addEventListener('submit', submitForm);
    function submitForm(event) {
        // Отменяем стандартное поведение браузера с отправкой формы
        event.preventDefault();
        // event.target — это HTML-элемент form
        let formData = new FormData(event.target);
        // Собираем данные формы в объект
        let obj = {};
        formData.forEach(
            (value, key) => {
                if(key.includes('.')){
                    var partName = key.split(".");
                    obj[partName[0]] = {};
                    obj[partName[0]][partName[1]] = value;
                } else {
                    obj[key] = value;
                }
            }
        );
        // Собираем запрос к серверу
        let request = new Request(event.target.action, {
            method: 'POST',
            body: JSON.stringify(obj),
            headers: {
                'Content-Type': 'application/json',
            },
        });
        // Отправляем (асинхронно!)
        fetch(request).then(
            function(response) {
                // Запрос успешно выполнен
                console.log(response);
                // return response.json() и так далее см. документацию
            },
            function(error) {
                // Запрос не получилось отправить
                console.error(error);
            }
        );
        // Код после fetch выполнится ПЕРЕД получением ответа
        // на запрос, потому что запрос выполняется асинхронно,
        // отдельно от основного кода
        console.log('Запрос отправляется');
    }
</script>
<input type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/';" value="Назад" /></p>
</html>
