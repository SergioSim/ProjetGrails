package mbds
import grails.converters.JSON

class ApiController {
    String home = "http://localhost:8090/mbds/api/"
    def index() {
        render text:"Ohhh"
    }

    def user(String id) {
        switch (request.getMethod()) {
            case "GET":
                if (id != 0 && id != null) {
                    def user = User.get(id)
                    if (user == null) {
                        render(status: 400, text: "User not found")
                    } else {
                        render(new JSON(target: user))
                    }
                    break
                }
                render(new JSON(target: User.list()))
                break

            case "POST":
                User u = new User(request.JSON).save(flush: true)
                if (u != null) {
                    render(status: 201, text: home + "user/" + u.id )
                } else {
                    render(status: 400, text: "Failed to add User")
                }
                break
            
            case "PUT":
                User user = User.get(id)
                user.properties = request.JSON
                if (user.validate()) {
                    render(status: 201, text: home + "user/" + user.id )
                } else {
                    render(status: 304, text: "Failed to update User")
                }

            default:
                render(status: 500, text: "User not found")
        }
    }
}