<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 20.01.2016
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
  <div class="sec-wrap">
    <div class="partials-wrap">
      <div class="main-tender-wrap">
        <p>Тендеры</p>

        <div class="main-tenderPic-wrap">
          <a href="/tender"><img src="/resources/img/hammertime.png"></a>
        </div>
        <nav class="main-tender-bottom-menu">
          <a href="#" class="active-main-menu-link">Участвовать</a>
          <a href="/doer-create">Исполнители</a>
        </nav>
      </div>
      <div class="main-project-wrap">
        <p>Проекты</p>

        <div class="main-projectPic-wrap">
          <a href="/projectList?pageNumber=0"><img src="/resources/img/circul.png"></a>
        </div>
        <nav class="main-project-bottom-menu">
          <a href="#" class="active-main-menu-link">Реструктуризация</a>
          <a href="#">Готовый прототип</a>
          <a href="#">Проект на бумаге</a>
          <a href="#">Ноу-Хау</a>
        </nav>
      </div>
      <div class="main-news-wrap">
        <p>Новости</p>

        <div class="main-newsPic-wrap">
          <a href="/blog-post/news?pageNumber=0"><img src="/resources/img/yagazetko.png"></a>
        </div>
        <nav class="main-news-bottom-menu">
          <a href="#" class="active-main-menu-link">Киев</a>
          <a href="#">Львов</a>
          <a href="#">Харьков</a>
          <a href="#">Запорожье</a>
        </nav>
      </div>
    </div>
    <div class="main-slider-wrap">
      <ul class="bxslider">
        <li><img src="/resources/img/slider-item.png"/></li>
        <li><img src="/resources/img/slider-item1.png"/></li>
        <li><img src="/resources/img/slider-item2.png"/></li>
      </ul>
    </div>
  </div>
</section>