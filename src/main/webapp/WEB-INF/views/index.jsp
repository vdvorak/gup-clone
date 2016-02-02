<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="resources/css/bootstrap.css">
        <link rel="stylesheet" href="resources/css/bootstrap-theme.css">
        <link rel="stylesheet" href="resources/css/jquery.bxslider.css">
        <link rel="stylesheet" href="resources/css/main.css">
        <link rel="stylesheet" href="resources/css/font-awesome.css">
        <link rel="stylesheet" href="resources/css/media-queries.css">

        
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
        
        <div class="menu">
            <div class="container">
                
                <!-- не залогиненный -->
                <div class="notLogged">
                    <ul>
                        <li><a href="#">Регистрация</a></li>
                        <li><p>&nbsp;/&nbsp;</p></li>
                        <li><a id='go' href="#">Вход</a></li>
                    </ul>
                    
                    <div id="modal_form"><!-- Сaмo oкнo --> 
                        <div id="tab-container" class="tab-container">
                            <ul class='etabs'>
                                <li class='tab1'><a href="#registration">Регистрация</a></li>
                                <li class='tab2'><a href="#entry">Вход</a></li>
                            </ul>
                            
                            <div id="registration">
                                <h2>HTML Markup for these tabs</h2>
                                <!-- content -->
                            </div>
                            
                            <div id="entry">
                                <form class="contact_form" method="post" action="#" role="form">
                                    <ul class="contactUl">
                                        <li class="contactLi">
                                            <label for="email">Введите логин/ E-mail</label>
                                            <input type="email" name="email" id='email' placeholder="Не менее 6 символов" required>
                                        </li>
                                        <li class="contactLi">
                                            <label for="password">Пароль</label>
                                            <input type="password" name="password" id='password' required>
                                        </li>
                                        <li class="contactLi">
                                            <label for="checkbox">Remember me</label>
                                            <input type="checkbox" id="checkbox">
                                        </li>
                                        <li class="contactLi">
                                            <button class="submit" type="submit">Войти</button>
                                        </li>
                                    </ul>
                                </form>
                                <a class="contactA" href="#">Забыли пароль?</a>
                            </div>
                        </div>
                    </div>
                    <div id="overlay"></div><!-- Пoдлoжкa -->
                </div>
                
                <!-- залогиненный -->
                <div class="Logged">
                    <ul class="groupLi">
                        <li class="btnFace">
                            <img src="resources/images/face.png" alt="face">
                            <a class="menuName" href="#">Петров Василий</a>
                            <div class="dropFace">
                                <ul>
                                    <li><a href="#">Анкета</a></li>
                                    <li><a href="#">Сообщения</a></li>
                                    <li><a href="#">Уведомления</a></li>
                                    <li><a href="#">Контакты</a></li>
                                    <li><a href="#">Выход</a></li>
                                </ul>
                                <a class="edit" href="#">Редактировать страницу</a>
                            </div>
                        </li>
                        <il class='btnMail'>
                            <a class="btnMenu" href="#"><img src="resources/images/mail.png" alt="mail"></a>
                            <div class="mailDrop">
                                <a href="#"><img src="resources/images/LOGO.png" alt="LOGO"></a>
                                <form role="form" action="#">
                                    <textarea class="form-control" rows="7" id="comment"></textarea>
                                    <button type="submit" class="btnSubmit">Ответить</button>
                                </form>
                            </div>
                        </il>
                        <il><a class="btnMenu" href="#"><img src="resources/images/bell.png" alt="bell"></a></il>
                        <il class='btnbook'>
                            <a class="btnMenu" href="#"><img src="resources/images/book.png" alt="book"></a>
                            <div class="bookDrop">
                                <ul>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                    <li><a href="#"><img src="resources/images/MergedLayers.png" alt="logo"></a></li>
                                </ul>
                            </div>
                        </il>
                    </ul>
                    <ul class="money">
                        <li>
                            <a class="score" href="#">00.00. грн.</a>
                            <ul>
                                <li>
                                    <div class="balance">
                                        <button type="button" class="btnBalance" data-toggle="modal" data-target="#myModal">Пополнить баланс</button>
                                        <div class="modal fade" id="myModal" role="dialog">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <form action="#" id="formModal" role="form">
                                                        <div class="selectBox mod">
                                                            <select class="form-control">
                                                                <option>Liq Pay</option>
                                                                <option>2</option>
                                                                <option>3</option>
                                                                <option>4</option>
                                                                <option>5</option>
                                                            </select>
                                                        </div>
                                                        <input type="text" class="form-control sear mod1" placeholder="Введите сумму">
                                                        <button type="submit" class="submit">Пополнить</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        
        <div class="head">
            <div class="container">
                <div class="logo">
                    <a class="brand" href="#"><img src="resources/images/brand.png" alt="brand"></a>
                    <p class="logo-p">Global Ukranian Portal</p>
                </div>
                <div class="social">
                    <ul class="store">
                        <li><a href="#"><img src="resources/images/windowsStore.png" alt="Windows Store"></a></li>
                        <li><a href="#"><img src="resources/images/googlePlay.png" alt="Google Play"></a></li>
                        <li><a href="#"><img src="resources/images/appStore.png" alt="App Store"></a></li>
                    </ul>
                    <button class="socialBtn">ВСТУПИТЬ В ОРГАНИЗАЦИЮ <i class="fa fa-plus"></i></button>
                </div>
            </div>
        </div>
        
        <div class="container">
            <div class="search">
                <form action="#" id="form" role="form">
                    <input type="text" class="form-control sear" name="search" placeholder="Поиск">
                    <div class="selectBox">
                        <select class="form-control">
                            <option>Обьявления</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                    <button type="submit" class="submit-search">Найти <i class="fa fa-search fa-flip-horizontal"></i></button>
                </form>
                <button class="users">Пользователи</button>
                <img src="resources/images/caretka.png" alt="caret">
            </div>
        </div>
        
        <div class="container">
        	<ul class="mid">
        		<li>
        			<a href="#" class="ads-p">Обьявления</a>
					<div class="ads-img">
						<a href="#"><img class="img-responsive" src="resources/images/rupor.png" alt="rupor"></a>
					</div>
					<ul class="dropUl">
					    <li><a class="ads-a1" href="#">Топ обьявлений</a></li>
						<li><a class="ads-a1" href="#">Новые</a></li>
						<li><a class="ads-a1" href="#">Новые2</a></li>
						<li><a class="ads-drop" href="#">Новые3</a></li>
					</ul>
        		</li>
        		<li>
        			<a href="#" class="ads-p">Тендеры</a>
					<div class="ads-img">
						<a href="#"><img class="img-responsive" src="resources/images/hammer.png" alt="hammer"></a>
					</div>
					<ul class="dropUl">
					    <li><a class="ads-a1" href="#">Участвовать</a></li>
						<li><a class="ads-a1" href="#">Исполнители</a></li>
						<li><a class="ads-a1" href="#">Исполнители2</a></li>
						<li><a class="ads-drop" href="#">Исполнители3</a></li>
					</ul>
        		</li>
        		<li>
        			<a href="#" class="ads-p">Проекты</a>
					<div class="ads-img">
						<a href="#"><img class="img-responsive" src="resources/images/compass.png" alt="compass"></a>
					</div>
					<ul class="dropUl">
					    <li><a class="ads-a1" href="#">Реструкторизация</a></li>
						<li><a class="ads-a1" href="#">Готовый прототип</a></li>
						<li><a class="ads-a1" href="#">Проект на бумаге</a></li>
						<li><a class="ads-drop" href="#">Ноу-Хау</a></li>
					</ul>
        		</li>
        		<li>
        			<a href="#" class="ads-p">Новости</a>
					<div class="ads-img">
						<a href="#"><img class="img-responsive" src="resources/images/newspaper.png" alt="newspaper"></a>
					</div>
					<ul class="dropUl">
					    <li><a class="ads-a1" href="#">Киев</a></li>
						<li><a class="ads-a1" href="#">Львов</a></li>
						<li><a class="ads-a1" href="#">Харьков</a></li>
						<li><a class="ads-drop" href="#">Запорожье</a></li>
					</ul>
        		</li>
        		<li>
        			<div class="slider">
						<ul class="bxslider">
							<li><img src="resources/images/elektronnye-torgi-kto-30-04-2015.png" /></li>
							<li><img src="resources/images/elektronnye-torgi-kto-30-04-2015.png" /></li>
							<li><img src="resources/images/elektronnye-torgi-kto-30-04-2015.png" /></li>
						</ul>
					</div>
        		</li>
        	</ul>
        </div>
        
        <div class="container">
        	<div class="tidings">
        	    <div class="tidi1">
        			<a href="#"><img src="resources/images/tidi3.png" alt="tidi"></a>
        			<a href="#" class="tidi3-a">В колокольне Лавры открыли концертный зал</a>
        		</div>
        		<div class="tidi2">
        			<a href="#"><img src="resources/images/tidi1.png" alt="tidi"></a>
        			<a href="#" class="tidi1-a">История книгаря</a>
        		</div>
        		<div class="tidi3">
        			<a href="#"><img src="resources/images/tidi2.png" alt="tidi"></a>
        			<a href="#" class="tidi1-a">Легенда Хью Гласса</a>
        		</div>
        		<div class="tidi4">
        			<p class="data">Опубликовано: 22.10.16</p>
        			<a href="#"><img src="resources/images/tidi4.png" alt="tidi"></a>
        			<a class="tidi4-a" href="#">MONARCHY</a>
        			<p class="tidi4-p">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
        		</div>
        		<div class="tidi4">
        			<p class="data-tidi5">Опубликовано: 22.10.16</p>
        			<a class="tidi4-ar" href="#">MONARCHY</a>
        			<a class="tidi5-img" href="#"><img src="resources/images/tidi5.png" alt="tidi"></a>
        			<p class="tidi4-pr">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
        		</div>
        		<div class="tidi4">
        			<p class="data">Опубликовано: 22.10.16</p>
        			<a href="#"><img src="resources/images/tidi6.png" alt="tidi"></a>
        			<a class="tidi4-a" href="#">MONARCHY</a>
        			<p class="tidi4-p">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
        		</div>
        		<div class="tidi4">
        			<p class="data-tidi5">Опубликовано: 22.10.16</p>
        			<a class="tidi4-ar" href="#">MONARCHY</a>
        			<a class="tidi5-img" href="#"><img src="resources/images/tidi7.png" alt="tidi"></a>
        			<p class="tidi4-pr">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
        		</div>
        		
        		<div class="clearfix"></div>
        		
        		<div class="tidi-drop">
					<div class="tidi4">
						<p class="data">Опубликовано: 22.10.16</p>
						<a href="#"><img src="resources/images/tidi4.png" alt="tidi"></a>
						<a class="tidi4-a" href="#">MONARCHY</a>
						<p class="tidi4-p">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
					</div>
        		</div>
        		<img class="tidiDown" src="resources/images/caret.png" alt="caret">
        	</div>
        	
        	<div class="ad">
        		<p class="ad-p">ТОП ОБЪЯВЛЕНИЙ</p>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="resources/images/motherboard.png" alt="motherboard"></a>
        			<a href="#" class="ad-a1">Плата 56g</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="resources/images/iphone.png" alt="iphone"></a>
        			<a href="#" class="ad-a1">Iphone 6s</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="resources/images/factory.png" alt="tv"></a>
        			<a href="#" class="ad-p2">ЖК-телевизор BBK LM4027F</a>
        		</div>
        		
        		<div class="clearfix"></div>
        		
        		<div class="drop">
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="resources/images/motherboard.png" alt="motherboard"></a>
						<a href="#" class="ad-a1">Плата 56g</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="resources/images/iphone.png" alt="iphone"></a>
						<a href="#" class="ad-a1">Iphone 6s</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="resources/images/factory.png" alt="tv"></a>
						<a href="#" class="ad-p2">ЖК-телевизор BBK LM4027F</a>
					</div>
        		</div>
        		<img class="caretDown" id="ad-caret" src="resources/images/caret.png" alt="caret">
        	</div>
        	
        	<div class="ad">
        		<p class="tend-p">ТОП ТЕНДЕРОВ</p>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="resources/images/name.png" alt="name"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="resources/images/arm.png" alt="arm"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="resources/images/holography.png" alt="holography"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		
        		<div class="clearfix"></div>
        		
        		<div class="tend-drop">
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="resources/images/name.png" alt="name"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="resources/images/arm.png" alt="arm"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="resources/images/holography.png" alt="holography"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
        		</div>
        		<img class="caretDown" id="tend-caret" src="resources/images/caret.png" alt="caret">
        	</div>
        	
        	<div class="ad">
        		<p class="proj-p">ТОП ПРОЕКТОВ</p>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="resources/images/tractor.png" alt="tractor"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="resources/images/wheat.png" alt="wheat"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="resources/images/factory.png" alt="factory"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		
        		<div class="clearfix"></div>
        		
        		<div class="proj-drop">
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="resources/images/tractor.png" alt="tractor"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="resources/images/wheat.png" alt="wheat"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="resources/images/factory.png" alt="factory"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
        		</div>
        		<img class="caretDown" id="proj-caret" src="resources/images/caret.png" alt="caret">
        	</div>
        </div>
        
        <div class="question">
            <img class="question-img" src="resources/images/question.png" alt="question">
            <div class="questionForm">
                <p class="quest-p">Оставьте свой вопрос</p>
                <form id="form-quest" action="#" role="form">
                    <input type="text" class="form-control inText" name="name" placeholder="Ваше имя">
                    <textarea class="form-control" rows="7" id="comment-quest"></textarea>
                    <button type="submit" class="btn-quest">Отправить</button>
                </form>
                
                <div class="message">
                    <img src="resources/images/operator.png" alt="operator">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Оператор: Григорий</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
                <div class="message">
                    <img src="resources/images/user.png" alt="user">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Лидия Ивановна</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
                <div class="message">
                    <img src="resources/images/operator.png" alt="operator">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Оператор: Григорий</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
                <div class="message">
                    <img src="resources/images/user.png" alt="user">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Лидия Ивановна</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
                <div class="message">
                    <img src="resources/images/operator.png" alt="operator">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Оператор: Григорий</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
            </div>
        </div>
        
        <div class="footer"></div>
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
        <script>window.jQuery || document.write('<script src="resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>

        <script src="resources/js/vendor/bootstrap.js"></script>
        
        <script src="resources/js/jquery.bxslider.js"></script>
        <script>
			$(document).ready(function(){
				$('.bxslider').bxSlider();
			});
		</script>

        <script src="resources/js/main.js"></script>
        
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
        <script>
            $('#tab-container').easytabs();
        </script>
    </body>
</html>
