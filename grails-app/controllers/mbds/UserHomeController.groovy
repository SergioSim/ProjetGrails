package mbds

import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder

class UserHomeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def index() {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName()
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().first()
        if(role == 'ROLE_USER'){
            render( view: 'index', model: [theUser: currentUserName, theRole: role])
        }else if(role == 'ROLE_ADMIN'){
            render( view: 'admin', model: [theUser: currentUserName, theRole: role])
        }

    }
}
