package samples

import java.lang.Integer
import javax.validation.constraints.{NotNull, Min}

case class AppIndata(val name: String, val age: Integer) {

  @NotNull
  def getName() = name

  @Min(1)
  def getAge() = age
}
