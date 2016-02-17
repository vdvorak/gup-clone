<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container2">
    <div class="search">
        <input type="text" id="searchInput" class="form-control sear" name="search" placeholder="Поиск">
        <div class="selectBox">
            <select class="form-control" id="selectedService">
                <option value="profile">Пользователи</option>
                <option value="news">Новости</option>
                <option value="project">Проекты</option>
                <option value="tender">Тендеры</option>
                <option value="offer">Обьявления</option>
            </select>
        </div>
        <button id="searchButton" class="submit-search">Найти <i class="fa fa-search fa-flip-horizontal"></i></button>
        <button class="users" id="userListBtn">Пользователи</button>
        <div class="clearfix"></div>

            <div id="profileFilterBlock" style="display: none">
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
            </div>

            <div id="newsFilterBlock" style="display: none">
                <div class="categories">
                    <p>Рубрики</p>
                    <div class="categoriesItem">
                        <a href="#"><img src="/resources/images/science.png" alt="science"></a>
                        <a href="#">Наука и техника</a>
                    </div>
                    <div class="categoriesItem">
                        <a href="#"><img src="/resources/images/art.png" alt="art"></a>
                        <a href="#">Искусство</a>
                    </div>
                    <div class="categoriesItem">
                        <a href="#"><img src="/resources/images/savor.png" alt="savor"></a>
                        <a href="#">Светская жизнь</a>
                    </div>
                    <div class="categoriesItem">
                        <a href="#"><img src="/resources/images/Policy.png" alt="Policy"></a>
                        <a href="#">Политика</a>
                    </div>
                    <div class="categoriesItem">
                        <a href="#"><img src="/resources/images/WorldAndSociety.png" alt="World and Society"></a>
                        <a href="#">Мир и общество</a>
                    </div>
                    <div class="categoriesItem">
                        <a href="#"><img src="/resources/images/Economy.png" alt="Economy"></a>
                        <a href="#">Економика</a>
                    </div>
                    <div class="categoriesItem">
                        <a href="#"><img src="/resources/images/Sport,hobby.png" alt="Sport, hobby"></a>
                        <a href="#">Спорт, хобби</a>
                    </div>
                    <div class="categoriesItem">
                        <a href="#"><img src="/resources/images/SocialNetwork.png" alt="Social network"></a>
                        <a href="#">Соц.сети</a>
                    </div>
                </div>
            </div>

        <div class="clearfix"></div>

        <div class="search-img"></div>

        <div class="clearfix"></div>
    </div>
</div>