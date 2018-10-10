<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main" />
    <meta charset="UTF-8">
    <title>Hello ${theUser.getUsername()}</title>
    <asset:stylesheet src="drag_drop.css"/>
    <asset:stylesheet src="userMessages.css"/>

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
<img src="${createLink(controller: 'user', action: 'getUserImage', id: theUser.id )}" id="theImage" height="140px" width="140px" />
<g:set var="myMessagesToOthers" value="${fruitInstanceList}"/>
<p>Upload image : ...(with drag & drop...)</p>

<div class="nav" role="navigation">
    <ul>
        <li><g:link controller="message" class="create" action="create">Create Message</g:link></li>
    </ul>
</div>
<p>Your Message outBox: ${theOutBox}</p>
<g:each in="${theOutBox}" var="theMessage">
    <div class="userMessage" onclick="messageShow(${theMessage.id})">
        <p>Author : ${theMessage.author.username} Date : ${theMessage.dateCreated}</p>
        <p>Click to show message!</p>
        <div class="aMessage" id="${theMessage.id}" >Message : ${theMessage.content}</div>
    </div>
</g:each>
<p>Your Message inBox: ${theInBox}</p>
<div id="drop-area">
    <form action="http://localhost:8090/mbds/userHome/updateImage" method="PUT" enctype="multipart/form-data" id="the-form" class="my-form">
        <input type="file" name="userImageFile" id="fileElem" accept="image/*" onchange="handleFiles(this.files)" />
        <label class="button" for="fileElem">Select some files</label>
    </form>
</div>

<asset:javascript src="jquery-3.0.0.js"/>
<asset:javascript src="script.js"/>
</body>
</html>