package mbds

class Message {

    User author
    User target
    String content
    Date dateCreated = new Date()
    boolean lu = false

    static constraints = {
    }
}
