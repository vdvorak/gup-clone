<%--
  Created by IntelliJ IDEA.
  User: Комп2
  Date: 30.09.2015
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<p>
Weeeeeeeeeeeeeeeeeeee

<p> <h4> send message: </h4> </p>
<p><form method="post" action="/msg">
  <p> <h6> Enter the subject of conversation: </h6> <h5><input type="text" title="subject" placeholder="subject" /></h5>

  <p> <h6>From: </h6> <h5><input type="text" title="from" required placeholder="from" />*</h5>

  <p> <h6>To: </h6> <h5><input type="text" title="to" required placeholder="to" />*</h5>

  <p> <h6>message: </h6> <h5><input type="text" title="message" required placeholder="message" />*</h5>

  <p><input type="submit" title="commit" value="sent message"></p>
</form>


</body>
</html>
