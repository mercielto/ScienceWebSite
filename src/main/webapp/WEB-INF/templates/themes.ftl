<#import "macros.ftl" as macros>
<#import "navBar.ftl" as navBar>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
      <#include "css/themes.css">
    </style>

    <@macros.basicDownloadFiles/>
</head>
<body>
  <@navBar.navBar option=option imageBuilder=imageBuilder contextPath=contextPath/>

  <main class="theme-main">
    <#list themes as theme>
    <div class="">
      <a href="${contextPath}/themes/${theme.getName()}">
        <img src="${imageBuilder.getThemePhotoInBytes(theme)}"
        class="theme-card rounded" alt="${theme.getName()}">
      </a>
    </div>
  </#list>

    <div>
      <a href="${contextPath}/themes/${other.getName()}">
        <img src="${imageBuilder.getThemePhotoInBytes(other)}"
             class="theme-card rounded" alt="${other.getName()}">
      </a>
    </div>


  </main>

  <footer>

  </footer>

</body>
</html>