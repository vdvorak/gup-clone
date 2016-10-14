<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container2">
    <div class="search">
        <input type="text" id="searchInput" class="form-control sear" name="search" placeholder="Поиск">

        <div class="selectBox">
            <select class="form-control" id="selectedService">
                <option value="profile">Пользователи</option>
                <option value="offer">Обьявления</option>
            </select>
        </div>
        <button id="searchButton" class="submit-search">Найти <i class="fa fa-search fa-flip-horizontal"></i></button>
        <button class="users" id="userListBtn">Все пользователи</button>
        <div class="clearfix"></div>

        <div id="profileFilterBlock" class="hidefilter" style="display: none">
            <div class="filterContacts">
                <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter1"
                                                     value="legal_entity" name="profileType"/><span></span></label>
                <label for="checkbox-filter1">Юр. лицо</label>
            </div>
            <div class="filterContacts">
                <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter2"
                                                     value="individual" name="profileType"/><span></span></label>
                <label for="checkbox-filter2">Физ. лицо</label>
            </div>
            <div class="filterContacts">
                <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter3"
                                                     value="entrepreneur" name="profileType"/><span></span></label>
                <label for="checkbox-filter3">ФОП</label>
            </div>
        </div>

        <div id="offerFilterBlock" class="hidefilter" style="display: none">
            <div class="filterADS">
                <div class="ItemADS">
                    <a id="36" href="#" data-level="1"><img src="/resources/images/kids.png" alt="kids"></a>
                    <a href="#">Малыши</a>

                    <div class="descriptionTitleLeft">
                        <img src="/resources/images/descriptionTitleLeft.png" alt="descriptionTitleLeft">
                    </div>
                </div>
                <div class="ItemADS">
                    <a id="35" href="#" data-level="1"><img src="/resources/images/Zoo.png" alt="Zoo"></a>
                    <a href="#">Zoo</a>

                    <div class="descriptionTitleLeft">
                        <img src="/resources/images/descriptionTitleLeft.png" alt="descriptionTitleLeft">
                    </div>
                </div>
                <div class="ItemADS">
                    <a id="891" href="#" data-level="1"><img src="/resources/images/StyleAndFashion.png"
                                                             alt="StyleAndFashion"></a>
                    <a href="#">Стиль и мода</a>

                    <div class="descriptionTitleRight">
                        <img src="/resources/images/descriptionTitleRight.png" alt="descriptionTitleRight">
                    </div>
                </div>
                <div class="ItemADS">
                    <a id="903" href="#" data-level="1"><img src="/resources/images/SportAndHobby.png"
                                                             alt="SportAndHobby"></a>
                    <a href="#">Спорт и хобби</a>

                    <div class="descriptionTitleRight">
                        <img src="/resources/images/descriptionTitleRight.png" alt="descriptionTitleRight">
                    </div>
                </div>
                <div class="ItemADS">
                    <a id="37" href="#" data-level="1"><img src="/resources/images/Equipment.png"
                                                            alt="Equipment"></a>
                    <a href="#">Техника</a>

                    <div class="descriptionTitleLeft">
                        <img src="/resources/images/descriptionTitleLeft.png" alt="descriptionTitleLeft">
                    </div>
                </div>
                <div class="ItemADS">
                    <a id="899" href="#" data-level="1"><img src="/resources/images/HouseAndCottage.png"
                                                             alt="HouseAndCottage"></a>
                    <a href="#">Дома и дача</a>

                    <div class="descriptionTitleLeft">
                        <img src="/resources/images/descriptionTitleLeft.png" alt="descriptionTitleLeft">
                    </div>
                </div>
                <div class="ItemADS">
                    <a id="exchange" href="#" data-level="1"><img src="/resources/images/Barter.png"
                                                                  alt="Barter"></a>
                    <a href="#">Бартер</a>
                </div>
                <div class="ItemADS">
                    <a id="1" href="#" data-level="1"><img src="/resources/images/TheProperty.png"
                                                           alt="TheProperty"></a>
                    <a href="#">Недвижимость</a>

                    <div class="descriptionTitleRight">
                        <img src="/resources/images/descriptionTitleRight.png" alt="descriptionTitleRight">
                    </div>
                </div>
                <div class="ItemADS">
                    <a id="3" href="#" data-level="1"><img src="/resources/images/Transport.png"
                                                           alt="Transport"></a>
                    <a href="#">Транспорт</a>

                    <div class="descriptionTitleLeft">
                        <img src="/resources/images/descriptionTitleLeft.png" alt="descriptionTitleLeft">
                    </div>
                </div>
                <div class="ItemADS">
                    <a id="7" href="#" data-level="1"><img
                            src="/resources/images/BusinessAndServices.png"
                            alt="BusinessAndServices"></a>
                    <a href="#">Бизнес и услуги</a>

                    <div class="descriptionTitleLeft">
                        <img src="/resources/images/descriptionTitleLeft.png" alt="descriptionTitleLeft">
                    </div>
                </div>
                <div class="ItemADS">
                    <a id="1435" href="#" data-level="1"><img src="/resources/images/Volunteering.png"
                                                              alt="Volunteering"></a>
                    <a href="#">Волонтерство</a>
                </div>
                <div class="ItemADS">
                    <a id="free" href="#" data-level="1"><img src="/resources/images/IsFree.png"
                                                              alt="IsFree"></a>
                    <a href="#">Бесплатно</a>
                </div>

                <div class="clearfix"></div>

                <label for="input-region-search">Регион</label>

                <div id="filter-container-region-search">
                    <i id="filter-icon-gps" class="fa fa-map-marker"></i>
                    <input id="input-region-search" type="text" placeholder="Вся Украина" readonly>
                    <i id="filter-icon-close" class="fa fa-times"></i>
                </div>
                <div id="filter-region-menu" class="filter-elem-hidden">
                    <div id="filter-region-nav">
                        <a class="filter-region-a left-a">Вся Украина</a>
                        <a class="filter-region-a left-a filter-elem-hidden"><i
                                class="fa fa-angle-left"></i> Вернуться к выбору региона</a>
                        <a class="filter-region-a right-a filter-elem-hidden">Все объявления по региону</a>
                    </div>
                    <ul id="filter-area-box" class="filter-region-body">
                        <input id="input-selected-area" type="text" class="filter-elem-hidden">

                        <div class="div-region div-region-left">
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Винницкая область</a>
                                </li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Волынская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Донецкая область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Житомирская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Закарпатская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a href="#">Ивано‑Франковская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Киевская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Кировоградская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Крым</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Луганская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Львовская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                        </div>
                        <div class="div-region div-region-right">
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Николаевская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Одесская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Ровенская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Сумская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Тернопольская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Харьковская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Херсонская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Хмельницкая область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Черкасская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Черниговская область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                            <div class="filter-region-elem">
                                <li class="region-li li-area"><a class="filter-region-a" href="#">Черновицкая область</a></li>
                                <i class="fa fa-angle-right li-angle"></i>
                            </div>
                        </div>
                    </ul>
                    <ul id="filter-city-box" class="filter-region-body filter-elem-hidden">
                        <input id="input-selected-city" type="text" class="filter-elem-hidden">

                        <div class="div-region div-region-left">
                            <li class="filter-region-elem li-city filter-elem-hidden"></li>
                        </div>
                        <div class="div-region div-region-right">

                        </div>
                    </ul>
                </div>

                <div class="clearfix"></div>


                <div class="superFilter">
                    <form action="#">

                        <label for="select-categories-3lvl" style="display: none">Выберите подкатегорию:</label>
                        <select id="select-categories-3lvl" style="display: none">
                            <option>Все объявления</option>
                        </select>

                        <div class="clearfix"></div>

                        <div class="price" style="display: none">
                            <p>Цена:</p>
                            <select id="filter-price" name="price" value="">

                            </select>

                            <div id="price-wrapper" style="display: none">
                                <input id="priceMin" type="number" placeholder="от">
                                <input id="priceMax" type="number" placeholder="до">
                                <select id="filter-currency" name="currency">
                                    <option>UAH</option>
                                    <option>USD</option>
                                    <option>EUR</option>
                                </select>
                            </div>
                        </div>
                        <div class="clearfix"></div>

                        <div class="parameters">

                        </div>

                        <div class="clearfix"></div>

                        <button id="btn-offers-search" type="submit">Искать по фильтру</button>

                        <div class="clearfix"></div>
                    </form>
                </div>
            </div>
        </div>

        <div class="clearfix"></div>

        <div class="search-img"></div>

        <div class="clearfix"></div>
    </div>
</div>