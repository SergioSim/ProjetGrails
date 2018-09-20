package mbds

import grails.gorm.DetachedCriteria

class UserImage {

    String imageName
    String imageType
    byte[] imageBytes
    User user

    private static DetachedCriteria criteriaFor(long userId) {
        UserImage.where {
            user == User.load(userId)
        }
    }

    static boolean exists(long userId) {
        criteriaFor(userId).count()
    }

    static constraints = {
        user nullable: false, blank: false, unique: true, validator:{ UserImage ui ->
            if (ui.user?.id) {
                if (UserImage.exists(ui.user.id)) {
                    return ['user.exists']
                }
            }
        }
        imageBytes sqlType: 'longblob'
        imageType nullable: false, inList: ["image/jpeg", "image/jpg", "image/png", "image/gif"]
        imageName nullable: false,  blank: false
    }
}
