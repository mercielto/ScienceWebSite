<#import "navBar.ftl" as navBar>
<#import "macros.ftl" as macros>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <@macros.basicDownloadFiles/>
    <title>My-questions</title>
    <style>
        <#include "css/my-posts.css">
        <#include "css/add-new-sign.css">
    </style>
</head>
<body>
<@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

<div id="my-posts-main">
    <@macros.addNewSign themes fileBuilder/>

    <#if questions?size == 0>
        <div class="">
            <h3>You haven't asked any questions yet</h3>
        </div>
    <#else>
        <@macros.postList false contextPath fileBuilder questions/>
    </#if>
</div>
<@macros.footer contextPath/>
<#include "js/myQuestions.jsp">

</body>

</html>
