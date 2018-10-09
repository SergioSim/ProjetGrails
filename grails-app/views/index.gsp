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
                <a href="/mbds/user/create" role="button"  aria-expanded="false">Sign up</a>
            </li>
            <li class="dropdown">
                <a href="/mbds/userHome/index" role="button"  aria-expanded="false">Sign in</a>
            </li>
            <li class="dropdown">
                <a href="/mbds/logout/index" role="button"  aria-expanded="false">Logout</a>
            </li>
        </content>
    </div>
    <div>
        <section>
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
    <div class="center-boody">
        <center><h1> Welcome to our Grails Projects </h1></center>
    </div>

</body>
</html>
