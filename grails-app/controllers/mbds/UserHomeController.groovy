package mbds

import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder

class UserHomeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def index() {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName()
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().first()
        User user = User.findByUsername(currentUserName)
        if(role == 'ROLE_USER'){
            render( view: 'index',
                    model: [
                            theUser: user,
                            theRole: role,
                            theInBox: getUserMessageAuthor(user.id),
                            theOutBox: getUserMessageTarget(user.id)])
        }else if(role == 'ROLE_ADMIN'){
            render( view: 'admin',
                    model: [
                            theUser: currentUserName,
                            theRole: role,
                            thePage: '',
                            theId: user.id])
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

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def getUserMessageAuthor(Long id) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName()
        User user = User.findByUsername(currentUserName)
        if(id == user.id){
            return Message.findAllByAuthor(user)
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def getUserMessageTarget(Long id) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName()
        User user = User.findByUsername(currentUserName)
        if(id == user.id){
            return Message.findAllByTarget(user)
        }
    }
}
