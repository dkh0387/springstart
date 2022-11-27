package de.dkh.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.dkh.core.Ingredient.Type;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Hint: FOR GET-REQUEST ONLY! (means that data are being retrieved) <br/>
 * This class process the GET-requests from webbrowser, when using
 * {@code .../design} URL. It does:
 * <ol>
 * <li>deliver the {@linkplain Ingredient} list</li>
 * <li>transfer the data to the view for rendering as HTML in webbrowser</li>
 * </ol>
 * 
 * @author dkh
 *
 */
@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	/**
	 * This method is calling on GET-request "/design". {@linkplain Model} is
	 * decorated with Attributes {@linkplain Type#name()}, {@linkplain Type} and a
	 * new {@linkplain Taco} itself by calling "/design". Example for databinding
	 * between {@code this} and {@code /design.html} over {@linkplain Model}: <br>
	 * {@code <!-- FOR loop like for (ingredient : ingredients) {} -->
	 * <div th:each="ingredient : ${wrap}"> <!-- model attributeName is
	 * type.toString().toLowerCase() (see below)-->
	 * <input name="ingredients" type="checkbox" th:value="${ingredient.id}" />
	 * <span th:text="${ingredient.name}">INGREDIENT</span><br />
	 * </div>}
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = List.of(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE), new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

		Stream.of(Ingredient.Type.values())
				.forEach(type -> model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type)));
		model.addAttribute("design", new Taco());

		return "design";
	}

	/**
	 * If the {@code form} in {@code design.html} is being transfered the browser
	 * sends all the filled data via HTTP-POST request to the server URL .../design.
	 * That means the Controller {@linkplain this} obtains the data back<br/>
	 * Obtained {@linkplaint Taco} object will be persisted in a DB later on...<br/>
	 * 
	 * @param designedTaco
	 * @return
	 */
	@PostMapping
	@Validated
	public String processDesign(@Valid Taco taco, Errors errors) {

		if (errors.hasErrors()) {
			return "design";
		}
		// TODO: not implemented yet...
		log.info("Processing design: " + taco);
		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(i -> type.equals(i.getType())).collect(Collectors.toList());
	}

}
