package mbds

import grails.gorm.services.Service

@Service(UserImage)
interface UserImageService {

    UserImage get(Serializable id)

    List<UserImage> list(Map args)

    Long count()

    void delete(Serializable id)

    UserImage save(UserImage userImage)

}