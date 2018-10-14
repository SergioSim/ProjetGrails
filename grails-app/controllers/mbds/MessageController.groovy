package mbds

import grails.validation.ValidationException
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.*

class MessageController {

    MessageService messageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond messageService.list(params), model:[messageCount: messageService.count()]
    }

    def show(Long id) {
        respond messageService.get(id)
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def create() {
        params.put("theUser",session["userId"])
        respond new Message(params)
    }
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def save(Message userMessage) {
        if (userMessage == null) {
            notFound()
            return
        }

        try {
            messageService.save(userMessage)
        } catch (ValidationException e) {
            respond userMessage.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userMessage.label', default: 'Message'), userMessage.id])
                redirect(controller: "userHome", action: "index")
            }
            '*' { respond userMessage, [status: CREATED] }
        }
    }

    def edit(Long id) {



        respond messageService.get(id)
    }

    def update(Message theMessage) {
        if (theMessage == null) {
            notFound()
            return
        }

        try {
            messageService.save(theMessage)
        } catch (ValidationException e) {
            respond theMessage.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'message.label', default: 'Message'), theMessage.id])
                redirect theMessage
            }
            '*'{ respond theMessage, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def read(Long id){
        Message theMessage = messageService.get(id)
        if (theMessage == null) {
            notFound()
            return
        }
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().first()
        if(role == 'ROLE_USER') {
            if(theMessage.target.id == session["userId"]) {
                theMessage.lu = true
            }else{
                render(status: 401)
                return
            }
        }
        try {
            messageService.save(theMessage)
        } catch (ValidationException e) {
            respond theMessage.errors, view:'edit'
            return
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        messageService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'message.label', default: 'Message'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
