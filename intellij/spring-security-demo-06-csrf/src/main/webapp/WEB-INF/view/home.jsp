<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
<hr>
<!-- Display username and role.
 NOTE: in order to mke it work we need the spring-security-taglibs dependency-->
<p>
    Username: <security:authentication property="principal.username"/>
    <br><br>
    Role(s): <security:authentication property="principal.authorities"/>
</p>
<hr>

<!-- Add a logout button.
NOTE: In Spring Security, the default logout URL is "/logout",
 so we do NOT need to redirect manually via controller to the login page, it is just enough to activate Spring logout in DemoSecurityConfig!-->
<form:form action="/logout" method="POST">
    <input type="submit" value="Logout">
</form:form>

</body>
</html>