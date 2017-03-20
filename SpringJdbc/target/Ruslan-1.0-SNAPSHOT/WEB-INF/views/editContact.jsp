<%--
  Created by IntelliJ IDEA.
  User: розалия
  Date: 19.03.2017
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3 align="center">Edit Contact</h3> <br>
    <form action="/editContact/${person.getId()}" method="post">
        <input type="text" name="country" value="${person.getCountry()}"/><br>
        <input type="text" name="name" value="${person.getName()}"/><br>
        <input type="text" name="surname" value="${person.getSurname()}"/><br>
        <input type="submit" value="Save"/>
    </form>
</body>
</html>
