package mbds

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.codehaus.groovy.util.HashCodeHelper

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class UserImage implements Serializable{

    private static final long serialVersionUID = 1

    String imageName
    String imageType
    byte[] imageBytes
    User user

    @Override
    boolean equals(other) {
        if (other instanceof UserImage) {
            other.userId == user?.id
        }
    }

    @Override
    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        if (user) {
            hashCode = HashCodeHelper.updateHash(hashCode, user.id)
        }
        hashCode
    }

    static UserImage get(long userId) {
        criteriaFor(userId).get()
    }

    static boolean exists(long userId) {
        criteriaFor(userId).count()
    }

    private static DetachedCriteria criteriaFor(long userId) {
        UserImage.where {
            user.id == User.load(userId).id
        }
    }

    static UserImage create(User user, String imageName, String imageType, byte[] imageBytes, boolean flush = true) {
        def instance = new UserImage(user: user, imageName: imageName, imageType: imageType, imageBytes: imageBytes)
        instance.save(flush: flush, failOnError: true)
        instance
    }

    static constraints = {
        user nullable: false, blank: false, unique: true, validator:{ User u ->
            if (u?.id) {
                if (UserImage.exists(u.id)) {
                    return ['userImage.exists']
                }
            }
        }

        imageBytes sqlType: 'longblob', maxSize: 26214400
        imageType nullable: false, inList: ["image/jpeg", "image/jpg", "image/png", "image/gif"]
        imageName nullable: false,  blank: false
    }

    static mapping = {
        id column: 'user', generator: 'assigned'
        version false
    }
}
