package mbds

class MessagePurgeJob {

    static triggers = {
        cron name:   'cronTrigger',   startDelay: 1000, cronExpression: '0 0 4/24 ? * * *'
    }

    def execute() {
        Message.deleteAll(Message.findAllByLu(true))
    }
}
