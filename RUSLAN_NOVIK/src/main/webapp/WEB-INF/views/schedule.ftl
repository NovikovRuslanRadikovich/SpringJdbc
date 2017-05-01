<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>


<a href="/">На главную</a>
<br>
<a href = "/follow/${cityId}/${polyclinicId}">Назад</a>

<#if schedules??>
<table border="2" width="100%">
    <tr>
        <td>Monday: ${schedules.monday_schedule}</td>
    </tr>
    <tr>
        <td>Tuesday: ${schedules.tuesday_schedule}</td>
    </tr>
    <tr>
        <td>Wednesday: ${schedules.wednesday_schedule}</td>
    </tr>
    <tr>
        <td>Thursday: ${schedules.thursday_schedule}</td>
    </tr>
    <tr>
        <td>Friday: ${schedules.friday_schedule}</td>
    </tr>
    <tr>
        <td>Saturday: ${schedules.saturday_schedule}</td>
    </tr>
    <tr>
        <td>Sunday: ${schedules.sunday_schedule}</td>
    </tr>

</table>
</#if>
</body>
</html>