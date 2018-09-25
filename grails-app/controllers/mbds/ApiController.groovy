package mbds
import grails.converters.JSON;
class ApiController {

    def index() {
        render text:"Ohhh";
}
    def user(){
        switch (request.getMethod()) {
            case "GET":
                def rep= new JSON(target:User.list())
                render rep
                break;
            case "POST":
                def user = new User(request).save(flush: true)

            default:
                response: 500;
        }
    }
}