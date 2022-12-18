package de.dkh.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.dkh.core.Ingredient.Type;
import de.dkh.core.data.IngredientRepository;
import de.dkh.core.data.TacoRepositoiry;
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
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	private TacoRepositoiry tacoRepository;
	private static final Order order = new Order();

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepositoiry tacoRepository) {
		super();
		this.ingredientRepo = ingredientRepo;
		this.tacoRepository = tacoRepository;
	}

	@ModelAttribute(name = "order")
	public static Order order() {
		return order;
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

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
		List<Ingredient> ingredients = new ArrayList<>();

		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Stream.of(Ingredient.Type.values())
				.forEach(type -> model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type)));
		model.addAttribute("design", new Taco());

		return "design";
	}

	/**
	 * If the {@code form} in {@code design.html} is being transfered the browser
	 * sends all the filled data via HTTP-POST request to the server URL .../design.
	 * That means the Controller {@linkplain this} obtains the data back<br/>
	 * Obtained {@linkplaint Taco} object will be persisted in a DB over
	 * {@linkplain JdbcTacoRepository} on...<br/>
	 * Very important concept of Spring MVC here: {@code order} is a model attribute
	 * defined above, the annotation {@code @SessionAttributes("order")} makes sure,
	 * that all designed tacos are binded to this one {@code order} coming from
	 * model. This order remains within the whole session, so the user is able to
	 * add new tacos to it. Only if the user makes the order the object expires.
	 * There is no data binding between the view and the order model!
	 * 
	 * @param designedTaco
	 * @return
	 */
	@PostMapping
	@Validated
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {

		if (errors.hasErrors()) {
			return "design";
		}
		log.info("Processing design: " + taco);
		Taco savedTaco = tacoRepository.save(taco);
		order.addDesign(savedTaco);
		log.info("Actual order: " + order);
		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(i -> type.equals(i.getType())).collect(Collectors.toList());
	}

}
