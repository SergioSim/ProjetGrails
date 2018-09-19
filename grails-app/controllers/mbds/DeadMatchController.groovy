package mbds

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DeadMatchController {

    DeadMatchService deadMatchService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond deadMatchService.list(params), model:[deadMatchCount: deadMatchService.count()]
    }

    def show(Long id) {
        respond deadMatchService.get(id)
    }

    def create() {
        respond new DeadMatch(params)
    }

    def save(DeadMatch deadMatch) {
        if (deadMatch == null) {
            notFound()
            return
        }

        try {
            deadMatchService.save(deadMatch)
        } catch (ValidationException e) {
            respond deadMatch.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'deadMatch.label', default: 'DeadMatch'), deadMatch.id])
                redirect deadMatch
            }
            '*' { respond deadMatch, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond deadMatchService.get(id)
    }

    def update(DeadMatch deadMatch) {
        if (deadMatch == null) {
            notFound()
            return
        }

        try {
            deadMatchService.save(deadMatch)
        } catch (ValidationException e) {
            respond deadMatch.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'deadMatch.label', default: 'DeadMatch'), deadMatch.id])
                redirect deadMatch
            }
            '*'{ respond deadMatch, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        deadMatchService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'deadMatch.label', default: 'DeadMatch'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'deadMatch.label', default: 'DeadMatch'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
