<#import "macros.ftl" as macros>
<#import "navBar.ftl" as navBar>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        <#include "css/posts.css">
        <#include "css/searchParams.css">
    </style>

    <@macros.basicDownloadFiles/>
</head>
<body>
    <@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

    <main class="main-posts">
        <@macros.settings themes=themes>
            <label class="settings-likes">
                Likes
                <input type="text" onkeyup="this.value = this.value.replace(/[^\d]/g,'');">
            </label>
        </@macros.settings>


        <div class="posts">
            <#list posts as post>

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

                    <div class="posts-single-tags params">
                        <#list post.getTags() as tag>
                            <p>#${tag}</p>
                        </#list>
                    </div>

                    <div class="posts-single-theme params">
                        <p class>${post.getTheme().getName()}</p>
                    </div>

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



<#--                    <div class="posts-single-user">-->


<#--                        <div class="posts-single-footer">-->

<#--                            <div class="posts-single-footer-userName">-->
<#--                                <p>${user.getName()}</p>-->
<#--                            </div>-->

<#--                            <div class="posts-single-footer-params">-->
<#--                                <p>${post.getDate()?date?string("dd-MM-yyyy")}</p>-->

<#--                                <div class="posts-single-footer-likes">-->
<#--                                    <img class="posts-single-footer-img"src="${fileBuilder.getServicePhotoInBytes("like1.jpg")}" alt="like">-->
<#--                                    ${post.getLikes()?size}-->
<#--                                </div>-->

<#--                                <div class="posts-single-footer-comments">-->
<#--                                    <img class="posts-single-footer-img" src="${fileBuilder.getServicePhotoInBytes("comment.png")}" alt="">-->
<#--                                    ${post.getComments()?size}-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->
                </div>

            </#list>
        </div>


    </main>

</body>
</html>