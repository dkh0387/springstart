<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Custom login page</title>
</head>
<body>
<h3>My custom login page</h3>

<!-- if the submit button clicked the user processing url is routing.
 This is defined in DemoSecurityConfig-->
<form:form action="/authenticateTheUser" method="POST">

    <!-- NOTE: field names have exactly to match the according attributes
    in org.springframework.security.core.userdetails.User -->

    <p>
        User name: <input type="text" name="username"/>
    </p>

    <p>
        Password: <input type="password" name="password"/>
    </p>

    <input type="submit" value="Login"/>

</form:form>

</body>
</html>