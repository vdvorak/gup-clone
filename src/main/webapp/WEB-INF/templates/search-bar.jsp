<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container2">
    <div class="search">
        <%--<form action="#" id="form" role="form">--%>
        <input type="text" id="searchInput" class="form-control sear" name="search" placeholder="Поиск">
        <div class="selectBox">
            <select class="form-control" id="selectedService">
                <option value="profile">Пользователи</option>
                <option value="project">Проекты</option>
                <option value="tender">Тендеры</option>
                <option value="offer">Обьявления</option>
            </select>
        </div>
        <button id="searchButton" class="submit-search">Найти <i class="fa fa-search fa-flip-horizontal"></i></button>
        <%--</form> type="submit"  --%>
        <button class="users" id="userListBtn">Пользователи</button>
        <img src="/resources/images/caretka.png" alt="caret">
    </div>
</div>