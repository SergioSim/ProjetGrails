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
            User user = User.findByUsername(currentUserName)
            render( view: 'admin', model: [theUser: currentUserName, theRole: role, thePage: '', theId: user.id])
        }

    }

    def userView(){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName()
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().first()
        render( view: 'admin', model: [theUser: currentUserName, theRole: role, thePage: "user"])
    }

    def messageView(){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName()
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().first()
        render( view: 'admin', model: [theUser: currentUserName, theRole: role, thePage: "message"])
    }

    def roleView(){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName()
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().first()
        render( view: 'admin', model: [theUser: currentUserName, theRole: role, thePage: "role"])
    }

    def deadMatchView(){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName()
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().first()
        render( view: 'admin', model: [theUser: currentUserName, theRole: role, thePage: "deadMatch"])
    }

    def statisticView(){
    }
}
