<!DOCTYPE html>
<html>
<body>

<h2>Hello world of Spring!</h2>

<br><br>

<!-- We can either access the input directly from the previous form or use the Spring Model from controller to get the according attribute.
 Since this view goes over HelloWorldController#processFormViewWithModel() we just use the model from this method.-->
Student name from previous form: ${param.studentName}

<br><br>

Student name from model: ${name}

</body>
</html>