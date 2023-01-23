<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="student" scope="request" type="de.dkh.springdemo.mvc.Student"/>
<!DOCTYPE html>
<html>
<body>

<h2>Student confirmed!</h2>

<br><br>

<!-- We can access the student attributes
    from the model being applied as parameter in the corresponding controller method: StudentController#processStudentForm()
    NOTE: here we have an axample of for each loop using JSP.-->
Student name: ${student.firstName} ${student.lastName} ${student.country} ${student.language}

<br><br>

Country: ${student.country} ${student.language}

<br><br>

Preferred programming language: ${student.language}

<br><br>

Selected operating systems:

<ul>

    <c:forEach var="temp" items="${student.selOpSystem}">
        <li>
                ${temp}
        </li>
    </c:forEach>

</ul>

</body>
</html>