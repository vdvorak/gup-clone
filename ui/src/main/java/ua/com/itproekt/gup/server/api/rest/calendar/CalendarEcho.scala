package ua.com.itproekt.gup.server.api.rest.calendar

import scala.beans.BeanProperty

case class CalendarEcho(@BeanProperty val name: String, @BeanProperty val weekDay: String)
