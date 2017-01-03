package ua.com.itproekt.gup.server.api.rest.samples

import javax.validation.constraints.{Min, NotNull}

case class CalendarData(val name: String, val age: Integer) {

  @NotNull
  def getName() = name

  @Min(1)
  def getAge() = age
}
