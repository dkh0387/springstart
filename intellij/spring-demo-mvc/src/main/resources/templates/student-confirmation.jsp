<jsp:useBean id="student" scope="request" type="de.dkh.springdemo.mvc.Student"/>
<!DOCTYPE html>
<html>
<body>

<h2>Student confirmed!</h2>

<br><br>

<!-- We can access the student attributes
    from the model being applied as parameter in the corresponding controller method: StudentController#processStudentForm()-->
Student infos from previous form: ${student.firstName} ${student.lastName} ${student.country}

</body>
</html>