package remote

import akka.actor._


object Server extends App  {
  val      system = ActorSystem("ServerSystem")
  val server = system.actorOf(Props[Server], name = "Server")
  server ! "The Server is alive"
}


class Server extends Actor {

  override def receive = {
    case msg: String => 
      println(s"Получено сообщение от: '$msg'")
      if (msg.equals("4")) sender ! "stop" else sender ! "(Server)"
  }

}
