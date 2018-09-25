package mbds
import grails.converters.JSON

class ApiController {

    def index() {
        render  "ok"
    }

    def getUser() {
        def converter = new JSON(target: User.list());
        render converter
    }
}