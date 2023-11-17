<#import "macros.ftl" as macros>
<#import "navBar.ftl" as navBar>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        <#include "css/profile.css">
    </style>

    <@macros.basicDownloadFiles/>
</head>
<body>
<@navBar.navBar option=option contextPath=contextPath fileBuilder=fileBuilder/>

<main>
    <form method="post" class="profile-main" enctype="multipart/form-data">
        <h2 id="profile-name"><input name="name" id="profile-name-input" type="text" value="${profileUser.getName()}"></h2>

        <div id="img-redactor">
            <img id="prof-photo" class="profile-photo rounded-circle" src="${fileBuilder.getProfilePhotoInBytes(profileUser)}" alt="profile photo"/>

            <img id="delete-profile-photo" onclick="deleteImg()" src="${fileBuilder.getServicePhotoInBytes("cross.png")}">
        </div>

        <input id="img-input" onchange="imgDownload(event)" type="file" value="Upload" name="img">
        <LABEL for="img-input" class="rounded" id="img-input-label">Download image</LABEL>

        <button name="submit" id="profile-submit" class="rounded profile-subscribe-size">Submit changes</button>

        <textarea name="description" class="rounded" id="profile-description" placeholder="Profile description"><#if (profileUser.getDescription())??>${profileUser.getDescription()}</#if></textarea>

        <div class="profile-attributes">
            <@macros.profileAttributeUsers fileBuilder=fileBuilder listOfUser=profileUserSubscriptions topic="Subscribtions"/>
            <@macros.profileAttributeUsers fileBuilder=fileBuilder listOfUser=profileUserSubscriber topic="Subscribers"/>
            <@macros.profileAttributePosts listOfPost=profileUserLikedPosts topic="Likes" contextPath=contextPath/>
            <@macros.profileAttributePosts listOfPost=profileUserPosts topic="Posts" contextPath=contextPath/>
        </div>
    </form>
    <#include "js/profileSettings.jsp">
</main>

<@macros.footer contextPath/>
<#include "js/profileSettings.jsp">
</body>
</html>