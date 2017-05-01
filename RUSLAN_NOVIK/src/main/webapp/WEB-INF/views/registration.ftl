
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <title>Authorization</title>


</head>
<body>

    <h2>Создать аккаунт</h2>

    <form name = "user" action="/registration" method="POST">
        <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="username">Имя:</label></th>
                <td><input type = "text" name="username" size="15" maxlength="15" id="username"/><br>
                </td>
            </tr>
            <tr>
                <th><label for="password">Пароль:</label></th>
                <td><input type = "password" name="password" size="30" id="password"/><br>
                </td>
            </tr>
        </table>
            <button type="submit">Отправить</button>
        </fieldset>

    <#if errors??>
        <#list errors as error>
            <div class="alert alert-danger">
                <strong>${error.defaultMessage}</strong>
            </div>
        </#list>
    </#if>

    <#if existingName??>
        <strong>${existingName}</strong>
    </#if>

    </form>
</body>
</html>