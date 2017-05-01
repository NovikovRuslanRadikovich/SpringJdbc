<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Doctors</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        function Delete_doctor(i) {
            var data = {
                "id": i
            };
            $.ajax({
                url: "/admin/delete_doctor/" + i,
                type: "GET"
            }).done(function(data){
                $('#doctor'+i).hide(400);
            })
        }


    </script>
</head>
<body>


<a href="/">На главную</a>
<br>
<a href="/follow/${cityId}">Назад</a>
<#if doctors ??>
    <#list doctors as doctor>
    <div id="doctor${doctor.getId()}">
       <p><a href="/follow/${cityId}/${polyclinicId}/${doctor.getId()}">Посмотреть расписание врача ${doctor.getFio()}..</a></p>
        <@security.authorize access="hasRole('ROLE_ADMIN')">
           <button onclick="Delete_doctor(${doctor.getId()})">Удалить врача</button>
       </@security.authorize>
    </div>
    </#list>
</#if>
<br>
</body>
</html>