<#macro basicDownloadFiles>
    <style>
        <#include "css/main.css">
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</#macro>


<#macro profileAttributeUsers listOfUser topic fileBuilder>
    <div>
        <p data-bs-toggle="modal" data-bs-target="#modal${topic}" class="modal-text">
            ${topic}
            ${listOfUser?size}
        </p>

        <div class="modal fade" id="modal${topic}" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title modal-header-title">${topic}</h5>
                    </div>
                    <div class="modal-body">
                        <div class="profile-attributes-list-user">
                            <#list listOfUser as user>
                                <a href="${contextPath}/profile/${user.getLink()}" class="modal-attributes-text">
                                    <img src="${fileBuilder.getProfilePhotoInBytes(user)}" class="profile-attributes-img rounded-circle" alt="ava">
                                    ${user.getName()}
                                </a>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro settings themes>
    <div class="forum-settings rounded">
        <p id="forum-settings-title">Settings</p>

        <div class="forum-settings-date">
            <span class="date-choose">
                <label>
                    From
                    <input type="date" name="date-from" id="date-from">
                </label>
                <label>
                    to
                    <input type="date" name="date-to" id="date-to">
                </label>
            </span>
        </div>

        Category:
        <div class="forum-settings-theme">
            <#list themes as theme>
                <label class="forum-settings-theme-label">
                    <input type="checkbox" name="${theme.getName()}" class="checkbox rounded-circle">
                    ${theme.getName()}
                </label>
            </#list>
        </div>


        <div id="tags-block">
            <label>
                Tags:
                <input type="text" id="tags">
            </label>

<#--            when enter is pressed, tags goes to this div by js-->
            <div id="tags-list">

            </div>
        </div>

        <#nested>

        <input type="submit" value="Filter" id="filter">
    </div>
</#macro>

<#macro singlePost post contextPath fileBuilder authorizedUser>
    <div class="posts-single rounded">
    <#assign user = post.getUser()>
    <div class="posts-single-header">
        <a class="posts-single-header-img" href="${contextPath}/profile/${user.getLink()}">
            <img class="posts-single-user-img rounded-circle" src="${fileBuilder.getProfilePhotoInBytes(user)}" alt="">
        </a>

        <div class="posts-single-header-names">
            <p>${user.getName()}</p>
            <p>${post.getDate()?date?string("dd-MM-yyyy")}</p>
        </div>
    </div>

    <a href="${contextPath}/posts/${post.getLink()}">
        <h4 class="posts-single-title">${post.getTitle()}</h4>
    </a>

    <div class="posts-single-text">
        <p>${fileBuilder.getPostText(post.getPathInStorage())}</p>
    </div>

    <div class="posts-single-footer">
        <div class="posts-single-params">
            <div class="posts-single-params-likes">

                <button class="posts-single-params-btn">
                    <#assign likes = post.getLikes()>
                    <#assign source = "not_liked.png">

                    <#if authorizedUser.isPresent()>
                        <#assign authUser = authorizedUser.get()>
                        <#list likes as like>
                            <#if like.getUser().getId() == authUser.getId()>
                                <#assign source = "liked.png">
                                <#break>
                            </#if>
                        </#list>
                    </#if>

                    <img class="posts-single-params-img" onclick="" src="${fileBuilder.getServicePhotoInBytes(source)}" alt="like">
                </button>
                ${post.getLikes()?size}
            </div>

            <div class="posts-single-params-comments">
                <button class="posts-single-params-btn">
                    <img class="posts-single-params-img" src="${fileBuilder.getServicePhotoInBytes("comment.png")}" alt="comment">
                </button>
                ${post.getComments()?size}
            </div>
        </div>

        <div>
            <div class="posts-single-tags params">
                <#list post.getTags() as tag>
                    <p>#${tag}</p>
                </#list>
            </div>

            <div class="posts-single-theme params">
                <p class>${post.getTheme().getName()}</p>
            </div>
        </div>
    </div>


</div>
</#macro>

<#macro singleAnswer answer contextPath fileBuilder >
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
</#macro>

<#macro singleAnswerInputField option contextPath fileBuilder>
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
                <textarea id="textArea" class="answers-single-textarea" placeholder="Your comment..."></textarea>
            </div>
        </label>

        <input id="comment-submit" type="submit" name="comment-submit" onclick="submit()" value="Submit">
    </#if>
</#macro>

<#macro profileAttributePosts topic listOfPost contextPath>
    <div>
        <p data-bs-toggle="modal" data-bs-target="#modal${topic}" class="modal-text">
            ${topic}
            ${listOfPost?size}
        </p>

        <div class="modal fade" id="modal${topic}" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title modal-header-title">${topic}</h5>
                    </div>
                    <div class="modal-body">
                        <ul class="profile-attributes-list-post">
                            <#list listOfPost as post>
                                <li><a href="${contextPath}/posts/${post.getLink()}">${post.getTitle()}</a></li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#--    css -->
<#macro addNewSign themes fileBuilder>
    <div id="my-posts-write-new">
        <button id="my-posts-new-button" data-toggle="modal" data-target=".bd-example-modal-lg">
            <img class="my-posts-write-new-img" src="${fileBuilder.getServicePhotoInBytes("add.png")}">
        </button>

        <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="my-post-new-header">Express your thoughts</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="post" class="my-posts-write-new-form">
                            <h4><input name="title" id="my-posts-form-title" type="text" placeholder="Title"></h4>

                            <div id="my-posts-form-params">
                                <select name="theme" id="my-posts-params-theme">
                                    <#list themes as theme>
                                        <option>${theme.getName()}</option>
                                    </#list>
                                </select>

                                <input name="tags" type="text" placeholder="tags">
                            </div>

                            <textarea name="text" id="my-posts-textarea"></textarea>

                            <div id="my-posts-form-submit">
                                <input type="submit">
                            </div>
                        </form>
                    </div>


                </div>
            </div>
        </div>
    </div>
</#macro>

<#--    params "post": post - true, question - false-->
<#macro postList post contextPath fileBuilder objects>
    <#list objects as object>
        <div class="my-posts-block">
            <div class="my-posts-post rounded">
                <#if post>
                    <#assign link = contextPath + "/posts/" + object.getLink()>
                <#else>
                    <#assign link = contextPath + "/forum/" + object.getLink()>
                </#if>
                <a class="my-posts-title" href="${link}">
                    <h4>
                        <#if post>
                            ${object.getTitle()}
                        <#else>
                            ${object.getMainQuestion()}
                        </#if>
                    </h4>
                </a>

                <div class="my-posts-params">
                    <#if post>
                        <div class="my-posts-like">
                            <img class="my-posts-params-img" src="${fileBuilder.getServicePhotoInBytes("not_liked.png")}" alt="">
                            ${object.getLikes()?size}
                        </div>
                    </#if>

                    <div class="my-posts-comment">
                        <img class="my-posts-params-img" src="${fileBuilder.getServicePhotoInBytes("comment.png")}" alt="">
                        <#if post>
                            ${object.getComments()?size}
                        <#else>
                            ${object.getQuestionAnswers()?size}
                        </#if>
                    </div>
                </div>
            </div>

            <button class="my-posts-deletion-button" onclick="deleteObject(event)">
                <img class="my-posts-deletion" src="${fileBuilder.getServicePhotoInBytes("trashcan_delete.png")}">
            </button>


        </div>
    </#list>
</#macro>

<#macro footer contextPath>
    <!-- Footer -->
    <footer class="footer bg-light text-center">

        <!-- Copyright -->
        <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
            © 2023 Copyright:
            <a class="text-dark" href="${contextPath}">MABR & DABR</a>
        </div>
        <!-- Copyright -->

    </footer>
</#macro>

<#macro singleQuestion contextPath question fileBuilder>
    <#assign user = question.getUser()>
    <div class="forum-questions-single rounded-pill">
        <a href="${contextPath}/profile/${user.getLink()}" class="forum-questions-img-href">
            <img src="${fileBuilder.getProfilePhotoInBytes(user)}"
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
            <img class="forum-questions-comment-img" src="${fileBuilder.getServicePhotoInBytes("comment.png")}" alt="comments">
            <p class="forum-questions-comment-count">
                ${question.getQuestionAnswers()?size}
            </p>
        </div>
    </div>
</#macro>