<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>New user</title>

    <link rel="stylesheet" type="text/css" href="/css/add-customer-style.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>Register a new user</h2>
    </div>
</div>

<div id="container">
    <h3>Save User</h3>

    <!-- Adding a Spring form tag here.
         Type declaration lets Spring know, that we have a User instance here.
         All fields are bounded automatically to Setter/Getter in User class.
    -->
    <%--@elvariable id="customer" type="de.dkh.springsecuritydemodbbcryptlandingpage.entity.User"--%>
    <form:form action="saveUser" modelAttribute="user" method="POST">

        <table>
            <tbody>

            <tr>
                <td><label>User name:</label></td>
                <td><form:input path="username"/></td>
            </tr>

            <tr>
                <td><label>Password:</label></td>
                <td><form:input path="password"/></td>
            </tr>

            <tr>
                <td><label>Roles:</label></td>
                <td><form:checkboxes path="roles" items="${user.userRoles}" itemLabel="label"
                                     itemValue="label"/></td>
            </tr>

            <tr>
                <td><label>Enabled:</label></td>
                <td><form:input path="enabled"/></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
            </tbody>
        </table>

    </form:form>

    <div style="clear: both;">
        <!-- moving back to the landing page -->
        <p>
            <a href="/">Back to home</a>
        </p>

    </div>
</div>

</body>
</html>