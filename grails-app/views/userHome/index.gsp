<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Hello ${theUser.getUsername()}</title>
    </head>
    <body>
        <h1>Welcome ${theUser.getUsername()}</h1>
        <p>your id : ${theUser.id}</p>
        <p> your role is : USER_ROLE</p>
        <p>Your image : </p>
        <img src="${createLink(controller: 'user', action: 'getUserImage', id: theUser.id )}" height="140px" width="140px" />
        <g:set var="myMessagesToOthers" value="${fruitInstanceList}"/>
        <p>Upload image : ...</p>
        <p>Your Message outBox: ${theOutBox}</p>
        <p>Your Message inBox: ${theInBox}</p>

        <g:link controller="logout">grails.plugin.springsecurity.LogoutController</g:link>
    </body>
</html>