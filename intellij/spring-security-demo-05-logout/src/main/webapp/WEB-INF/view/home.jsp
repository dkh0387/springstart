<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Homepage</title>
</head>
<body>

<h2>Company home page</h2>
<hr>

<p>
    Welcome to company home page
</p>

<!-- Add a logout button.
NOTE: In Spring Security, the default logout URL is "/logout",
 so we do NOT need to redirect manually via controller to the login page, it is just enough to activate Spring logout in DemoSecurityConfig!-->
<form:form action="/logout" method="POST">
    <input type="submit" value="Logout">
</form:form>

</body>
</html>