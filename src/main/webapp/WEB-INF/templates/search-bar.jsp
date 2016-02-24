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

            <div id="profileFilterBlock" class="hidefilter" style="display: none">
                <div class="filterContacts">
                    <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter1" value="legal_entity" name="profileType"/><span></span></label>
                    <label for="checkbox-filter1">Юр. лицо</label>
                </div>
                <div class="filterContacts">
                    <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter2" value="individual" name="profileType"/><span></span></label>
                    <label for="checkbox-filter2">Физ. лицо</label>
                </div>
                <div class="filterContacts">
                    <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter3" value="entrepreneur" name="profileType"/><span></span></label>
                    <label for="checkbox-filter3">ФОП</label>
                </div>
            </div>

            <div id="newsFilterBlock" class="hidefilter" style="display: none">
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

            <div id="offerFilterBlock" class="hidefilter" style="display: none">
                <div class="filterADS">
                    <div class="clearfix"></div>

                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/kids.png" alt="kids"></a>
                        <a href="#">Малыши</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/Zoo.png" alt="Zoo"></a>
                        <a href="#">Zoo</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/StyleAndFashion.png" alt="StyleAndFashion"></a>
                        <a href="#">Стиль и мода</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/SportAndHobby.png" alt="SportAndHobby"></a>
                        <a href="#">Спорт и хобби</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/Equipment.png" alt="Equipment"></a>
                        <a href="#">Техника</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/HouseAndCottage.png" alt="HouseAndCottage"></a>
                        <a href="#">Дома и дача</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/Barter.png" alt="Barter"></a>
                        <a href="#">Бартер</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/TheProperty.png" alt="TheProperty"></a>
                        <a href="#">Недвижимость</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/Transport.png" alt="Transport"></a>
                        <a href="#">Транспорт</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/BusinessAndServices.png" alt="BusinessAndServices"></a>
                        <a href="#">Бизнес и услуги</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/Volunteering.png" alt="Volunteering"></a>
                        <a href="#">Волонтерство</a>
                    </div>
                    <div class="ItemADS">
                        <a href="#"><img src="/resources/images/IsFree.png" alt="IsFree"></a>
                        <a href="#">Бесплатно</a>
                    </div>

                    <div class="clearfix"></div>
                </div>
            </div>

            <div id="tenderFilterBlock" class="hidefilter" style="display: none">
                <form class="filterArtists" action="#">
                    <label class="filterArtistsLabel" for="filterArtistsInput">Cоздатель:&nbsp;</label>
                    <input id="filterArtistsInput" type="text" placeholder="Автозаполнение"><br>

                    <label class="filterArtistsLabel marginTop" for="checkboxArtists">По актуальности:&nbsp;</label>
                    <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkboxArtists" value="1" name="k"/><span></span></label>

                    <label class="filterArtistsLabel marginTop marginLeft">По оценке:&nbsp;</label>
                    <div class="selectBoxEvaluation">
                        <select class="form-control">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>

                    <button type="submit">Искать по фильтру</button>
                </form>
            </div>

        <div id="tenderFilterBlock" class="hidefilter" style="display: none">
            <div class="tenderFilter">

                <div class="column">
                    <form action="#">
                        <div class="clearfix"></div>

                        <label for="tenderNumber">Номер тендера:</label>
                        <input type="text" placeholder="Автозаполнение" id="tenderNumber">

                        <div class="clearfix"></div>

                        <label for="filterNACE">КВЭДЫ:</label>
                        <input type="text" placeholder="Автозаполнение" id="filterNACE">

                        <div class="clearfix"></div>

                        <label for="Author">Автор:</label>
                        <input type="text" id="Author">

                        <div class="clearfix"></div>

                        <label>Тип тендера:</label>
                        <div class="tenderRadio">
                            <label><input type="radio" value="open" name="k"/><span></span></label><p>открытый</p>
                            <label><input type="radio" value="open" name="k"/><span></span></label><p>закрытый</p>
                        </div>

                        <div class="clearfix"></div>

                        <label>Ожидаемая стоимость: от&nbsp;</label>
                        <input type="text" class="moneyAfter">
                        <p class="tenderP">до</p>
                        <input type="text" class="moneyAfter">

                        <div class="clearfix"></div>
                    </form>
                </div>

                <div class="column">
                    <h2>Адрес (место проведения):</h2>
                    <form action="#">
                        <div class="location">
                            <img src="/resources/images/location.png" alt="location">
                            <label for="region">Область</label>
                            <input type="text" id="region">
                            <label for="city">Город</label>
                            <input type="text" id="city">

                            <div class="clearfix"></div>
                        </div>
                        <label for="">Период проведения:</label>
                        <p class="datePickPi">от <input type="text" id="datepicker"></p>
                        <p class="datePickPi">до <input type="text" id="datepicker2"></p>
                        <label for="available">Доступные к участию:</label>
                        <label class="participate"><input type="checkbox" id="available" value="1" name="k"/><span></span></label>

                        <div class="clearfix"></div>
                    </form>
                    <button type="button">Искать по фильтру</button>
                </div>

                <div class="clearfix"></div>
            </div>
        </div>

        <div class="clearfix"></div>

        <div class="search-img"></div>

        <div class="clearfix"></div>
    </div>
</div>