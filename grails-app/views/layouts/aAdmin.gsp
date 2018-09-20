<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><g:layoutTitle default="User" /></title>
    <g:layoutHead />
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <asset:stylesheet src="application.css"/>
</head>
<body onload="${pageProperty(name:'body.onload')}">

<div class="menu-gauche">
    <div class="vertical-menu">
        <g:link class="create" action="index">Main</g:link>
        <a href="#" class="active">Utilisateur</a>
        <g:link class="create" action="userView">Liste des utilisateurs</g:link>
    </div>

    <div class="vertical-menu">
        <a href="" class="active">Message</a>
        <g:link class="create" action="messageView">Listes des messages</g:link>
    </div>

    <div class="vertical-menu">
        <a href="" class="active">Match</a>
        <g:link class="create" action="deadMatchView">Listes des Dead Matchs</g:link>
    </div>
</div>
<div class="body">
    <g:layoutBody />
</div>
<div class="footer" role="contentinfo"> This is a layout footer</div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>
</body>
</html>

