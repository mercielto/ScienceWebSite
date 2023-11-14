<#import "navBar.ftl" as navBar>
<#import "macros.ftl" as macros>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <@macros.basicDownloadFiles/>
    <style>
        <#include "css/my-posts.css">
    </style>
</head>
<body>
    <@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

    <div id="my-posts-main">
        <div id="my-posts-write-new">
                <button id="my-posts-new-button" data-toggle="modal" data-target=".bd-example-modal-lg">
                    <img class="my-posts-write-new-img" src="${fileBuilder.getServicePhotoInBytes("add.png")}">
                </button>

                <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="my-post-new-header">New post</h5>
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

        <#list posts as post>
            <div class="my-posts-post rounded">
                <a class="my-posts-title" href="${contextPath}/posts/${post.getLink()}">
                    <h4>${post.getTitle()}</h4>
                </a>

                <div class="my-posts-params">
                    <div class="my-posts-like">
                        <img class="my-posts-params-img" src="${fileBuilder.getServicePhotoInBytes("not_liked.png")}" alt="">
                        ${post.getLikes()?size}
                    </div>

                    <div class="my-posts-comment">
                        <img class="my-posts-params-img" src="${fileBuilder.getServicePhotoInBytes("comment.png")}" alt="">
                        ${post.getComments()?size}
                    </div>
                </div>
            </div>
        </#list>
    </div>
</body>
</html>
