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
    <@navBar.navBar option=option contextPath=contextPath helpers=helpers/>

    <main class="profile-main">
        <h2 id="profile-name">${profileUser.getName()}</h2>
        <img class="profile-photo rounded-circle" src="${helpers.getProfilePhotoInBytes(profileUser)}" alt="profile photo"/>

        <#assign disabled="disabled">
        <#if option.isPresent()>
            <#assign disabled="">
            <#assign user = option.get()>

            <#if user.getId() == profileUser.getId()>
                <a href="${contextPath}/profile/${user.getLink()}/settings">
                    <button name="settings" id="profile-notSubscribed"class="rounded profile-subscribe-size">Settings</button>
                </a>
            <#else>
                <form method="post">
                    <#if subscribed>
                        <button name="subscribe" id="profile-subscribed" class="rounded profile-subscribe-size" ${disabled}>Subscribed</button>
                    <#else>
                        <button name="subscribe" id="profile-notSubscribed" class="rounded profile-subscribe-size" ${disabled}>Subscribe</button>
                    </#if>
                </form>
            </#if>
        <#else>
            <form method="post">
                <#if subscribed>
                    <button name="subscribe" id="profile-subscribed" class="rounded profile-subscribe-size" ${disabled}>Subscribed</button>
                <#else>
                    <button name="subscribe" id="profile-notSubscribed" class="rounded profile-subscribe-size" ${disabled}>Subscribe</button>
                </#if>
            </form>
        </#if>

        <p id="profile-description">
            <#if (profileUser.getDescription())??>
                ${profileUser.getDescription()}
            <#else>
                User has no profile description
            </#if>
        </p>

        <div class="profile-attributes">

            <@macros.profileAttribute helpers=helpers listOfUser=profileUserSubscriptions topic="Subscribtions"/>
            <@macros.profileAttribute helpers=helpers listOfUser=profileUserSubscriber topic="Subscriber"/>
            <@macros.profileAttribute helpers=helpers listOfUser=profileUserLikes topic="Likes"/>
            <@macros.profileAttribute helpers=helpers listOfUser=profileUserPosts topic="Posts"/>

        </div>
    </main>

    <footer>

    </footer>

</body>
</html>