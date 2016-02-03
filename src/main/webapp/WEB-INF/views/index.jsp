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

        <link rel="stylesheet" href="/resources/css/bootstrap.css">
        <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
        <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
        <link rel="stylesheet" href="/resources/css/main.css">
        <link rel="stylesheet" href="/resources/css/font-awesome.css">
        <link rel="stylesheet" href="/resources/css/media-queries.css">

		<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	</head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <jsp:include page="/WEB-INF/templates/common-header.jsp"/>

        <jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

        <jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

        <jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

        
        <div class="container">
        	<div class="tidings">
        	    <div class="tidi1">
        			<a href="#"><img src="/resources/images/tidi3.png" alt="tidi"></a>
        			<a href="#" class="tidi3-a">В колокольне Лавры открыли концертный зал</a>
        		</div>
        		<div class="tidi2">
        			<a href="#"><img src="/resources/images/tidi1.png" alt="tidi"></a>
        			<a href="#" class="tidi1-a">История книгаря</a>
        		</div>
        		<div class="tidi3">
        			<a href="#"><img src="/resources/images/tidi2.png" alt="tidi"></a>
        			<a href="#" class="tidi1-a">Легенда Хью Гласса</a>
        		</div>
        		<div class="tidi4">
        			<p class="data">Опубликовано: 22.10.16</p>
        			<a href="#"><img src="/resources/images/tidi4.png" alt="tidi"></a>
        			<a class="tidi4-a" href="#">MONARCHY</a>
        			<p class="tidi4-p">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
        		</div>
        		<div class="tidi4">
        			<p class="data-tidi5">Опубликовано: 22.10.16</p>
        			<a class="tidi4-ar" href="#">MONARCHY</a>
        			<a class="tidi5-img" href="#"><img src="/resources/images/tidi5.png" alt="tidi"></a>
        			<p class="tidi4-pr">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
        		</div>
        		<div class="tidi4">
        			<p class="data">Опубликовано: 22.10.16</p>
        			<a href="#"><img src="/resources/images/tidi6.png" alt="tidi"></a>
        			<a class="tidi4-a" href="#">MONARCHY</a>
        			<p class="tidi4-p">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
        		</div>
        		<div class="tidi4">
        			<p class="data-tidi5">Опубликовано: 22.10.16</p>
        			<a class="tidi4-ar" href="#">MONARCHY</a>
        			<a class="tidi5-img" href="#"><img src="/resources/images/tidi7.png" alt="tidi"></a>
        			<p class="tidi4-pr">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
        		</div>
        		
        		<div class="clearfix"></div>
        		
        		<div class="tidi-drop">
					<div class="tidi4">
						<p class="data">Опубликовано: 22.10.16</p>
						<a href="#"><img src="/resources/images/tidi4.png" alt="tidi"></a>
						<a class="tidi4-a" href="#">MONARCHY</a>
						<p class="tidi4-p">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
					</div>
        		</div>
        		<img class="tidiDown" src="/resources/images/caret.png" alt="caret">
        	</div>
        	
        	<div class="ad">
        		<p class="ad-p">ТОП ОБЪЯВЛЕНИЙ</p>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="/resources/images/motherboard.png" alt="motherboard"></a>
        			<a href="#" class="ad-a1">Плата 56g</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="/resources/images/iphone.png" alt="iphone"></a>
        			<a href="#" class="ad-a1">Iphone 6s</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="/resources/images/factory.png" alt="tv"></a>
        			<a href="#" class="ad-p2">ЖК-телевизор BBK LM4027F</a>
        		</div>
        		
        		<div class="clearfix"></div>
        		
        		<div class="drop">
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="/resources/images/motherboard.png" alt="motherboard"></a>
						<a href="#" class="ad-a1">Плата 56g</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="/resources/images/iphone.png" alt="iphone"></a>
						<a href="#" class="ad-a1">Iphone 6s</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="/resources/images/factory.png" alt="tv"></a>
						<a href="#" class="ad-p2">ЖК-телевизор BBK LM4027F</a>
					</div>
        		</div>
        		<img class="caretDown" id="ad-caret" src="/resources/images/caret.png" alt="caret">
        	</div>
        	
        	<div class="ad">
        		<p class="tend-p">ТОП ТЕНДЕРОВ</p>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="/resources/images/name.png" alt="name"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="/resources/images/arm.png" alt="arm"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="/resources/images/holography.png" alt="holography"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		
        		<div class="clearfix"></div>
        		
        		<div class="tend-drop">
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="/resources/images/name.png" alt="name"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="/resources/images/arm.png" alt="arm"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="/resources/images/holography.png" alt="holography"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
        		</div>
        		<img class="caretDown" id="tend-caret" src="/resources/images/caret.png" alt="caret">
        	</div>
        	
        	<div class="ad">
        		<p class="proj-p">ТОП ПРОЕКТОВ</p>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="/resources/images/tractor.png" alt="tractor"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="/resources/images/wheat.png" alt="wheat"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		<div class="add-top1">
        			<a href="#"><img class="img-responsive" src="/resources/images/factory.png" alt="factory"></a>
        			<a href="#" class="ad-a1">Название</a>
        		</div>
        		
        		<div class="clearfix"></div>
        		
        		<div class="proj-drop">
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="/resources/images/tractor.png" alt="tractor"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="/resources/images/wheat.png" alt="wheat"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
					<div class="add-top1">
						<a href="#"><img class="img-responsive" src="/resources/images/factory.png" alt="factory"></a>
						<a href="#" class="ad-a1">Название</a>
					</div>
        		</div>
        		<img class="caretDown" id="proj-caret" src="/resources/images/caret.png" alt="caret">
        	</div>
        </div>
        
        <div class="question">
            <img class="question-img" src="/resources/images/question.png" alt="question">
            <div class="questionForm">
                <p class="quest-p">Оставьте свой вопрос</p>
                <form id="form-quest" action="#" role="form">
                    <input type="text" class="form-control inText" name="name" placeholder="Ваше имя">
                    <textarea class="form-control" rows="7" id="comment-quest"></textarea>
                    <button type="submit" class="btn-quest">Отправить</button>
                </form>
                
                <div class="message">
                    <img src="/resources/images/operator.png" alt="operator">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Оператор: Григорий</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
                <div class="message">
                    <img src="/resources/images/user.png" alt="user">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Лидия Ивановна</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
                <div class="message">
                    <img src="/resources/images/operator.png" alt="operator">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Оператор: Григорий</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
                <div class="message">
                    <img src="/resources/images/user.png" alt="user">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Лидия Ивановна</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
                <div class="message">
                    <img src="/resources/images/operator.png" alt="operator">
                    <p class="data-mess">26. 10. 15</p>
                    <a href="#">Оператор: Григорий</a>
                    <p class="mess-p">Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>
                </div>
            </div>
        </div>
        
        <div class="footer"></div>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
        <script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
        <script src="/resources/js/vendor/bootstrap.js"></script>
        <script src="/resources/js/jquery.bxslider.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>

		<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

		<script src="/resources/js/main.js"></script>
		<sec:authorize access="isAuthenticated()">
			<script src="/resources/js/autorizedHeader.js"></script>
		</sec:authorize>
		<script src="/resources/js/logo-section.js"></script>
		<script src="/resources/js/search-bar.js"></script>

		<script>
			$(document).ready(function(){
				$('.bxslider').bxSlider();
				$('#tab-container').easytabs();

			});
		</script>
    </body>
</html>
