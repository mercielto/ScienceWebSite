<#import "macros.ftl" as macros>
<#import "navBar.ftl" as navBar>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        <#include "css/posts.css">
        <#include "css/searchParams.css">
    </style>

    <@macros.basicDownloadFiles/>
</head>
<body>
    <@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

    <main class="main-posts">
        <@macros.settings themes=themes>
            <label class="settings-likes">
                Likes
                <input type="text" onkeyup="this.value = this.value.replace(/[^\d]/g,'');">
            </label>
        </@macros.settings>


        <div class="posts">
            <#list posts as post>
                <@macros.singlePost post contextPath fileBuilder/>
            </#list>
        </div>


    </main>

</body>
</html>