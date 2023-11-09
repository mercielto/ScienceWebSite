<#import "navBar.ftl" as navBar>
<#import "macros.ftl" as macros>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <@macros.basicDownloadFiles/>
    <style>
        <#include "css/main-page.css">
    </style>
</head>
<body>
    <@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

    <div id="page-main">

        <div id="page-main-title">
            <p>MABR</p>
            <p>&</p>
            <p>DABR</p>
        </div>

        <div id="page-main-text">
            MABR & DABR is a site on a popular science topic. Various articles related to one or another topic provided on the site are published here. Users can ask their questions on the local forum. Only authorized users can respond to them.
            <br>If you are not with us yet, then do not despair, you can join right now!
        </div>

        <div id="page-main-blocks">
            <#list blocks as block>
                <div class="page-main-blocks-single">
                    <p class="page-main-blocks-single-title">
                        ${block.getTitle()}
                    </p>

                    <p class="page-main-blocks-single-count">
                        ${block.getCount()}
                    </p>
                </div>
            </#list>
        </div>

    </div>
</body>
</html>
