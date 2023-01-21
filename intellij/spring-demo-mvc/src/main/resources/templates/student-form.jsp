<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>
<head>
    <title>Student - Input form!</title>
</head>

<!-- We are using Spring form tags here.
    We can easily bind the data in the form to the Spring model being an attribute in the according controller method: StudentController#showStudentForm().
    Then, for example in the directing view we can apply those model attributes (see student-confirmation.jsp).
-->
<form:form action="processForm" modelAttribute="student">
    First name: <form:input path="firstName"/>

    <br><br>

    Last name: <form:input path="lastName"/>

    <br><br>

    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>