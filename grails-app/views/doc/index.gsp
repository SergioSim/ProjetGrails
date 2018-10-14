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
    <button onclick="location.href='/mbds';">Home</button>
    <section>
        <h2>Documentation de l'api rest du projet grails</h2>
        <h5>Avant d'envoyer des requêtes :</h5>
        <section>
            <ul>
                <li>
                    <pre>
                        1) Envoyer une requête POST a http://localhost:8090/mbds/api/login
                            avec Content-Type:application/json et un Body en JSON contenant le nom de l'utilisateur (username) et le mot de passe (password)
                            Exemple: {"username":"VotreNom","password":"motdepasseSecret"}
                        2) Vous recevrez une réponse exemple :  {"username": "VotreNom","roles": ["ROLE_ADMIN"],"access_token": "4hoieugu60lpcthh6cv5d7m7aagski6e"}
                        3) Mettez par la suite consécutivement la ligne X-Auth-Token: {votre access token} dans le header de vos requêtes

                    </pre>
                </li>
            </ul>
        </section>
        <h5>Requêtes sur l'utilisateurs :</h5>
        <section>
            <ul>
                <li>Url:
                    <a href="http://localhost:8090/mbds/api/user/">http://localhost:8090/mbds/api/user/{id}</a>
                </li>
                <li>
                    <pre>
                        Requête GET sans id : renvoye la liste de tous les utilsateurs en JSON (status 200)
                        Requête GET avec id valide/existant: renvoye l'utilisateur en JSON (status 200)
                        Requête GET avec id invalide/inexistant: reponse "user not found" (status 404)
                        Requête POST avec ou sans id contentant les champs necessaires et valides:
                            response "http://localhost:8090/mbds/api/user/{id}" (status 201)
                        Requête POST avec ou sans id ne contenat pas les champs necessaire et valides:
                            response "Failed to add user - not valid champs" (status 400)
                        Requête DELETE sans id : renvoye "for user delete/put use /api/user/{Your user ID}" (status 400)
                        Requête DELETE avec id valide/existant: réponse vide - l'utilisateur est supprimé (status 200)
                        Requête DELETE avec id invalide/inexistant: réponse "user not found" (status 404)
                        Requête PUT sans id : renvoyé "for user delete/put use /api/user/{Your user ID}" (status 400)
                        Requête PUT avec id valide/existant : réponse http://localhost:8090/mbds/api/user/{id} -
                            l'utilisateur est mis à jour, les champs invalides seront ignorées (status 200)
                        Requête PUT avec id invalide/inexistant: réponse "user not found" (status 404)
                    </pre>
                </li>
            </ul>
        </section>
        <h5>Requêtes sur les DeadMatchs :</h5>
        <section>
            <ul>
                <li>Url:
                    <a href="http://localhost:8090/mbds/api/deadmatch/">http://localhost:8090/mbds/api/deadmatch/{id}</a>
                </li>
                <li>
                    <pre>
                        Requête GET sans id : renvoie la liste de tous les deadmatchs en JSON (status 200)
                        Requête GET avec id valide/existant: renvoie le deadmatch en JSON (status 200)
                        Requête GET avec id invalide/inexistant: réponse "deadmatch not found" (status 404)
                        Requête POST avec ou sans id contenant les champs nécessaires et valides:
                            response "http://localhost:8090/mbds/api/deadmatch/{id}" (status 201)
                        Requête POST avec ou sans id ne contenant pas les champs nécessaire et valides:
                            response "Failed to add deadmatch - not valid champs" (status 400)
                        Requête DELETE sans id : renvoie "for deadmatch delete/put use /api/deadmatch/{Your deadmatch ID}" (status 400)
                        Requête DELETE avec id valide/existant: réponse vide - le deadmatch est supprimé (status 200)
                        Requête DELETE avec id invalide/inexistant: réponse "deadmatch not found" (status 404)
                        Requête PUT sans id : renvoie "for deadmatch delete/put use /api/deadmatch/{Your deadmatch ID}" (status 400)
                        Requête PUT avec id valide/existant : réponse http://localhost:8090/mbds/api/deadmatch/{id} -
                            le deadmatch est mis à jour, les champs invalides seront ignorées (status 200)
                        Requête PUT avec id invalide/inexistant: réponse "deadmatch not found" (status 404)

                    </pre>
                </li>
            </ul>
        </section>
        <h5>Requêtes sur les Messages  :</h5>
        <section>
            <ul>
                <li>Url:
                    <a href="http://localhost:8090/mbds/api/message/">http://localhost:8090/mbds/api/deadmatch/{id}</a>
                </li>
                <li>
                    <pre>
                        Requête GET sans id : renvoie la liste de tous les messages en JSON (status 200)
                        Requête GET avec id valide/existant: renvoie le messages en JSON (status 200)
                        Requête GET avec id invalide/inexistant: reponse "messages not found" (status 404)
                        Requête POST avec ou sans id contenant les champs nécessaires et valides:
                            response "http://localhost:8090/mbds/api/message/{id}" (status 201)
                        Requête POST avec ou sans id ne contenant pas les champs nécessaire et valides:
                            response "Failed to add message - not valid champs" (status 400)
                        Requête DELETE sans id : renvoyé "for message delete/put use /api/message/{Your message ID}" (status 400)
                        Requête DELETE avec id valide/existant: réponse vide - le message est supprimé (status 200)
                        Requête DELETE avec id invalide/inexistant: réponse "message not found" (status 404)
                        Requête PUT sans id : renvoyé "for message delete/put use /api/message/{Your message ID}" (status 400)
                        Requête PUT avec id valide/existant : réponse http://localhost:8090/mbds/api/deadmatch/{id} -
                            le message est mis à jour, les champs invalides seront ignorées (status 200)
                        Requête PUT avec id invalide/inexistant: réponse "message not found" (status 404)

                    </pre>
                </li>
            </ul>
        </section>
        <h5>Quand tous les requêtes sont envoyées :</h5>
        <section>
            <ul>
                <li>
                    <pre>
                        Envoyer une requête GET a http://localhost:8090/mbds/api/logout
                        avec dans le header X-Auth-Token: {votre token}
                        le token sera alors plus utilisable
                    </pre>
                </li>
            </ul>
        </section>
    </section>
</div>
</body>
</html>
