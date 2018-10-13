package mbds
import grails.converters.JSON
import grails.validation.ValidationException
import org.grails.web.converters.exceptions.ConverterException

class ApiController {
    String home = "http://localhost:8090/mbds/api/"
    MessageService messageService
    DeadMatchService deadMatchService
    MyUserService myUserService

    def index() {
        render text:"Ohhh"
    }

    def user(String id){
        prepareRequest(id, User.class, myUserService)
    }

    def message(String id){
        prepareRequest(id, mbds.Message.class, messageService)
    }

    def deadmatch(String id){
        prepareRequest(id, DeadMatch.class, deadMatchService)
    }

    def <T,S> void prepareRequest(String id, Class<T> domainClass, S domainService) {
        String domainName = domainClass.getName().split("\\.")[1].toLowerCase()
        if(request.getMethod() == "POST"){
            handleRequest(id, null, domainClass, domainService)
        }else{
            if (id != 0 && id != null) {
                T domainObject = domainService.get(id)
                if(domainObject != null){
                    handleRequest(id, domainObject, domainClass, domainService)
                }else{
                    render(status: 404, text: domainName + " not found")
                    return
                }
            }else if(request.getMethod() == "GET") {
                render(status: 200, contentType: "application/json", new JSON(target: domainService.list()))
            }else{
                render(status: 400, "for " +domainName+ " delete/put use /api/" +domainName+ "/{Your "+domainName+" ID}")
            }
        }
    }

    def <T,S,V> void handleRequest(String id, V domainObject, Class<T> domainClass, S domainService){
        String domainName = domainClass.getName().split("\\.")[1].toLowerCase()
        switch (request.getMethod()) {
            case "POST":
                T t = domainClass.newInstance()
                try {
                    t.properties = request.JSON
                }catch(ConverterException e){
                    render(status: 400, text: "Failed to update "+ domainName + " - not valid champs")
                    return
                }
                //User u = new User(request.JSON)
                try{
                    t = domainService.save(t)
                }catch(ValidationException e){
                    render(status: 400, text: "Failed to add " + domainName +" - not valid champs")
                    return
                }
                if (t != null) {
                    render(status: 201, text: home + domainName + "/" + t.id )
                } else {
                    render(status: 400, text: "Failed to add " + domainName +" - not valid champs")
                }
                break

            case "GET":
                render(status: 200, contentType: "application/json", new JSON(target: domainObject))
                break

            case "PUT":
                try {
                    domainObject.properties = request.JSON
                }catch(ConverterException e){
                    render(status: 400, text: "Failed to update "+ domainName + " - not valid champs")
                    return
                }
                println(domainObject)
                if (domainObject.validate() && domainService.save(domainObject)) {
                    render(status: 200, text: home + domainName+ "/" + domainObject.id )
                } else {
                    render(status: 400, text: "Failed to update "+ domainName + " - not valid champs")
                }
                break

            case "DELETE":
                Long idl = Long.parseLong(id)
                domainService.delete(idl)
                render(status: 200)
                break

            default:
                render(status: 405, text: "Only GET, POST, PUT, DELETE Allowed")
                break
        }
    }
}