<%--
  Created by IntelliJ IDEA.
  User: розалия
  Date: 19.03.2017
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3 align="center">Contact List</h3>
    <br>
    <a align="center" href="/newContact" >new contact</a>
    <br>
    <c set var="quantity" value="${quantity}"/>
    <c:if test="${quantity > 0}">
    <table bordercolor="black" border="2" width="100%">
        <tr>
            <td>Number</td>
            <td>Country</td>
            <td>Name</td>
            <td>Surname</td>
            <td>Action</td>
        </tr>
        <c:forEach var="person" items="${allPeople}">
            <tr>
                <td>${person.getId()}"</td>
                <td>${person.getCountry()}</td>
                <td>${person.getName()}</td>
                <td>${person.getSurname()}</td>
                <td><a href="/editContact/${person.getId()}">Редактировать</a> <a href="/deleteContact/${person.getId()}">Удалить</a> </td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
</body>
</html>
