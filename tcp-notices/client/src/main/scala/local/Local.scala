package local

import akka.actor._


object Client extends App {
  implicit val system = ActorSystem("LocalSystem")
  val      client = system.actorOf(Props[Client], name = "Client")  // the local actor
  client ! "START"                                                  // start the action
}


class Client extends Actor {

  // create the remote actor
  val remote = context.actorFor("akka://ServerSystem@127.0.0.1:5150/user/Server")
  var counter = 0

  override def receive = {
    case "START" =>
      remote ! "start"
    case msg: String => 
        println(s"Получено сообщение от: '$msg'")
        if (counter < 5) {
            sender ! String.valueOf(counter)
            counter += 1
        }
  }
}
