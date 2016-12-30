package samples

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AppControllerSpecTest extends Specification {

  "Calling ScalaJavaController.ping" should {
    "return pong" in {
      new AppController().ping() must be equalTo "pong"
    }
  }

}
