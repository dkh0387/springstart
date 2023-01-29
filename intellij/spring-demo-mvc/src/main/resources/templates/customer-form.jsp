<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>
<head>
    <title>Customer - Input form!</title>

    <style>
        .error {
            color: red
        }

        .errorPayload {
            color: orange;
        }

        .infoPayload {
            color: yellow;
        }
    </style>
</head>

<!-- We are using Spring form tags here.
    We can easily bind the data in the form to the Spring model being an attribute in the according controller method: StudentController#showStudentForm().
    Then, for example in the directing view we can apply those model attributes (see student-confirmation.jsp).
-->
<%--@elvariable id="customer" type="de.dkh.springdemo.mvc.validation.Customer"--%>
<form:form action="processForm" modelAttribute="customer">
    First name: <form:input path="firstName"/>

    <br><br>

    Last name: <form:input path="lastName"/>
    <form:errors path="lastName" cssClass="error"/>

    <br><br>

    Free passes: <form:input path="freePasses"/>
    <form:errors path="freePasses" cssClass="error"/>

    <br><br>

    Post code: <form:input path="postCode"/>
    <form:errors path="postCode" cssClass="error"/>

    <br><br>

    Course code: <form:input path="courseCode"/>
    <form:errors path="courseCode" cssClass="error"/>

    <br><br>

    Age: <form:input path="age"/>
    <form:errors path="age" cssClass="infoPayload"/>

    <br><br>

    Nationality: <form:input path="nationality"/>
    <form:errors path="nationality" cssClass="errorPayload"/>

    <br><br>

    <input type="submit" value="Submit"/>

</form:form>

</body>
</html>