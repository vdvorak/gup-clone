<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="language" content="English" />
		
		<title>Welcome</title>

		
		<!--[if lt IE 9]>
			<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
	</head>

	<body>
	
		<div id="container">
			
			<!--  header -->
			<tiles:insertAttribute name="header"/>
			
			<!-- main central container -->
			<tiles:insertAttribute name="body"/>
	    		
			<!-- footer -->
			<tiles:insertAttribute name="footer"/>
			
		</div>
	
	</body>
</html>