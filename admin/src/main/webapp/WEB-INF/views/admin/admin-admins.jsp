<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Редактор админов</title>

    <!-- Links -->
    <jsp:include page="/WEB-INF/templates/admin-top-links.jsp"/>
    <!-- Links -->
</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="/WEB-INF/templates/admin-left-bar.jsp"/>
    <!-- Navigation -->

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Информация</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>


        <div class="row">
            <div class="col-lg-12">

                <div class="col-lg-8">
                    <div class="span9">

                        <div id="content">
                            <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                <li><a href="#adminsTable" data-toggle="tab">Админы</a></li>
                                <li><a href="#moderatorsTable" data-toggle="tab">Модераторы</a></li>
                                <li><a href="#spectatorsTable" data-toggle="tab">Наблюдатели</a></li>
                                <li>
                                    <button type="button" class="btn btn-default btn-md" data-toggle="modal"
                                            data-target="#charge" style="margin-left: 25px">
                                        Создать админа
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="btn btn-default btn-md" data-toggle="modal"
                                            data-target="#invest" style="margin-left: 25px">
                                        Выбрать из пользователей портала
                                    </button>
                                </li>
                            </ul>

                            <!-- Modal -->
                            <div class="modal fade" id="charge" tabindex="-1"
                                 role="dialog" aria-labelledby="myModalLabel"
                                 style="z-index: 1051">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close"
                                                    data-dismiss="modal"
                                                    aria-label="Close"><span
                                                    aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title" id="mModalLabel">
                                                Создать</h4>
                                        </div>

                                        <div class="col-xs-4">
                                            <br>
                                            <input id="newLogin" type="text"
                                                   class="form-control"
                                                   placeholder="email">
                                            <br>
                                            <input id="newPassword" type="text"
                                                   class="form-control"
                                                   placeholder="Пароль">
                                        </div>

                                        <div class="col-xs-4">
                                            <br>

                                            <div class="checkbox">
                                                <label><input name="role_radio" id="adminCheck" type="radio"
                                                              value="ROLE_ADMIN">ADMIN</label>
                                            </div>
                                            <div class="checkbox">
                                                <label><input name="role_radio" id="moderatorCheck" type="radio"
                                                              value="ROLE_MODERATOR">MODERATOR</label>
                                            </div>
                                            <div class="checkbox">
                                                <label><input name="role_radio" id="spectatorCheck" type="radio"
                                                              value="ROLE_SPECTATOR">SPECTATOR</label>
                                            </div>
                                        </div>

                                        <div class="modal-footer">

                                            <button id="create" type="submit"
                                                    class="btn btn-primary">Создать
                                            </button>
                                            <button type="button"
                                                    class="btn btn-default"
                                                    data-dismiss="modal">Отмена
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- Modal -->
                            <div class="modal fade" id="invest" tabindex="-1"
                                 role="dialog" aria-labelledby="myModalLabel"
                                 style="z-index: 1051">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close"
                                                    data-dismiss="modal"
                                                    aria-label="Close"><span
                                                    aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title">Редактировать</h4>
                                        </div>

                                        <div class="col-xs-4">
                                            <br>

                                            <div id="scrollable-dropdown-menu">
                                                <input id="typeahead" class="typeahead tt-input form-control"
                                                       type="text" placeholder="users" autocomplete="off"
                                                       spellcheck="false" dir="auto"
                                                       style="position: relative; vertical-align: top; background-color: transparent;">
                                            </div>
                                        </div>
                                        <div class="col-xs-4">
                                            <br>

                                            <div class="checkbox">
                                                <label><input id="adminCheck2" class="ch2" type="checkbox"
                                                              value="ROLE_ADMIN">ADMIN</label>
                                            </div>
                                            <div class="checkbox">
                                                <label><input id="supportCheck2" class="ch2" type="checkbox"
                                                              value="ROLE_SUPPORT">SUPPORT</label>
                                            </div>
                                            <div class="checkbox">
                                                <label><input id="moderatorCheck2" class="ch2" type="checkbox"
                                                              value="ROLE_MODERATOR">MODERATOR</label>
                                            </div>
                                        </div>
                                        <div class="col-xs-4">
                                            <br>

                                            <%--<div class="checkbox">--%>
                                                <%--<label><input id="anonymousCheck2" class="ch2" type="checkbox"--%>
                                                              <%--value="ROLE_ANONYMOUS">ANONYMOUS</label>--%>
                                            <%--</div>--%>
                                            <div class="checkbox">
                                                <label><input id="userCheck2" class="ch2" type="checkbox"
                                                              value="ROLE_USER">USER</label>
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button id="update" type="submit"
                                                    class="btn btn-primary">Сохранить
                                            </button>
                                            <button type="button"
                                                    class="btn btn-default"
                                                    data-dismiss="modal">Отмена
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="tab-content">
                                <div class="tab-pane active" id="adminsTable">

                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="panel panel-default">
                                                <!-- /.panel-heading -->
                                                <div class="panel-body">
                                                    <div class="dataTable_wrapper">

                                                        <table id="admins"
                                                               class="table table-striped table-bordered table-hover"
                                                               cellspacing="0" width="100%">
                                                            <thead>
                                                            <tr>
                                                                <th>Фото</th>
                                                                <th>Имя</th>
                                                                <th>Логин</th>
                                                                <th>Дата регистрации</th>
                                                            </tr>
                                                            </thead>
                                                        </table>


                                                        <table class="table table-user-information">
                                                            <tbody>
                                                            <h3 class="panel-title">Редактировать профиль</h3>
                                                            <tr>
                                                                <td>ID профиля:</td>
                                                                <td><input id="input-admin-id" class="form-control"
                                                                           name="adminId" readonly required>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>

                                                        <a id="adminEditHref" href="">
                                                            <button id="editAdminProfileButton"
                                                                    class="btn btn-primary disabled">
                                                                Редактировать
                                                            </button>
                                                        </a>
                                                        <button id="deleteAdminProfileButton"
                                                                class="deleteAdminButton btn btn-primary disabled">
                                                            Удалить
                                                        </button>


                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="moderatorsTable">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="panel panel-default">
                                                <!-- /.panel-heading -->
                                                <div class="panel-body">
                                                    <div class="dataTable_wrapper">

                                                        <table id="moderators"
                                                               class="table table-striped table-bordered table-hover"
                                                               cellspacing="0" width="100%">
                                                            <thead>
                                                            <tr>
                                                                <th>Фото</th>
                                                                <th>Имя</th>
                                                                <th>Логин</th>
                                                                <th>Дата регистрации</th>
                                                            </tr>
                                                            </thead>
                                                        </table>

                                                        <table class="table table-user-information">
                                                            <tbody>
                                                            <h3 class="panel-title">Редактировать профиль</h3>
                                                            <tr>
                                                                <td>ID профиля:</td>
                                                                <td><input id="input-moderator-id" class="form-control"
                                                                           name="moderatorId" readonly required>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>


                                                        <a id="moderatorEditHref" href="">
                                                            <button id="editModeratorProfileButton"
                                                                    class="btn btn-primary disabled">
                                                                Редактировать
                                                            </button>
                                                        </a>
                                                        <button id="deleteModaratorProfileButton"
                                                                class="deleteAdminButton btn btn-primary disabled ">
                                                            Удалить
                                                        </button>


                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="spectatorsTable">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="panel panel-default">
                                                <!-- /.panel-heading -->
                                                <div class="panel-body">
                                                    <div class="dataTable_wrapper">

                                                        <table id="spectators"
                                                               class="table table-striped table-bordered table-hover"
                                                               cellspacing="0" width="100%">
                                                            <thead>
                                                            <tr>
                                                                <th>Фото</th>
                                                                <th>Имя</th>
                                                                <th>Логин</th>
                                                                <th>Дата регистрации</th>
                                                            </tr>
                                                            </thead>
                                                        </table>

                                                        <table class="table table-user-information">
                                                            <tbody>
                                                            <h3 class="panel-title">Редактировать профиль</h3>
                                                            <tr>
                                                                <td>ID профиля:</td>
                                                                <td><input id="input-spectator-id" class="form-control"
                                                                           name="spectatorId" readonly required>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>


                                                        <a id="spectatorEditHref" href="">
                                                            <button id="editSpectatorProfileButton"
                                                                    class="btn btn-primary disabled">
                                                                Редактировать
                                                            </button>
                                                        </a>
                                                        <button id="deleteSpectatorProfileButton"
                                                                class="deleteSpecctatorButton btn btn-primary disabled ">
                                                            Удалить
                                                        </button>


                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.panel -->

<!-- Bottom Links -->
<jsp:include page="/WEB-INF/templates/admin-bottom-links.jsp"/>
<script src="/resources/js/typeahead.js"></script>
<!-- Bottom Links -->

<script src="/resources/js/admin/admin-admins.js"></script>
</body>
</html>

