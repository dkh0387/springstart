package de.dkh.springdemo.mvc.formtags;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @RequestMapping("/showForm")
    public String showStudentForm(Model model) {
        model.addAttribute("student", new Student());
        /*
        In case we want bind the enum values to the view over the model we can add an attribute.
        Otherwise, we just get them from Student object directly.
         */
        //model.addAttribute("countries", Student.Country.values());
        return "student-form";
    }

    @RequestMapping("/processForm")
    public String processStudentForm(@ModelAttribute("student") Student student) {
        System.out.println("Student confirmed: " + student.toString());
        return "student-confirmation";
    }
}
