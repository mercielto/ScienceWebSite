<#macro basicDownloadFiles>
    <style>
        <#include "css/main.css">
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
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
                    <input type="date" name="date-from">
                </label>
                <label>
                    to
                    <input type="date" name="date-to">
                </label>
            </span>
        </div>

        Category:
        <div class="forum-settings-theme">
            <#list themes as theme>
                <label class="forum-settings-theme-label">
                    <input type="checkbox" name="${theme.getName()}" class="rounded-circle">
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

        <#nested>

        <input type="submit" value="Filter">
    </div>
</#macro>

<#macro singlePost post contextPath fileBuilder>
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
                    <img class="posts-single-params-img" src="${fileBuilder.getServicePhotoInBytes("like1.png")}" alt="like">
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
                <textarea class="answers-single-textarea" placeholder="Your comment..."></textarea>
            </div>
        </label>

        <input id="comment-submit" type="submit" name="comment-submit" value="Submit">
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