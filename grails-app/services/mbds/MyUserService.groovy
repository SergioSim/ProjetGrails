package mbds

import grails.gorm.transactions.Transactional
import grails.util.Holders
import grails.validation.ValidationException
import org.apache.catalina.core.ApplicationPart
import org.hibernate.SessionFactory
import org.springframework.context.ApplicationContext
import org.springframework.web.multipart.MultipartFile

@Transactional
class MyUserService {
    ApplicationContext ctx = Holders.grailsApplication.mainContext
    UserService userService
    UserRoleService userRoleService
    UserImageService userImageService
    SessionFactory sessionFactory


    void init(){
            userService = ctx.getBean("userService")
            userRoleService = ctx.getBean("userRoleService")
            userImageService = ctx.getBean("userImageService")
    }

    boolean removeUserDeadMatch(Long userId) {
        if (userId != null) {
            User u = User.get(userId)
            DeadMatch.deleteAll(DeadMatch.findAllByWinnerOrLooser(u,u))
        }
    }

    boolean removeUserMessage(Long userId) {
        if (userId != null) {
            User u = User.get(userId)
            Message.deleteAll(Message.findAllByAuthorOrTarget(u,u))
        }
    }

    boolean updateUserImage(User user, ApplicationPart f ) throws ValidationException{
        if (user == null) {
            return false
        }
        if (f.getSize() != 0) {
                userImageService.delete(user.id)
                def hibSession = sessionFactory.getCurrentSession()
                assert hibSession != null
                hibSession.flush()
                UserImage ui = new UserImage()
                ui.imageName = f.getSubmittedFileName()
                ui.imageType = f.getContentType()
                ui.imageBytes = f.getInputStream().getBytes()
                ui.user = userService.get(user.id)
                ui.id = user.id
                userImageService.save(ui)
        }
        return true
    }

    boolean saveUserImage(User user, MultipartFile f) throws ValidationException{
        if (user == null) {
            return false
        }
        if (!f.empty) {
            UserImage ui = new UserImage()
            ui.imageName = f.getOriginalFilename()
            ui.imageType = f.getContentType()
            ui.imageBytes = f.getBytes()
            ui.user = userService.get(user.id)
            ui.id = user.id
            userImageService.save(ui)
        }
        return true
    }

    boolean delete(Long id){
        if (id == null) {
            return false
        }
        def ur = UserRole.get(id,1)
        def ur2 = UserRole.get(id,2)
        if(ur){userRoleService.delete(ur)}
        if(ur2){userRoleService.delete(ur2)}
        userImageService.delete(id)
        removeUserMessage(id)
        removeUserDeadMatch(id)
        userService.delete(id)
        return true
    }
}
