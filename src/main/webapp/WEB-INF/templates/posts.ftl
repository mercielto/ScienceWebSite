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

    <main>
        <div class="main-posts">
            <@macros.settings themes=themes/>


            <div class="posts" id="posts_">
                <#list posts as post>
                    <@macros.singlePost post contextPath fileBuilder option/>
                </#list>
            </div>
        </div>

        <div id="more-posts">
            <button id="need-more" class="rounded">
                I NEED MORE!
            </button>
        </div>
    </main>

    <@macros.footer contextPath/>
    <#include "js/searchFilterPost.jsp">
</body>
</html>