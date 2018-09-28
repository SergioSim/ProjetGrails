<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="layout" content="aAdmin" />
        <title>Hello ${theUser}</title>
    </head>
    <body class="bod">
        <h1>Welcome ${theUser}</h1>
        <g:set var="now">
            Today it's : ${new Date()}
        </g:set>

        <p>${now}</p>

        <g:if test="${thePage == ''}">
            <h1>Your Photo: </h1>
            <img src="${createLink(controller: 'user', action: 'getUserImage', id: theId )}" height="140px" width="140px" />
            <br><br><br><br>
        </g:if>
        <g:else>
            <g:include controller="${thePage}" action="index" />
        </g:else>
    </body>
</html>