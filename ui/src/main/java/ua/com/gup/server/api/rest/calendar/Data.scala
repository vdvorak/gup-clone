package ua.com.gup.server.api.rest.calendar

/**
 * CalendarData
 */
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


/**
 * CalendarDataAnnotated
 */
import java.lang.Integer
import javax.validation.constraints.{Min, NotNull}
import scala.annotation.meta.beanGetter
import scala.annotation.meta.beanGetter
import scala.beans.BeanProperty
import org.codehaus.jackson.annotate.JsonProperty

case class CalendarDataAnnotated() {
  @JsonProperty("name")
  @BeanProperty @(NotNull@beanGetter) val name: String = null

  @JsonProperty("age")
  @BeanProperty @(Min@beanGetter)(1) val age: Integer = 0

  override def toString = "CalendarDataAnnotated(" + name + "," + age + ")"
}


/**
 * CalendarEcho
 */
import scala.beans.BeanProperty

case class CalendarEcho(@BeanProperty val name: String, @BeanProperty val weekDay: String)
