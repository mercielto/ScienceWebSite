<#import "navBar.ftl" as navBar>
<#import "macros.ftl" as macros>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <@macros.basicDownloadFiles/>
    <style>
        <#include "css/login-registration.css">
    </style>
</head>
<body>
    <@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

    <form method="post" class="form-position rounded">
        <p>Enter login:</p>
        <input class="rounded" type='text' name='login'>
        <p>Enter password:</p>
        <input class="rounded" type='text' name='password'><br>
        <div id="remember-me">
            <input class="rounded" type='checkbox' name='remember_me'>Remember me<br>
        </div>
        <input class="rounded login-registration-button" type="submit" value="Login"><br>
    </form>
</body>
</html>
