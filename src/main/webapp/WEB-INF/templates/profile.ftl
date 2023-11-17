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

    <main class="profile-main">
        <h2 id="profile-name">${profileUser.getName()}</h2>
        <img class="profile-photo rounded-circle" src="${fileBuilder.getProfilePhotoInBytes(profileUser)}" alt="profile photo"/>

        <#assign disabled="disabled">
        <div id="subscription-div">
            <#if option.isPresent()>
                <#assign disabled="">
                <#assign user = option.get()>

                <#if user.getId() == profileUser.getId()>
                    <a href="${contextPath}/profile/settings/${user.getLink()}">
                        <button name="settings" id="profile-notSubscribed" class="rounded profile-subscribe-size">Settings</button>
                    </a>
                <#else>
                    <#if subscribed>
                        <button name="subscribe" id="subscribe-button" class="profile-subscribed rounded profile-subscribe-size" ${disabled}>Subscribed</button>
                    <#else>
                        <button name="not_subscribed" id="subscribe-button" class="profile-notSubscribed rounded profile-subscribe-size" ${disabled}>Subscribe</button>
                    </#if>
                </#if>
            <#else>
                <#if subscribed>
                    <button name="subscribed" id="subscribe-button" class="profile-subscribed rounded profile-subscribe-size" ${disabled}>Subscribed</button>
                <#else>
                    <button name="not_subscribed" id="subscribe-button" class="profile-notSubscribed rounded profile-subscribe-size" ${disabled}>Subscribe</button>
                </#if>
            </#if>
        </div>

        <p id="profile-description" class="rounded">
            <#if (profileUser.getDescription())??>
                ${profileUser.getDescription()}
            <#else>
                User has no profile description
            </#if>
        </p>

        <div class="profile-attributes">

            <@macros.profileAttributeUsers fileBuilder=fileBuilder listOfUser=profileUserSubscriptions topic="Subscribtions"/>
            <@macros.profileAttributeUsers fileBuilder=fileBuilder listOfUser=profileUserSubscriber topic="Subscribers"/>
            <@macros.profileAttributePosts listOfPost=profileUserLikedPosts topic="Likes" contextPath=contextPath/>
            <@macros.profileAttributePosts listOfPost=profileUserPosts topic="Posts" contextPath=contextPath/>

        </div>
    </main>

    <@macros.footer contextPath/>
    <#include "js/profile.jsp">
</body>
</html>