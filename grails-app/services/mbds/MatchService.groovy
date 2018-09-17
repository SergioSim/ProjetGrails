package mbds

import grails.gorm.services.Service

@Service(DeadMatch)
interface MatchService {

    DeadMatch get(Serializable id)

    List<DeadMatch> list(Map args)

    Long count()

    void delete(Serializable id)

    DeadMatch save(DeadMatch match)

}