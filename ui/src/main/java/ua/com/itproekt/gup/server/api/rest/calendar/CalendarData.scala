package ua.com.itproekt.gup.server.api.rest.calendar

import java.lang.Integer
import javax.validation.constraints.{Min, NotNull}

case class CalendarData() {
  val name: String = null
  val age: Integer = 0

  @NotNull
  def getName() = name

  @Min(1)
  def getAge() = age

  override def toString = "CalendarData(" + name + "," + age + ")"
}
