<#import "navBar.ftl" as navBar>
<#import "macros.ftl" as macros>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <@macros.basicDownloadFiles/>
    <style>
        <#include "css/forumAnswers.css">
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
            <#list answers as answer>
                <#assign user = answer.getUser()>

                <div class="answers-single rounded">

                    <div class="answers-single-header">
                        <a class="answers-single-img-block" href="${contextPath}/profile/${user.getLink()}">
                            <img class="answers-img rounded-circle" src="${fileBuilder.getProfilePhotoInBytes(user)}" alt="" title="${user.getName()}">
                        </a>
                    </div>

                    <div class="answers-single-block">
                        <div class="answers-single-block-names">
                            <p>${user.getName()}</p>
                            <p class="answers-single-date">${answer.getDate()?date?string("dd-MM-yyyy")}</p>

                        </div>

                        <p class="answers-single-text">
                            ${answer.getText()}
                        </p>
                    </div>

                </div>
            </#list>

            <#if option.isPresent()>
                <#assign user = option.get()>

                <label class="answers-single rounded">
                    <div class="answers-single-header">
                        <a class="answers-single-img-block" href="${contextPath}/profile/${user.getLink()}">
                            <img class="answers-img rounded-circle" src="${fileBuilder.getProfilePhotoInBytes(user)}" alt="" title="${user.getName()}">
                        </a>
                    </div>
                    <div class="answers-single-block">
                        <div class="answers-single-block-names">
                            <p>${user.getName()}</p>
                            <p class="answers-single-date">${.now?string("dd-MM-yyyy")}</p>

                        </div>
                        <textarea class="answers-single-textarea" placeholder="Your comment..."></textarea>
                    </div>
                </label>

                <input id="comment-submit" type="submit" name="comment-submit" value="Submit">
            </#if>
        </div>
    </main>
</body>
</html>
