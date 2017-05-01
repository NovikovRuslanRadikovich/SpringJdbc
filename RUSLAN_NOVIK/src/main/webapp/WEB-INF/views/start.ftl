<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        function Delete_city(j) {

            var data = {
                "id": j
            };
            $.ajax({
                url: "/admin/delete_city/" + j,
                data :data,
                type: "GET"
            }).done(function () {
                $('#city' + j).hide(100);
            })
            ;
        }
        $("#button_add_city").click(function () {

            var data = {
                "name": $("#name").val()
            };
            $.ajax({
                url: "/admin",
                data: data,
                type: "POST"
            }).done(function () {
                alert("Добавлен новый город")
            })
        });
    </script>

</head>
<body>
<@security.authorize access="isAnonymous()">
<a href="/registration">Регистрация</a>
</@security.authorize>
<br>
<@security.authorize access="isAnonymous()">
<a href="/login">Авторизация</a>
</@security.authorize>
<@security.authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
<a href="/logout">Выход</a>
</@security.authorize>
<p>Список городов</p>

<#if cities ??>
    <#list cities as city>
    <div id="city${city.getId()}" class="${city.getId()}">
        <p><a href="/follow/${city.getId()}">Посмотреть поликлиники в городе ${city.getName()}..</a></p>

        <@security.authorize access="hasRole('ROLE_ADMIN')">
            <button id ="${city.getId()}" onclick="Delete_city(${city.getId()})">Удалить город</button>
        </@security.authorize>
    </div>
    </#list>
</#if>
<br>
<@security.authorize access="hasRole('ROLE_ADMIN')">
Новый город:
<br>
<form name="new_city" class="new_city" action="/admin" method="POST">
    <input type="text" class="city_input" name="name" placeholder="Name of city">
    <br>
    <button id="button_add_city">Добавить</button>
</form>
<br>
</@security.authorize>

<@security.authorize access ="hasRole('ROLE_ADMIN')">
Новая поликлиника:
<form name="new_polyclinic" class="new_polyclinic" action="/admin/new_polyclinic" method="POST">
    <input type="text" class="polyclinic_input" name="polyclinic_city" placeholder="City of polyclinic">
    <br>
    <input type="text" class="polyclinic_input" name="polyclinic_name" placeholder="Polyclinic's name">
    <br>
    <input type="text" class="polyclinic_input" name="polyclinic_address" placeholder="Polylinic's address">
    <br>
    <button id="button_add_polyclinic">Добавить</button>
</form>
<br>
</@security.authorize>

<@security.authorize access="hasRole('ROLE_ADMIN')">
Новый врач:
<form name="new_doctor" class="new_doctor" action="/admin/new_doctor" method="POST">
    <input type="text" class="doctor_input" name="doctor_city" placeholder="Doctor's city">
    <br>
    <input type="text" class="doctor_input" name="doctor_polyclinic" placeholder="Doctor's polyclinic">
    <br>
    <input type="text" class="doctor_input" name="fio" placeholder="Doctor's full name">
    <br>
    <input type="text" class="doctor_input" name="specialization" placeholder="Doctor's specialization">
    <br>
    <input type="text" class="doctor_input" name="regalia" placeholder="Doctor's regalia">
    <br>
    Телефон в формате 2xxx-xxx:
    <br>
    <input type="tel" class="doctor_input" name="telephone" pattern="2[0-9]{3}-[0-9]{3}"
           placeholder="telephone 2xxx-xxx">
    <br>
    <input type="text" class="doctor_input" name="monday_schedule" placeholder="Monday schedule">
    <br>
    <input type="text" class="doctor_input" name="tuesday_schedule" placeholder="Tuesday schedule">
    <br>
    <input type="text" class="doctor_input" name="wednesday_schedule" placeholder="Wednesday schedule">
    <br>
    <input type="text" class="doctor_input" name="thursday_schedule" placeholder="Thursday schedule">
    <br>
    <input type="text" class="doctor_input" name="friday_schedule" placeholder="Friday schedule">
    <br>
    <input type="text" class="doctor_input" name="saturday_schedule" placeholder="Saturday schedule">
    <br>
    <input type="text" class="doctor_input" name="sunday_schedule" placeholder="Sunday schedule">
    <input type="submit" value="Добавить">
</form>
</@security.authorize>
</body>
</html>