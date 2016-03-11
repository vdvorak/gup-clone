<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container2">
    <div class="search">
        <input type="text" id="searchInput" class="form-control sear" name="search" placeholder="Поиск">
        <div class="selectBox">
            <select class="form-control" id="selectedService">
                <option value="profile">Пользователи</option>
                <option value="news">Новости</option>
                <option value="project">Проекты</option>
                <option value="offer">Обьявления</option>
                <option value="tender">Тендеры</option>
                <option value="doer">Исполнители</option>
            </select>
        </div>
        <button id="searchButton" class="submit-search">Найти <i class="fa fa-search fa-flip-horizontal"></i></button>
        <button class="users" id="userListBtn">Все пользователи</button>
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
                    <div class="ItemADS">
                        <a id="36" href="#"><img src="/resources/images/kids.png" alt="kids"></a>
                        <a href="#">Малыши</a>
                        <div class="descriptionTitleLeft">
                            <a href="#">Earthshaker</a>
                            <a href="#">Axe</a>
                            <a href="#">Sven</a>
                            <a href="#">Pudge</a>
                            <a href="#">Tiny</a>
                            <a href="#">Sand King</a>
                            <a href="#">Kunkka</a>
                            <a href="#">Slardar</a>
                            <a href="#">Beastmaster</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="35" href="#"><img src="/resources/images/Zoo.png" alt="Zoo"></a>
                        <a href="#">Zoo</a>
                        <div class="descriptionTitleLeft">
                            <a href="#">Tidehunter</a>
                            <a href="#">Dragon Knight</a>
                            <a href="#">Wraith King</a>
                            <a href="#">Clockwerk</a>
                            <a href="#">Lifestealer</a>
                            <a href="#">Omniknight</a>
                            <a href="#">Night Stalker</a>
                            <a href="#">Huskar</a>
                            <a href="#">Doom</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="891" href="#"><img src="/resources/images/StyleAndFashion.png" alt="StyleAndFashion"></a>
                        <a href="#">Стиль и мода</a>
                        <div class="descriptionTitleRight">
                            <a href="#">Alchemist</a>
                            <a href="#">Spirit Breaker</a>
                            <a href="#">Brewmaster</a>
                            <a href="#">Lycan</a>
                            <a href="#">Treant Protector</a>
                            <a href="#">Chaos Knight</a>
                            <a href="#">Io</a>
                            <a href="#">Undying</a>
                            <a href="#">Centaur Warrunner</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="903" href="#"><img src="/resources/images/SportAndHobby.png" alt="SportAndHobby"></a>
                        <a href="#">Спорт и хобби</a>
                        <div class="descriptionTitleRight">
                            <a href="#">Magnus</a>
                            <a href="#">Timbersaw</a>
                            <a href="#">Abaddon</a>
                            <a href="#">Bristleback</a>
                            <a href="#">Tusk</a>
                            <a href="#">Elder Titan</a>
                            <a href="#">Legion Commander</a>
                            <a href="#">Earth Spirit</a>
                            <a href="#">Phoenix</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="37" href="#"><img src="/resources/images/Equipment.png" alt="Equipment"></a>
                        <a href="#">Техника</a>
                        <div class="descriptionTitleLeft">
                            <a href="#">Anti-Mage</a>
                            <a href="#">Bloodseeker</a>
                            <a href="#">Drow Ranger</a>
                            <a href="#">Shadow Fiend</a>
                            <a href="#">Juggernaut</a>
                            <a href="#">Razor</a>
                            <a href="#">Mirana</a>
                            <a href="#">Venomancer</a>
                            <a href="#">Morphling</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="899" href="#"><img src="/resources/images/HouseAndCottage.png" alt="HouseAndCottage"></a>
                        <a href="#">Дома и дача</a>
                        <div class="descriptionTitleLeft">
                            <a href="#">Faceless Void</a>
                            <a href="#">Phantom Lancer</a>
                            <a href="#">Phantom Assassin</a>
                            <a href="#">Vengeful Spirit</a>
                            <a href="#">Viper</a>
                            <a href="#">Riki</a>
                            <a href="#">Clinkz</a>
                            <a href="#">Sniper</a>
                            <a href="#">Broodmother</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="exchange" href="#"><img src="/resources/images/Barter.png" alt="Barter"></a>
                        <a href="#">Бартер</a>
                        <div class="descriptionTitleRight">
                            <a href="#">Templar Assassin</a>
                            <a href="#">Weaver</a>
                            <a href="#">Luna</a>
                            <a href="#">Spectre</a>
                            <a href="#">Bounty Hunter</a>
                            <a href="#">Nyx Assassin</a>
                            <a href="#">Ursa</a>
                            <a href="#">Meepo</a>
                            <a href="#">Gyrocopter</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="1" href="#"><img src="/resources/images/TheProperty.png" alt="TheProperty"></a>
                        <a href="#">Недвижимость</a>
                        <div class="descriptionTitleRight">
                            <a href="#">Slark</a>
                            <a href="#">Lone Druid</a>
                            <a href="#">Medusa</a>
                            <a href="#">Naga Siren</a>
                            <a href="#">Terrorblade</a>
                            <a href="#">Troll Warlord</a>
                            <a href="#">Arc Warden</a>
                            <a href="#">Ember Spirit</a>
                            <a href="#">Crystal Maiden</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="3" href="#"><img src="/resources/images/Transport.png" alt="Transport"></a>
                        <a href="#">Транспорт</a>
                        <div class="descriptionTitleLeft">
                            <a href="#">Bane</a>
                            <a href="#">Puck</a>
                            <a href="#">Lich</a>
                            <a href="#">Storm Spirit</a>
                            <a href="#">Lion</a>
                            <a href="#">Windranger</a>
                            <a href="#">Witch Doctor</a>
                            <a href="#">Zeus</a>
                            <a href="#">Enigma</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="7" href="#"><img src="/resources/images/BusinessAndServices.png" alt="BusinessAndServices"></a>
                        <a href="#">Бизнес и услуги</a>
                        <div class="descriptionTitleLeft">
                            <a href="#">Lina</a>
                            <a href="#">Necrophos</a>
                            <a href="#">Shadow Shaman</a>
                            <a href="#">Warlock</a>
                            <a href="#">Tinker</a>
                            <a href="#">Queen of Pain</a>
                            <a href="#">Nature's Prophet</a>
                            <a href="#">Death Prophet</a>
                            <a href="#">Enchantress</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="1435" href="#"><img src="/resources/images/Volunteering.png" alt="Volunteering"></a>
                        <a href="#">Волонтерство</a>
                        <div class="descriptionTitleRight">
                            <a href="#">Pugna</a>
                            <a href="#">Jakiro</a>
                            <a href="#">Dazzle</a>
                            <a href="#">Chen</a>
                            <a href="#">Leshrac</a>
                            <a href="#">Silencer</a>
                            <a href="#">Dark Seer</a>
                            <a href="#">Ogre Magi</a>
                            <a href="#">Batrider</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>
                    <div class="ItemADS">
                        <a id="free" href="#"><img src="/resources/images/IsFree.png" alt="IsFree"></a>
                        <a href="#">Бесплатно</a>
                        <div class="descriptionTitleRight">
                            <a href="#">Rubick</a>
                            <a href="#">Ancient Apparition</a>
                            <a href="#">Disruptor</a>
                            <a href="#">Invoker</a>
                            <a href="#">Keeper of the Light</a>
                            <a href="#">Outworld Devourer</a>
                            <a href="#">Skywrath Mage</a>
                            <a href="#">Shadow Demon</a>
                            <a href="#">Techies</a>
                            <a href="$">Cмотреть все обьявления</a>
                        </div>
                    </div>

                    <div class="clearfix"></div>

                    <div class="superFilter">
                        <form action="#">
                            <select>
                                <option>Подкатегория</option>
                                <option>Подкатегория2</option>
                                <option>Подкатегория3</option>
                                <option>Подкатегория4</option>
                                <option>Подкатегория5</option>
                            </select>
                            <select>
                                <option>Вид категории</option>
                                <option>Вид категории2</option>
                                <option>Вид категории3</option>
                                <option>Вид категории4</option>
                                <option>Вид категории5</option>
                            </select>

                            <div class="clearfix"></div>

                            <div class="price">
                                <p>Цена:</p>
                                <input type="text" placeholder="от">
                                <input type="text" placeholder="до">
                                <p>грн.<i class="fa fa-caret-down"></i></p>
                            </div>
                            <div class="price">
                                <p>Цена:</p>
                                <input type="text" placeholder="от">
                                <input type="text" placeholder="до">
                                <p>грн.<i class="fa fa-caret-down"></i></p>
                            </div>
                            <div class="price">
                                <p>Цена:</p>
                                <input type="text" placeholder="от">
                                <input type="text" placeholder="до">
                                <p>грн.<i class="fa fa-caret-down"></i></p>
                            </div>

                            <div class="clearfix"></div>

                            <input type="text">
                            <input type="text">
                            <input type="text">
                            <input type="text">
                            <input type="text">
                            <input type="text">
                            <input type="text">
                            <input type="text">
                            <input type="text">
                            <input type="text">

                            <div class="clearfix"></div>

                            <button id="btn-offers-search" type="submit">Искать по фильтру</button>

                            <div class="clearfix"></div>
                        </form>
                    </div>
                </div>
            </div>

            <div id="tenderFilterBlock" class="hidefilter" style="display: none">

                <div class="clearfix"></div>
                <div class="clearfix"></div>
                <div class="clearfix"></div>

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
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

        <div id="doerFilterBlock" class="hidefilter" style="display: none">
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
            </form>
        </div>

        <div class="clearfix"></div>

        <div class="search-img"></div>

        <div class="clearfix"></div>
    </div>
</div>