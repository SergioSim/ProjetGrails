package mbds
import grails.converters.JSON

class ApiController {
    String home = "http://localhost:8090/mbds/api/"
    UserService userService
    UserImageService userImageService
    MyUserService myUserService
    UserRoleService userRoleService

    def index() {
        render text:"Ohhh"
    }

    def user(String id) {
        switch (request.getMethod()) {
            case "GET":
                if (id != 0 && id != null) {
                    def user = userService.get(id)
                    if (user == null) {
                        render(status: 404, text: "User not found")
                    } else {
                        render(status: 200, contentType: "application/json", new JSON(target: user))
                    }
                    break
                }
                render(status: 200, contentType: "application/json", new JSON(target: User.list()))
                break

            case "POST":
                User u = new User(request.JSON)
                u = userService.save(u)
                if (u != null) {
                    render(status: 201, text: home + "user/" + u.id )
                } else {
                    render(status: 400, text: "Failed to add User - not valid champs")
                }
                break
            
            case "PUT":
                if (id != 0 && id != null) {
                    User user = userService.get(id)
                    if (user == null) {
                        render(status: 404, text: "User not found")
                    } else {
                        user.properties = request.JSON
                        if (user.validate() && userService.save(user)) {
                            render(status: 200, text: home + "user/" + user.id )
                        } else {
                            render(status: 400, text: "Failed to update User - not valid champs")
                        }
                    }
                    break
                }
                render(status: 400, "for user update use /api/user/{Your User ID}")
                break
            case "DELETE":
                if (id != 0 && id != null) {
                    User user = userService.get(id)
                    if (user == null) {
                        render(status: 404, text: "User not found")
                    } else {
                        Long idl = Long.parseLong(id)
                        myUserService.delete(idl)
                        render(status: 200)
                    }
                    break
                }
                render(status: 400, "for user delete use /api/user/{Your User ID}")
                break

            default:
                render(status: 405, text: "Only GET, POST, PUT, DELETE Allowed")
                break
        }
    }
}