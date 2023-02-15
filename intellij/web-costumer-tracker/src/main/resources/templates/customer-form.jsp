<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <title>Save Customer</title>

    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <link type="text/css" rel="stylesheet" href="/css/add-customer-style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <h3>Save Customer</h3>

    <!-- Adding a Spring form tag here.
         Type declaration lets Spring know, that we have a Customer instance here.
         All fields are bounded automatically to Setter/Getter in Customer class.
    -->
    <%--@elvariable id="customer" type="de.dkh.webcostumertracker.entity.Customer"--%>
    <form:form action="saveCustomer" modelAttribute="customer" method="POST">

        <!-- Problem: we use this form for both: saving a new and updating an existing customer.
        We need to keep track on the incoming customer id to recognize the customer as existing!-->
        <form:hidden path="id"/>

        <table>
            <tbody>

            <tr>
                <td><label>First name:</label></td>
                <td><form:input path="firstName"/></td>
            </tr>

            <tr>
                <td><label>Last name:</label></td>
                <td><form:input path="lastName"/></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><form:input path="email"/></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
            </tbody>
        </table>

    </form:form>

    <div style="clear: both;">
        <!-- moving back to the customer list -->
        <p>
            <a href="/customer/list">Back to list</a>
        </p>

    </div>
</div>

</body>
</html>