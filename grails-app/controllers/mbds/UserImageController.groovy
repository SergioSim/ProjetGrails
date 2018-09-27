package mbds

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserImageController {

    UserImageService userImageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userImageService.list(params), model:[userImageCount: userImageService.count()]
    }

    def show(Long id) {
        respond userImageService.get(id)
    }

    def create() {
        respond new UserImage(params)
    }

    def save(UserImage userImage) {
        if (userImage == null) {
            notFound()
            return
        }

        try {
            println userImage
            userImageService.save(userImage)
        } catch (ValidationException e) {
            respond userImage.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userImage.label', default: 'UserImage'), userImage.id])
                redirect userImage
            }
            '*' { respond userImage, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userImageService.get(id)
    }

    def update(UserImage userImage) {
        if (userImage == null) {
            notFound()
            return
        }

        try {
            userImageService.save(userImage)
        } catch (ValidationException e) {
            respond userImage.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userImage.label', default: 'UserImage'), userImage.id])
                redirect userImage
            }
            '*'{ respond userImage, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userImageService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userImage.label', default: 'UserImage'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userImage.label', default: 'UserImage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
