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
        <form action="http://localhost:8090/mbds/userHome/updateImage" method="PUT" enctype="multipart/form-data" id="the-form">
            <input type="file" name="userImageFile" id="userImageFile" />
            <input type="submit" />
        </form>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link controller="message" class="create" action="create">Create Message</g:link></li>
            </ul>
        </div>
        <p>Your Message outBox: ${theOutBox}</p>
        <p>Your Message inBox: ${theInBox}</p>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // your code here
            var form = document.getElementById('the-form');
            console.log(form)

            form.onsubmit = function() {
                var fileInput = document.getElementById('userImageFile');
                console.log(fileInput.files[0])
                var file = fileInput.files[0];
                var formData = new FormData(form);
                formData.append('userImageFile', file);
                var xhr = new XMLHttpRequest();
                // Add any event handlers here...
                xhr.open('PUT', form.getAttribute('action'), true);
                xhr.send(formData);

                return false; // To avoid actual submission of the form
            }
        }, false);
    </script>
    </body>
</html>