package de.dkh.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The Annotation {@code @Controller} marks the class for the Spring
 * autocomponentsearch.
 * 
 * @author dkh
 *
 */
@Controller
public class HomeController {

	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Processes an HTTP GET request for the root path. The return value is
	 * interpreted as a logical name of a view (HTML start page, here
	 * {@code home.html}.
	 * 
	 * @return
	 */
	@GetMapping("/home")
	public String home() {
		return "home";
	}

}
