<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 04.01.2016
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
  <!-- Header-bar -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="<c:url value="/admin" />">Панель управления</a>
  </div>
  <!-- Header-bar -->

  <!-- Header dropdown menu -->
  <ul class="nav navbar-top-links navbar-right">
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
      </a>
      <ul class="dropdown-menu dropdown-user">
        <%--<li><a href="<c:url value="/profileEditor" />"><i class="fa fa-user fa-fw"></i> Профиль</a>--%>
        <%--</li>--%>
        <%--<li class="divider"></li>--%>
        <li><a href="<c:url value="/logout" />"><i class="fa fa-sign-out fa-fw"></i> Выход</a>
        </li>
      </ul>
    </li>
  </ul>
  <!-- Header dropdown menu -->

  <!-- Left sidebar menu -->
  <div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
      <ul class="nav" id="side-menu">
        <li class="sidebar-search">
          <div class="input-group custom-search-form">
            <input type="text" class="form-control" placeholder="Поиск...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                  <i class="fa fa-search"></i>
                                </button>
                            </span>
          </div>
        </li>

        <li>
          <a href="<c:url value="/admin-users" />"><i class="fa fa-dashboard fa-fw"></i>Пользователи</a>
        </li>
        <li>
          <a href="<c:url value="/admin-admins" />"><i class="fa fa-table fa-fw"></i>Админы</a>
        </li>
        <li>
          <a href="<c:url value="/admin-offers-new" />"><i class="fa fa-credit-card fa-fw"></i>Новые объявления</a>
        </li>
        <li>
          <a href="<c:url value="/admin-tariffs" />"><i class="fa fa-credit-card fa-fw"></i>Тарифы</a>
        </li>
        <li>
          <a href="<c:url value="/admin-rents" />"><i class="fa fa-credit-card fa-fw"></i>Аренда</a>
        </li>
      </ul>
    </div>
  </div>
  <!-- Left sidebar menu -->
</nav>