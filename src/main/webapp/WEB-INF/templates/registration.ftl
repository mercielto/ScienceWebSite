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
        <@navBar.navBbar option=option contextPath=contextPath helpers=helpers/>

        <form method="post" class="form-position">
            <p>Enter login:</p>
            <input type='text' name='login'>
            <p>Enter password:</p>
            <input type='text' name='password1'>
            <p>Repeat password:</p>
            <input type='text' name='password2'><br>
            <input type="submit" value="Register"><br>
        </form>
    </body>
</html>
