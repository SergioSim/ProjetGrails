<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="layout" content="main" />
        <meta charset="UTF-8">
        <title>Hello ${theUser.getUsername()}</title>
    </head>
    <body>
    <div>
        <content tag="nav">
            <li class="dropdown">
                <a href="/mbds/logout/index" role="button"  aria-expanded="false">Logout </a>
            </li>
        </content>
    </div>
        <h1>Welcome ${theUser.getUsername()}</h1>
        <p>your id : ${theUser.id}</p>
        <p> your role is : USER_ROLE</p>
        <p>Your image : </p>
        <img src="${createLink(controller: 'user', action: 'getUserImage', id: theUser.id )}" height="140px" width="140px" />
        <g:set var="myMessagesToOthers" value="${fruitInstanceList}"/>
        <p>Upload image : ...(with drag & drop...)</p>
        <g:uploadForm controller="userHome" action="updateImage" method="PUT" enctype="multipart/form-data">
            <input type="file" name="userImageFile" id="userImageFile" />
            <input type="submit" />
        </g:uploadForm>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link controller="message" class="create" action="create">Create Message</g:link></li>
            </ul>
        </div>
        <p>Your Message outBox: ${theOutBox}</p>
        <p>Your Message inBox: ${theInBox}</p>
    </body>
</html>