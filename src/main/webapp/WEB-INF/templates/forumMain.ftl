<#import "navBar.ftl" as navBar>
<#import "macros.ftl" as macros>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <@macros.basicDownloadFiles/>
        <style>
            <#include "css/forumMain.css">
            <#include "css/searchParams.css">
        </style>
    </head>

    <body>
        <@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

        <main class="forum-body">
            <@macros.settings themes=themes/>

            <div id="questions-div">
                <div id="forum-questions">
                    <#list questions as question>
                        <@macros.singleQuestion contextPath question fileBuilder/>
                    </#list>
                </div>
                <div id="more-posts">
                    <button id="need-more" class="rounded">
                        I NEED MORE!
                    </button>
                </div>
            </div>
        </main>
        <@macros.footer contextPath/>
        <#include "js/searchFilterQuestion.jsp">
    </body>
</html>
