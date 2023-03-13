<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Custom login page</title>

    <link rel="stylesheet" type="text/css" href="/css/demo.css">
</head>
<body>
<h3>My custom login page</h3>

<!-- if the submit button clicked the user processing url is routing.
 This is defined in DemoSecurityConfig-->
<form:form action="/authenticateTheUser" method="POST">

    <!-- We use JSTL to check if the input is correct and provide the error msg.
    In case of invalif creds the param "...?error" will be added at the end of the url-->

    <c:if test="${param.error != null}">

        <i class="failed">Invalid username/password</i>

    </c:if>

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