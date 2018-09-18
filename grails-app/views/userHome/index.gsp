<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Hello ${theUser}</title>
    </head>
    <body>
        <h1>Welcome ${theUser}</h1>
        <p> your role is : ${theUser}</p>
        <p>Your images : ...</p>
        <p>Upload image : ...</p>
        <g:link controller="logout">grails.plugin.springsecurity.LogoutController</g:link>
    </body>
</html>