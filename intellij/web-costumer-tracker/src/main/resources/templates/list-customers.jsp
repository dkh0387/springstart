<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<link type="text/css" rel="stylesheet" href="/css/style.css">

<h2>List of Customers</h2>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Management</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <!-- add button: Add Customer
             onClick code means: we call the form for adding a new Customer.
             We do that by calling a according Spring controller mapping for `showFormForAdd`.
             This is a very important concept how to call views in views using Spring MVC!
         -->

        <input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button">

        <!-- add html table here -->
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
            <!-- loop over and show all customers
                 (getting as an attribute from the Spring MVC model in {@code CustomerController}) -->
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.email}</td>
                </tr>
            </c:forEach>

        </table>

    </div>
</div>

</body>
</html>