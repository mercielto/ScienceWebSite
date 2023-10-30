<#import "navBar.ftl" as navBar>
<#import "macros.ftl" as macros>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <@macros.basicDownloadFiles/>
        <style>
            <#include "css/forumMain.css">
        </style>
    </head>

    <body>
        <@navBar.navBar option=option contextPath=contextPath imageBuilder=imageBuilder/>

        <main class="forum-body">
            <div class="forum-settings rounded">
                <p id="forum-settings-title">Settings</p>

                <p>Date:</p>
                <div class="forum-settings-date">
                    <span class="date-choose">
                        <label>
                            From
                            <input type="date" name="date-from">
                        </label>
                        <label>
                            to
                            <input type="date" name="date-to">
                        </label>
                    </span>
                </div>

                <p>Category:</p>
                <div class="forum-settings-theme">
                    <#list themes as theme>
                        <label class="forum-settings-theme-label">
                            <input type="checkbox" class="rounded-circle">
                            ${theme.getName()}
                        </label>
                    </#list>
                </div>


                <p>
                    <label>
                        Tags:
                        <input type="text">
                    </label>
                </p>

                <p>
                    <label>
                        Number of responses
                        <input type="text" onkeyup="this.value = this.value.replace(/[^\d]/g,'');">
                    </label>
                </p>

            </div>

            <div class="forum-questions">
                <#list questions as question>
                    <#assign user = question.getUser()>
                    <div class="forum-questions-single rounded-pill">
                        <a href="${contextPath}/profile/${user.getLink()}" class="forum-questions-img-href">
                            <img src="${imageBuilder.getProfilePhotoInBytes(user)}"
                                 alt="${user.getName()}" class="forum-questions-img rounded-circle">
                        </a>
                        <a href="${contextPath}/forum/${question.getLink()}" class="forum-question-title">
                            <h4>${question.getMainQuestion()}</h4>
                        </a>

                        <div class="forum-questions-tags">
                            <#assign tags = question.getTags()>
                            <#list tags as tag>
                                <p>#${tag}</p>
                            </#list>
                        </div>

                        <div class="forum-questions-comment">
                            <img class="forum-questions-comment-img" src="${imageBuilder.getServicePhotoInBytes("comment.png")}" alt="comments">
                            <p class="forum-questions-comment-count">
                                ${question.getQuestionAnswers()?size}
                            </p>
                        </div>
                    </div>
                </#list>

                <nav aria-label="">
                    <ul class="pagination forum-questions-pagination">
                        <li class="page-item">
                            <button class="forum-questions-button"><span aria-hidden="true">&laquo;</span></button>
                        </li>

                        <#list pageNumbers as number>
                            <#if number == 1>
                                <#assign active = "forum-questions-button-active">
                            <#else>
                                <#assign active = "">
                            </#if>

                            <li class="page-item" ><button class="forum-questions-button">${number}</button></li>
                        </#list>

                        <li class="page-item">
                            <button class="forum-questions-button"><span aria-hidden="true">&raquo;</span></button>
                        </li>
                    </ul>
                </nav>
            </div>
        </main>
    </body>
</html>
