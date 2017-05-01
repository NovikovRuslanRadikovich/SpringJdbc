<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Polyclinics</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    function Delete_polyclinic(i) {

        var data = {
            "id": i
        };

        $.ajax({
            url: "/admin/delete_polyclinic/" + i,
            data: data,
            type: "GET"
        }).done(function(data){
            $('#polyclinic'+i).hide(400);
        })
        ;
    }

</script>
</head>
<body>

<a href="/">На главную</a>


<#if polyclinics ??>
    <#list polyclinics as polyclinic>
    <div id="polyclinic${polyclinic.getId()}">
       <p><a href="/follow/${cityId}/${polyclinic.getId()}">Посмотреть врачей в поликлинике ${polyclinic.getPolyclinic_name()}</a></p>
        <@security.authorize access="hasRole('ROLE_ADMIN')">
         <button onclick="Delete_polyclinic(${polyclinic.getId()})">Удалить поликлинику</button>
       </@security.authorize>
    </div>
    </#list>
</#if>
<br>

</body>
</html>