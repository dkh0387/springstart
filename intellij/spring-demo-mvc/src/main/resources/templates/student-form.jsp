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
<%--@elvariable id="student" type="de.dkh.springdemo.mvc.Student"--%>
<form:form action="processForm" modelAttribute="student">
    First name: <form:input path="firstName"/>

    <br><br>

    Last name: <form:input path="lastName"/>

    <br><br>

    Country:
    <!-- Binding the values of the inner Enum Country within Student class:
    it is already enough to add the enum values as an attribute the Model
    (over the path name setter on the according Student attr will be calling)!
    Alternatively we can just get the enum values directly from Student object.
    NOTE: itemLabel: what is in the combo box; itemValue: what we get as selected -->
    <form:select path="country">
        <form:options items="${student.countries}" itemValue="label" itemLabel="label"/>
    </form:select>

    <br><br>

    <input type="submit" value="Submit"/>

</form:form>

</body>
</html>