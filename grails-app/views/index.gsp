<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
    <div>
        <content tag="nav">
            <li class="dropdown">
                <a href="/mbds/logout/index" role="button"  aria-expanded="false">Logout </a>
            </li>
        </content>
    </div>
    <div class="menu-gauche">
    <div class="vertical-menu">
        <a href="#" class="active">Utilisateur</a>
        <a href="/mbds/user/index">Liste des utilisateurs</a>
        <a href="/mbds/user/index">Nouvelle utilisateur</a>
    </div>

    <div class="vertical-menu">
        <a href="" class="active">Message</a>
        <a href="#">Listes des messages</a>
        <a href="#">Nouveau message</a>
    </div>

        <div class="vertical-menu">
            <a href="" class="active">Match</a>
            <a href="#">Listes des matchs</a>
            <a href="#">Nouveau match</a>
        </div>
    </div>
    <div class="center-boody">
        <center><h1> Welcome Admin </h1></center>
    </div>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <div id="controllers" role="navigation">
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </section>
    </div>

</body>
</html>
