package mbds

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import org.springframework.security.access.annotation.Secured
import org.springframework.web.multipart.MultipartFile

import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService
    MyUserService myUserService
    UserImageService userImageService
    RoleService roleService

    static allowedMethods = [save: "POST", update: "PUT"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def show(Long id) {
        respond userService.get(id)
    }
    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def create() {
        respond new User(params)
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def getUserImage(Long id) {
        UserImage ui = userImageService.get(id)
        if(ui != null){
            response.outputStream << ui.imageBytes
        }else{
            response.outputStream << new URL("http://82.255.166.104/anonymous.jpg").bytes
        }
        response.outputStream.flush()
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
            def theRole = UserRole.findByUser(user)
            if (theRole == null) {
                Role role = roleService.get(2)
                new UserRole(user: user, role: role).save(flush: true)
            }
        }catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        MultipartFile f = request.getFile("userImageFile")
        if (!f.empty) {
            try {
                myUserService.saveUserImage(user,f)
            } catch (ValidationException e) {
                render "Invalid Image"
                return
            }
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect(controller: "userHome", action: "index")
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userService.get(id)
    }

    @Transactional
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

        try {
            myUserService.updateUserImage(user, request.getPart("userImageFile"))
        }catch (ValidationException e) {
            println e
            render "Invalid Image"
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

        myUserService.delete(id)

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
