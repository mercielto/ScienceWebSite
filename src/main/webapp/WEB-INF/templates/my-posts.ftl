<#import "navBar.ftl" as navBar>
<#import "macros.ftl" as macros>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <@macros.basicDownloadFiles/>
    <title>My-Posts</title>
    <style>
        <#include "css/my-posts.css">
        <#include "css/add-new-sign.css">
    </style>
</head>
<body>
    <@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

    <div id="my-posts-main">
        <@macros.addNewSign themes fileBuilder/>

        <#if posts?size == 0>
            <div class="">
                <h3>You haven't written any posts yet</h3>
            </div>
        <#else>
            <@macros.postList true contextPath fileBuilder posts/>
        </#if>
    </div>

    <@macros.footer contextPath/>
    <#include "js/myPosts.jsp">
</body>
</html>
