<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qz
  Date: 1/18/2016
  Time: 9:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="main-head">
  <div class="top-menu">
    <div class="top-menu-userSection">
      <div class="top-menu-userpic">
        <img src="/resources/img/reallySmallUserpic.png">
      </div>
      <div class="top-menu-username">
        <a href="#">
          <c:choose>
            <c:when test="${not empty profile.username}">
              ${profile.username}
            </c:when>
            <c:otherwise>
              Вася Пупкин
            </c:otherwise>
          </c:choose>
        </a>
      </div>
      <div class="header-mainMenu">
        <a href="/prioffice">Моя страница</a>
        <a href="/dialogues">Сообщения</a>
        <a href="/dialogues">Уведомления</a>
        <a href="/tender">Тендеры</a>
        <a href="/projectList?pageNumber=0">Проекты</a>
        <a href="/blogs">Новости</a>
        <a href="#">Настройки</a>
        <a href="/logout">Выход</a>
        <div class="pageedit">
          <a href="/edit-profile">Редактировать страницу</a>
        </div>
      </div>
    </div>
    <div class="top-menu-notifications">
      <div class="top-menu-messages">
        <img src="/resources/img/envelopeSmall.png">
      </div>
      <!--<div class="top-menu-actualMessageWrap">
          <img class="message-sender-userpic"src="">
          <div class="top-menu-incomingMessage">
              <p>
                  <span class="top-menu-incomingMessage_date"></span>
                  Сообщение СообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщение
              </p>
              <a  class="top-menu-incomingMessage_answer" href="#">Ответить</a>
          </div>
      </div>
      ТВОРЧЕСКИЙ ПИЗДЕЦ, ДОДЕЛАТЬ! -->
      <div class="top-menu-notification">
        <img src="/resources/img/bellSmall.png">
      </div>

    </div>

    <div class="top-menu-userBallance">
      <div class="ballance">
        <a href="#">00.00<span>грн</span></a>
      </div>
      <div class="ballanceAdd-wraper">
        <a href="#">Пополнить баланс</a>
      </div>
    </div>
  </div>
</header>
