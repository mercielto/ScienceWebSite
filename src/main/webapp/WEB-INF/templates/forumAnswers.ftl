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
                <div class="answers-single-div">
                    <#if option.isPresent()>

                        <#assign user = option.get()>

                        <#if author.getId() == user.getId()>
                            <label>
                                <input type="radio" name="mark-as-correct" value="${question.getId()}">

                                    <#if question.getAnswerId()?has_content>
                                        <#if answer.getId() != question.getAnswerId()>
                                            <img class="answers-img-mark" src="${fileBuilder.getServicePhotoInBytes("check-mark_not_selected.png")}" alt="Mark as correct">
                                        <#else>
                                            <img class="answers-img-mark" src="${fileBuilder.getServicePhotoInBytes("check-mark_selected.png")}" alt="Correct answer">
                                        </#if>
                                    <#else>
                                        <img class="answers-img" src="${fileBuilder.getServicePhotoInBytes("check-mark_not_selected.png")}" alt="Mark as correct">
                                    </#if>
                            </label>
                        <#else>
                            <#if answer.getId() == question.getAnswerId()>
                                <img title="This answer has been marked as correct" class="answers-img-mark" src="${fileBuilder.getServicePhotoInBytes("check-mark_selected.png")}" alt="Correct answer">
                            </#if>
                        </#if>
                    <#else>
                        <#if answer.getId() == question.getAnswerId()>
                            <img title="This answer has been marked as correct" class="answers-img-mark" src="${fileBuilder.getServicePhotoInBytes("check-mark_selected.png")}" alt="Correct answer">
                        </#if>
                    </#if>
                    <@macros.singleAnswer answer contextPath fileBuilder/>
                </div>
            </#list>
        </div>
    </main>
    <@macros.footer contextPath/>
    <#include "js/forumQuestionAnswer.jsp">
</body>
</html>
