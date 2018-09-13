package tp1

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true)
        def gamingRole = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)


        def adminUser = new User(username: 'admin',password: 'password').save(flush: true, failOnError: true)
        def playerUser = new User(username: 'playerUser',password: 'playerUser').save(flush: true, failOnError: true)
        def playerTwoUser = new User(username: 'playerTwoUser',password: 'playerTwoUser').save(flush: true, failOnError: true)

        UserRole.create(adminUser,adminRole)
        UserRole.create(playerUser,gamingRole)
        UserRole.create(playerTwoUser,gamingRole)



        def user1 = new User(username: 'Player1', password: 'Player1').save(flush: true, failOnError: true)
        def user2 = new User(username: 'Player2', password: 'Player2').save(flush: true, failOnError: true)

        def match = new Match(winner: user1, looser: user2, winnerScore: 10, looserscore: 1).save(flush: true, failOnError: true)

        new Message(author: user1, target: user2, content:"hello friend").save(flush: true, failOnError: true)
        new Message(author: user1, target: user2, content:"hello friend").save(flush: true, failOnError: true)
    }



    def destroy = {
    }
}