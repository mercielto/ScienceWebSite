<#import "macros.ftl" as macros>

<#if posts?size != 0>
    <#list posts as post>
        <@macros.singlePost post contextPath fileBuilder option/>
    </#list>
<#else>
    <h3>Unfortunately that's all</h3>
</#if>
