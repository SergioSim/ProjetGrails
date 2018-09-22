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
    Some re-usable code on: ${new Date()}
</g:set>


<p>${now}</p>
<g:if test="${thePage == ''}">
    <h1>hello</h1>
</g:if>
<g:else>
    <g:include controller="${thePage}" action="index" />
</g:else>
<g:link controller="logout">grails.plugin.springsecurity.LogoutController</g:link>
</body>
</html>