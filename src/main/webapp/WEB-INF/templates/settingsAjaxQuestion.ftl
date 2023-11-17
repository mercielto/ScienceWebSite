<#import "macros.ftl" as macros>

<#if questions?size != 0>
    <#list questions as question>
        <@macros.singleQuestion contextPath question fileBuilder/>
    </#list>
<#else>
    <div><h3>Unfortunately that's all</h3></div>
</#if>