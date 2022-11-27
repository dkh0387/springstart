package de.dkh.core;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 
 * 
 * I had the same problem now with testing code. That was caused in spring boot
 * because of the @RunWith annotation. I have used:
 * 
 * @RunWith(SpringRunner.class)
 * 
 *                              With that annotation there is JUnit Vintage
 *                              running which can't find any tests and gives you
 *                              the error. I have removed that and only JUnit
 *                              Jupiter is running and everything is fine.
 * 
 * @author dkh
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testHomePage() throws Exception {
		/*
		 * Run GET request against the homepage. Expected: HTTP 200 (OK); homepagename
		 * "home"; homepage title;
		 */
		mockMvc.perform(get("/home")).andExpect(status().isOk()).andExpect(view().name("home"))
				.andExpect(content().string("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\r\n"
						+ "	<head>\r\n"
						+ "	    <meta charset=\"UTF-8\">\r\n"
						+ "	    <title>Taco Cloud</title>\r\n"
						+ "	</head>\r\n"
						+ "	<body>\r\n"
						+ "		<h1>Welcome to...</h1>\r\n"
						+ "		<img src=\"/images/TacoCloud.jpg\" style=\"width: 1900px; height: 600px;\"/>\r\n"
						+ "		\r\n"
						+ "	</body>\r\n"
						+ "</html>"));
	}

}
