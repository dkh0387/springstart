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

<p>
    User: <security:authentication property="principal.username"/>
    Role(s): <security:authentication property="principal.authorities"/>
</p>

<hr>

<!-- We can use Spring JSP security tasks
 in order to bound the access according to the roles. The HTML content inside is only visible if the access is allowed-->

<security:authorize access="hasRole('MANAGER')">
    <!-- Add a link to point to /leaders ONLY for Role MANAGER-->
    <p>
        <a href="/leaders">Leadership Meeting</a>
    </p>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
    <!-- Add a link to point to /systems ONLY for Role ADMIN-->
    <p>
        <a href="/systems">Admins Meeting</a>
    </p>
</security:authorize>

<hr>

<!-- Add a logout button.
NOTE: In Spring Security, the default logout URL is "/logout",
 so we do NOT need to redirect manually via controller to the login page, it is just enough to activate Spring logout in DemoSecurityConfig!-->
<form:form action="/logout" method="POST">
    <input type="submit" value="Logout">
</form:form>

</body>
</html>