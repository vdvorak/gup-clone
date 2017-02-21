<!--| http://www.htmlgoodies.com/beyond/javascript/article.php/3470821 |-->
<!--| https://www.tutorialspoint.com/javascript/javascript_cookies.htm |-->

<html>
<head>

    <script type = "text/javascript">
        <!--
        // document.cookie = "key1=value1;key2=value2;expires=date";
        // document.cookie = "name=zzzzzzzzzzzz;";

        function WriteCookie() {
            if( document.myform.customer.value == "" ){
                alert("Enter some value!");
                return;
            }

            cookievalue = escape(document.myform.customer.value) + ";";
            document.cookie="name=" + cookievalue + ";";
            document.write("Setting Cookies: " + "name=" + cookievalue );
        }
        //-->
    </script>

</head>

<body>

    <form name="myform" action="#">
        Enter name: <input type="text" name="customer"/>
        <input type="button" value="Set Cookie" onclick="WriteCookie();"/>
    </form>

    <form action="#" method="POST">
        <input type="hidden" name="csrf-token" value="nc98P987bcpncYhoadjoiydc9ajDlcn">
        <input type="text" name="tweet">
        <input type="submit">
    </form>
</body>
</html>