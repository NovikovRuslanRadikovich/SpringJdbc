<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<form name="user" action="/login" method="POST">
    <div class="form-group row">
        <label for="nickname" class="col-sm-2 col-form-label">Имя:</label>
        <div class="col-sm-10">
            <input type="text" name="login_username" class="form-control" id="name" placeholder="Username"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 control-label">Пароль:</label>
        <div class="col-sm-10">
            <input type="password" name="login_password" class="form-control"
                   placeholder="Password"/>
        </div>
    </div>
    <div class="form-group row">
        <div class="offset-sm-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Вход</button>
        </div>
    </div>

<#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
   <strong> ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}</strong>
</#if>


</form>
</body>
</html>