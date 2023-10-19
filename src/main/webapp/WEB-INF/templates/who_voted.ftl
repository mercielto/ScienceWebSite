<html>
<body>
    <ul>
        <#list users as u>
            <li>${u.getLogin()}</li>
        </#list>
    </ul>
</body>
</html>