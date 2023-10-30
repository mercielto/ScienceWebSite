<#macro navBar option imageBuilder contextPath>
    <header class="header-main">
        <a href="" class="header-nav-text">
            <h1 id="header-title">MABR & DABR</h1>
        </a>
        <nav class="header-nav-settings rounded">
            <a href="#" class="header-nav-text">Name</a>
            <a href="#" class="header-nav-text">Name</a>
            <a href="#" class="header-nav-text">Name</a>


            <#if option.isPresent()>

                <#assign user=option.get()>

                <div class="header-nav-authorization rounded-pill">
                    <div class="dropdown mini-profile">
                        <a href="${contextPath}/profile/${user.getLink()}">
                            <img class="rounded-circle mini-profile-photo" src="${imageBuilder.getProfilePhotoInBytes(user)}" alt=".">
                        </a>
                        <a class="header-nav-text dropdown-toggle " role="button" data-toggle="dropdown">
                            ${user.getName()}
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
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
