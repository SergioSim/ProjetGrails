<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-user" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

         <div class="liste_utilisateur">
            <g:each  var = "i" in = "${userList}">
                 <g:each  var = "j" in = "${i}">
                    <div class="utilisateur"><p>${j.username}</p>
                    <div class="photo">
                        photo
                    </div>
                        <div class="operation-user">

                            <div class="d1">
                                <g:link action="show" id="${j.id}" class="logo-oiel">
                                    <img src="/mbds/assets/logo_oeil.png" alt="edit" border="0"/>
                                </g:link>
                            </div>

                            <div class="d2"><g:link controller="user" action="delete" id="${j.id}">Delete</g:link></div>
                            <div class="d3"><g:link controller="user" action="edit" id="${j.id}">Edit</g:link></div>
                        </div>

                    </div>
                </g:each>
            </g:each>
         </div>
                  <div class="pagination">
                <g:paginate total="${userCount ?: 0}" />
            </div>
        </div>
    </body>
</html>