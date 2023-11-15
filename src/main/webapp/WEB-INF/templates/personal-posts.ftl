<#import "macros.ftl" as macros>
<#import "navBar.ftl" as navBar>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Subscriptions</title>
    <style>
        <#include "css/posts.css">
        <#include "css/personal-posts.css">
    </style>

    <@macros.basicDownloadFiles/>
</head>
<body>
<@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

<main class="main-posts">
    <div class="posts">
        <#list posts as post>
            <@macros.singlePost post contextPath fileBuilder option/>
        </#list>
    </div>
</main>

</body>
</html>