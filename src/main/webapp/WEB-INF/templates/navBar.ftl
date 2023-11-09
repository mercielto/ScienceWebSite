<#macro navBar option fileBuilder contextPath>
    <header class="header-main">
        <a href="${contextPath}/" class="header-nav-text">
            <h1 id="header-title">MABR & DABR</h1>
        </a>
        <nav class="header-nav-settings rounded">
            <a href="${contextPath}/posts" class="header-nav-text">Posts</a>
            <a href="${contextPath}/forum" class="header-nav-text">Forum</a>
            <a href="#" class="header-nav-text">Name</a>

            <#if option.isPresent()>

                <#assign user=option.get()>

                <div class="header-nav-authorization rounded-pill">
                    <div class="dropdown mini-profile">
                        <a href="${contextPath}/profile/${user.getLink()}">
                            <img class="rounded-circle mini-profile-photo" src="${fileBuilder.getProfilePhotoInBytes(user)}" alt=".">
                        </a>
                        <a class="header-nav-text dropdown-toggle " role="button" data-toggle="dropdown">
                            ${user.getName()}
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${contextPath}/my-posts">My posts</a></li>
                            <li><a class="dropdown-item" href="${contextPath}/my-questions">My questions</a></li>
                            <li><a class="dropdown-item" href="${contextPath}/profile/settings/${user.getLink()}">Profile settings</a></li>
                            <li><a class="dropdown-item" href="${contextPath}/logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            <#else>
                <div class="header-nav-authorization rounded-pill">
                    <a href="${contextPath}/login" class="header-nav-text">Login</a>
                    |
                    <a href="${contextPath}/register" class="header-nav-text">Register</a>
                </div>
            </#if>

        </nav>
    </header>
</#macro>
