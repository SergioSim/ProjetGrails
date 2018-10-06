package mbds

import grails.gorm.transactions.Transactional

@Transactional
class MyUserService {

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
}
