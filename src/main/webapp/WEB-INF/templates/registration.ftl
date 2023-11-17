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
            <label class="rounded">Enter login:</label>
            <input class="rounded" type='text' name='login' minlength="8">
            <label class="rounded" >Enter password:</label>
            <input class="rounded" type='password' name='password1'>
            <label class="rounded">Repeat password:</label>
            <input class="rounded" type='password' name='password2'><br>
            <input class="login-registration-button rounded" type="submit" value="Register"><br>
        </form>
    </body>

    <@macros.footer contextPath/>
</html>
