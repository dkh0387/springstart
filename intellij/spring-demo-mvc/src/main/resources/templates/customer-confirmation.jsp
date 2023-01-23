<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="customer" scope="request" type="de.dkh.springdemo.mvc.validation.Customer"/>
<!DOCTYPE html>
<html>
<body>

<h2>Student confirmed!</h2>

<br><br>

<!-- We can access the student attributes
    from the model being applied as parameter in the corresponding controller method: CustomerController#processCustomerForm()
    NOTE: here we have an axample of for each loop using JSP.-->
Customer name: ${customer.firstName} ${customer.lastName}

</body>
</html>