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
              Пользователь
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
      <div class="top-menu-notification">
          <img src="/resources/img/bellSmall.png" id="notificationBellImg" class="headerNotificationIcon">
      </div>
      <div>
        <ul id="notificationMenu" class="notifications">
          <li class="titlebar">
            <span class="title" align="center">Уведомления</span>
        <span class="settings"><i class="icon-cog"></i>
        </span>
          </li>
          <div class="notifbox" id="notificationContainer">

          </div>
          <li class="seeall">
            <a>Посмотреть все</a>
          </li>
        </ul>
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
