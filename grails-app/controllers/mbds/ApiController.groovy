package mbds
import grails.converters.JSON;
class ApiController {

    def index() {
        render text:"Ohhh"
    }

    def user(long id) {
        if (id != 0) {
            def user = User.findById(id)
            if (user == null) {
                render(status: 400, text: "User not found")
            } else {
                render new JSON(target: user)
            }
        } else {
            switch (request.getMethod()) {
                case "GET":
                    def rep = new JSON(target: User.list())
                    render rep
                    break

                case "POST":
                    println(request.JSON)
                    if (new User(request.JSON).save(flush: true)) {
                        response.status = 200
                    } else {
                        response.status = 300
                    }
                    break
                default:
                    render(status: 500, text: "User not found")
            }
        }
    }
}