<#import "navBar.ftl" as navBar>
<#import "macros.ftl" as macros>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <@macros.basicDownloadFiles/>
    <style>
        <#include "css/forum-single.css">
        <#include "css/singleAnswer.css">
    </style>
</head>

<body>
    <@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

    <main class="">
        <#assign author = question.getUser()>
        <div class="question rounded">
            <div class="question-header">
                <a class="question-profile" href="${contextPath}/profile/${author.getLink()}">
                    <img class="question-profile-img rounded-circle" src="${fileBuilder.getProfilePhotoInBytes(author)}" alt="profile">
                    <p class="question-profile-name">${author.getName()}</p>
                </a>

                <div class="question-title">
                    <h2 class="question-title-text">${question.getMainQuestion()}</h2>

                    <div class="question-title-parameters">

                        <p>${question.getTheme().getName()}</p>

                        <div class="question-title-parameters-tags">
                            <#list question.getTags() as tag>
                                <p class="question-title-parameters-tags-single">#${tag}</p>
                            </#list>
                        </div>
                        <p>${question.getDate()?date?string("dd-MM-yyyy")}</p>

                    </div>
                </div>
            </div>

            <div class="question-text">
                ${question.getText()}
            </div>
        </div>



        <div class="answers">
            <@macros.singleAnswerInputField option contextPath fileBuilder/>

            <#list answers as answer>
                <@macros.singleAnswer answer contextPath fileBuilder/>
            </#list>
        </div>
    </main>
</body>
</html>
