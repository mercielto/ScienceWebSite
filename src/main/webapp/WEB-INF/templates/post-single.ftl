<#import "macros.ftl" as macros>
<#import "navBar.ftl" as navBar>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${post.getTitle()}</title>
    <style>
        <#include "css/post-single.css">
        <#include "css/searchParams.css">
        <#include "css/posts.css">
        <#include "css/singleAnswer.css">
    </style>

    <@macros.basicDownloadFiles/>
</head>
<body>
    <@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

    <main class="post-single">
        <@macros.singlePost post contextPath fileBuilder option/>

        <div class="post-single-comments">
            <@macros.singleAnswerInputField option contextPath fileBuilder/>

            <#list post.getComments() as comment>
                <@macros.singleAnswer comment contextPath fileBuilder/>
            </#list>

        </div>
    </main>

</body>
</html>