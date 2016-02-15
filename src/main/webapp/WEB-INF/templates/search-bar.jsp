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
        <div class="clearfix"></div>

        <form id="filterForm" action="#" role="form">
            <div class="filterContacts">
                <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter1" value="1" name="k"/><span></span></label>
                <label for="checkbox-filter1">Юр. лицо</label>
            </div>
            <div class="filterContacts">
                <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter2" value="1" name="k"/><span></span></label>
                <label for="checkbox-filter2">Физ. лицо</label>
            </div>
            <div class="filterContacts">
                <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter3" value="1" name="k"/><span></span></label>
                <label for="checkbox-filter3">ФОП</label>
            </div>
        </form>

        <div class="clearfix"></div>

        <div class="search-img"></div>

        <div class="clearfix"></div>
    </div>
</div>