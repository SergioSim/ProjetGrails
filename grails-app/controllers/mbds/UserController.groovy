package mbds

import grails.validation.ValidationException
import org.springframework.security.access.annotation.Secured
import org.springframework.web.multipart.MultipartFile

import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService
    UserImageService userImageService

    static allowedMethods = [save: "POST", update: "PUT"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def show(Long id) {
        respond userService.get(id)
    }
    @Secured(['ROLE_ADMIN'])
    def create() {
        respond new User(params)
    }

    @Secured(['ROLE_ADMIN'])
    def getUserImage(Long id) {
        UserImage ui = userImageService.get(id)
        if(ui != null){
            response.outputStream << ui.imageBytes
        }else{
            response.outputStream << new URL("http://82.255.166.104/anonymous.jpg").bytes
        }
        response.outputStream.flush()
    }

    @Secured(['ROLE_ADMIN'])
    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            user.save(flush: true)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        MultipartFile f = request.getFile("userImageFile")
        if (!f.empty) {
            try {
                UserImage ui = new UserImage()
                ui.imageName = f.getOriginalFilename()
                ui.imageType = f.getContentType()
                ui.imageBytes = f.getBytes()
                ui.user = userService.get(user.id)
                ui.id = user.id
                userImageService.save(ui)
            } catch (ValidationException e) {
                render "Invalid Image"
                println "hah"
                return
            }
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userService.get(id)
    }

    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
