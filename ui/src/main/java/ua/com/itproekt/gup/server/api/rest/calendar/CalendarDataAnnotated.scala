package ua.com.itproekt.gup.server.api.rest.calendar

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
