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
            <h2>Documentation de l'api rest du projet grails</h2>
            <h5>Avant d'envoyer des requettes :</h5>
            <section>
                <ul>
                    <li>
                        <pre>
1) Envoyer une requette POST a <a href="http://localhost:8090/mbds/api/login/">http://localhost:8090/mbds/api/login</a>
    avec Content-Type:application/json et un Body en JSON contenant le nom de l'utilisateur (username) et le mot de passe (password)
    Exemple: {"username":"VotreNom","password":"motdepasseSecret"}
2) Vous receverez une response exemple :  {"username": "VotreNom","roles": ["ROLE_ADMIN"],"access_token": "4hoieugu60lpcthh6cv5d7m7aagski6e"}
3) Mettez par la suite consecutivement la ligne X-Auth-Token: {votre access token} dans le header de vos requettes
                        </pre>
                    </li>
                </ul>
            </section>
            <h5>Requetes sur l'utilisateurs :</h5>
            <section>
                <ul>
                    <li>Url:
                        <a href="http://localhost:8090/mbds/api/user/">http://localhost:8090/mbds/api/user/{id}</a>
                    </li>
                    <li>
                        <pre>
Requette GET sans id : renvoye la liste de tous les utilsateurs en JSON (status 200)
Requette GET avec id valide/existant: renvoye l'utilisateur en JSON (status 200)
Requette GET avec id invalide/inexistant: reponse "user not found" (status 404)
Requette POST avec ou sans id contentant les champs necessaires et valides:
    response "http://localhost:8090/mbds/api/user/{id}" (status 201)
Requette POST avec ou sans id ne contenat pas les champs necessaire et valides:
    response "Failed to add user - not valid champs" (status 400)
Requette DELETE sans id : renvoye "for user delete/put use /api/user/{Your user ID}" (status 400)
Requette DELETE avec id valide/existant: reponse vide - l'utilisateur est supprime (status 200)
Requette DELETE avec id invalide/inexistant: reponse "user not found" (status 404)
Requette PUT sans id : renvoye "for user delete/put use /api/user/{Your user ID}" (status 400)
Requette PUT avec id valide/existant : reponse http://localhost:8090/mbds/api/user/{id} -
    l'utilisateur est mis a jour, les champs invalides seront ignorees (status 200)
Requette PUT avec id invalide/inexistant: reponse "user not found" (status 404)
                        </pre>
                    </li>
                </ul>
            </section>
            <h5>Requetes sur les DeadMatchs :</h5>
            <section>
                <ul>
                    <li>Url:
                        <a href="http://localhost:8090/mbds/api/deadmatch/">http://localhost:8090/mbds/api/deadmatch/{id}</a>
                    </li>
                    <li>
                        <pre>
Requette GET sans id : renvoye la liste de tous les deadmatchs en JSON (status 200)
Requette GET avec id valide/existant: renvoye le deadmatch en JSON (status 200)
Requette GET avec id invalide/inexistant: reponse "deadmatch not found" (status 404)
Requette POST avec ou sans id contentant les champs necessaires et valides:
    response "http://localhost:8090/mbds/api/deadmatch/{id}" (status 201)
Requette POST avec ou sans id ne contenat pas les champs necessaire et valides:
    response "Failed to add deadmatch - not valid champs" (status 400)
Requette DELETE sans id : renvoye "for deadmatch delete/put use /api/deadmatch/{Your deadmatch ID}" (status 400)
Requette DELETE avec id valide/existant: reponse vide - le deadmatch est supprime (status 200)
Requette DELETE avec id invalide/inexistant: reponse "deadmatch not found" (status 404)
Requette PUT sans id : renvoye "for deadmatch delete/put use /api/deadmatch/{Your deadmatch ID}" (status 400)
Requette PUT avec id valide/existant : reponse http://localhost:8090/mbds/api/deadmatch/{id} -
    le deadmatch est mis a jour, les champs invalides seront ignorees (status 200)
Requette PUT avec id invalide/inexistant: reponse "deadmatch not found" (status 404)
                        </pre>
                    </li>
                </ul>
            </section>
            <h5>Requetes sur les Messages :</h5>
            <section>
                <ul>
                    <li>Url:
                        <a href="http://localhost:8090/mbds/api/message/">http://localhost:8090/mbds/api/deadmatch/{id}</a>
                    </li>
                    <li>
                        <pre>
Requette GET sans id : renvoye la liste de tous les messages en JSON (status 200)
Requette GET avec id valide/existant: renvoye le messages en JSON (status 200)
Requette GET avec id invalide/inexistant: reponse "messages not found" (status 404)
Requette POST avec ou sans id contentant les champs necessaires et valides:
    response "http://localhost:8090/mbds/api/message/{id}" (status 201)
Requette POST avec ou sans id ne contenat pas les champs necessaire et valides:
    response "Failed to add message - not valid champs" (status 400)
Requette DELETE sans id : renvoye "for message delete/put use /api/message/{Your message ID}" (status 400)
Requette DELETE avec id valide/existant: reponse vide - le message est supprime (status 200)
Requette DELETE avec id invalide/inexistant: reponse "message not found" (status 404)
Requette PUT sans id : renvoye "for message delete/put use /api/message/{Your message ID}" (status 400)
Requette PUT avec id valide/existant : reponse http://localhost:8090/mbds/api/deadmatch/{id} -
    le message est mis a jour, les champs invalides seront ignorees (status 200)
Requette PUT avec id invalide/inexistant: reponse "message not found" (status 404)
                        </pre>
                    </li>
                </ul>
            </section>
            <h5>Quand tous les requettes sont envoyes :</h5>
            <section>
                <ul>
                    <li>
                        <pre>
Envoyer une requette GET a <a href="http://localhost:8090/mbds/api/logout">http://localhost:8090/mbds/api/logout</a>
avec dans le header X-Auth-Token: {votre token}
le token sera alors plus utilisable
                        </pre>
                    </li>
                </ul>
            </section>
        </section>
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
