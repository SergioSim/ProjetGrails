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
                println(request.JSON)
                if(new User(request.JSON).save(flush:true)){
                    response.status=200
                }
                else {
                    response.status=300
                }
                break;
            default:
                response: 500;
        }
    }
}