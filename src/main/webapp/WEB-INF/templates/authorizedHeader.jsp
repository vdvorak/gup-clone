<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="main-head">
  <div class="top-menu">
    <div class="top-menu-userSection">
      <div class="top-menu-userpic">
        <img id="headerProfileImg"  src="" width="32" height="33">
      </div>
      <div class="top-menu-username">
        <a href="#" id="headerProfileName"></a>
      </div>
      <div class="header-mainMenu">
        <a href="/prioffice">Моя страница</a>
        <a href="/dialogues">Сообщения</a>
        <a href="/dialogues">Уведомления</a>
        <a href="/tender">Тендеры</a>
        <a href="/project/list">Проекты</a>
        <a href="/offers">Объявления</a>
        <a href="/blogs">Блоги</a>
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
          <img src="/resources/img/bellSmall.png" id="notificationBellImg">  <%--class="headerNotificationIcon"--%>
      </div>

      <div class="top-menu-contactList">
        <img id="contactListImg" src="/resources/img/contact-book-128.png" width="26" height="26">
      </div>

      <div>
        <ul id="notificationMenu" class="notifications">
          <li class="titlebar">
            <span class="title">Уведомления</span>
        <span class="settings"><i class="icon-cog"></i>
        </span>
          </li>
          <div class="notifbox" id="notificationContainer">
          </div>
          <li class="seeall">
          </li>
        </ul>
      </div>

      <div>
        <ul id="contactListMenu" class="notifications">
          <li class="titlebar">
            <span class="title">Ваши контакты</span>
        <span class="settings"><i class="icon-cog"></i>
        </span>
          </li>
          <div class="notifbox" id="contactListContainer">
          </div>
          <li class="seeall">
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
