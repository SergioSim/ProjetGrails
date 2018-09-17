package mbds

import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder

class UserHomeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_USER'])
    def index() {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName()
        render( view: 'index', model: [theUser: currentUserName])
    }
}
