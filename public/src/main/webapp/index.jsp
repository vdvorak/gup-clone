<!DOCTYPE html>
<html lang="en" ng-app="gup">
<head>
	<meta charset="UTF-8">
	<title>GUP - 1</title>
	<link rel="stylesheet" href="lib/normalize.css">
	<base href="/" />
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
	<script src="lib/modernizr-2.8.3-respond-1.4.2.min.js"></script>
</head>
<body ng-controller="mainCtrl as main" ng-init="main.init()" ng-click="main.click()">

<div class="cover"></div>
<!-- HEADER -->

<div class="general-content" style="display:none">
	<header>
		<div class="container clearfix">
			<div class="headLeft clearfix">
				<div class="logo" ng-click="redirectToUrl('/')"></div>
				<text ng-model="main.hello" label="Поиск" color="white"></text>
				<div class="selectBox">
					<div class="defaultValue" ng-click="main.showCategories()">
						<p>{{ main.currentCategory }} <span>{{ main.arrow }}</span></p>
					</div>
					<div class="listOfValues" ng-show="main.showingCategories">
						<div class="listItem" ng-repeat="cat in main.sortingCategories" ng-click="main.setCategory(cat.id)">{{ cat.title }}</div>
					</div>
				</div>
				<div class="add" ng-class="temp"  ng-mousedown="temp = 'clicked'" ng-mouseup="temp = ''" 	ng-click="redirectToUrl('/bulletinAdd')">
					<p>Добавить Объявление</p>
				</div>
			</div>
			<div class="headRight clearfix">
				<div class="mail" ng-show="db.user">
					<p>10</p>
				</div>
				<div class="bell" ng-show="db.user">
					<p>8</p>
				</div>
				<div class="services" style="visibility:hidden"></div>
				<div class="userName" ng-show="db.user">
					<p ng-click="username = true">Имя Фамилия</p>
					<div ng-show="username">
						<p ng-click="redirectToUrl('/profile')">Профиль</p>
						<p ng-click="main.logout()">Выйти</p>
					</div>
				</div>
				<div class="auth" ng-hide="db.user">
					<span ng-click="redirectToUrl('/login')">Войти</span> | <span ng-click="redirectToUrl('/register')">Регистрация</span>
				</div>
			</div>
		</div>
	</header>

	<div class="navBtn" ng-click="main.toggleFilters()" ></div>

	<nav ng-show="main.showFilters">
		<div class="map">
		<div class="clearfix"></div>
			<text class="required" ng-model="main.hello" label="Киевская обл." color="blue" validate="login.emailIsValid" isValid="login.emailValid"></text>
		</div>
		<ul>
			<li class='item'><p>Для детей</p></li>
			<li class='item'><p>Для животных</p></li>
			<li class='item'><p>Бизнес</p></li>
			<li class='item'><p>Недвижимость</p></li>
			<li class='item'><p>Транспорт</p></li>
			<li class='item'><p>Одежда и косметика</p></li>
			<li class='item'><p>Волонтерство</p></li>
			<li class='item'><p>Бесплатно</p></li>
			<li class='item'><p>Дом и сад</p></li>
			<li class='item'><p>Хобби и спорт</p></li>
			<li class='item'><p>Бартер</p></li>
			<li class='item'><p>Технологии</p></li>
		</ul>
	</nav>

	<!-- CONTAINER -->
	<div class="container">
	    <!-- CONTENT NG-ROUTE -->
	    <div class="content clearfix" ng-view></div>

	</div>

	<!-- FOOTER -->
	<footer></footer>
</div>
<!-- LIBLARIES -->
<script type="text/javascript" src="lib/angular.min.js"></script>
<script type="text/javascript" src="lib/angular-route.js"></script>
<script type="text/javascript" src="lib/angular-cookies.js"></script>

<!-- JS BUILD -->
<script type="text/javascript" src="app.js"></script>

</body>
</html>
