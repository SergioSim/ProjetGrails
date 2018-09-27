package mbds

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserImageServiceSpec extends Specification {

    UserImageService userImageService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserImage(...).save(flush: true, failOnError: true)
        //new UserImage(...).save(flush: true, failOnError: true)
        //UserImage userImage = new UserImage(...).save(flush: true, failOnError: true)
        //new UserImage(...).save(flush: true, failOnError: true)
        //new UserImage(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userImage.id
    }

    void "test get"() {
        setupData()

        expect:
        userImageService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserImage> userImageList = userImageService.list(max: 2, offset: 2)

        then:
        userImageList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userImageService.count() == 5
    }

    void "test delete"() {
        Long userImageId = setupData()

        expect:
        userImageService.count() == 5

        when:
        userImageService.delete(userImageId)
        sessionFactory.currentSession.flush()

        then:
        userImageService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserImage userImage = new UserImage()
        userImageService.save(userImage)

        then:
        userImage.id != null
    }
}
