<!DOCTYPE html>
<html lang="en">
<body>

    <#if !isVoted>
        <form method="post">
            <p>What number you prefer?</p>
            <select name='vote'>
                <option value='1'>1</option>
                <option value='2'>2</option>
            </select>
            <input type="submit" value="vote"><br>
        </form>
    <#else>
        <p>You have already voted!!!</p>
    </#if>

    <p>1: ${(servletContext.getAttribute("1"))!0}</p>
    <p>2: ${(servletContext.getAttribute("2"))!0}</p>

</body>
</html>