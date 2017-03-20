<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: розалия
  Date: 19.03.2017
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <h3 align="center">New Contact</h3> <br>
   <form action="/newContact" method="post">
       <input type="text" name="country" placeholder="Country"><br>
       <input type="text" name="name" placeholder="Name"><br>
       <input type="text" name="surname" placeholder="Surname"><br>
       <input type="submit" value="Save"/>
   </form>

</body>
</html>
