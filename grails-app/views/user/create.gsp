<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <g:set var="isAdmin" value="${(String)org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities().first() == "ROLE_ADMIN"}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <g:if  test="${isAdmin}">
                    <li><g:link class="list" controller="userHome" action="userView"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                </g:if>
            </ul>
        </div>
        <div id="create-user" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <f:table collection= "userList" properties = "['Username']" />

            <g:uploadForm resource="${this.user}" method="POST">
                <fieldset class="form">
                    <g:if  test="${isAdmin}">
                        <f:all bean="user"/>
                    </g:if>
                    <g:else>
                        <f:field bean="user" property="username"/>
                        <f:field bean="user" property="password"/>
                    </g:else>
                    <div class="fieldcontain">
                        <label for="userImageFile">
                            Image
                        </label>
                        <input type="file" id="userImageFile" name="userImageFile" />
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:uploadForm>
</html>
        </div>
    </body>
