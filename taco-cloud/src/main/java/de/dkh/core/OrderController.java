package de.dkh.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller to take customers to the order processing after choosing over
 * {@linkplain DesignTacoController} (customers are redirecting to this page on
 * submit). This controller manages all requests beginning with
 * {@code "/orders"}.
 * 
 * @author dkh
 *
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepository;

	@Autowired
	public OrderController(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	/**
	 * By redirecting to {@code /orders/cuurent} with
	 * {@linkplain DesignTacoController} we create a new {@linkplain Order} model
	 * and the user comes to {@code orderForm.html}. Very import here: note the
	 * {@code static} reference to the {@linkplain DesignTacoController#order()}.
	 * The initialization of {@linkplain this} model should be with the same
	 * {@linkplain Order} created in {@linkplain DesignTacoController}!
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", DesignTacoController.order());
		return "orderForm";
	}

	/**
	 * The {@code form} in {@code orderForm.html} defines an action
	 * {@code th:action="@{/orders}" th:object="${order}}, so we need a method for
	 * POST-requests coming from this form. Since the action shows on
	 * {@linkplain this} controller {@code @RequestMapping("/orders")} the request
	 * will be processed through this method. IMPORTANT: note the {@code @} sign
	 * before {@code "/orders"}: this links the action to the controller here!
	 * 
	 * {@linkplain SessionStatus} attribute is needed to remove the
	 * {@linkplain Order} object from the actual session, otherwise the next order
	 * will be the same. Also see it connected with
	 * {@linkplain DesignTacoController} {@code @SessionAttributes("order")}.
	 * 
	 * @param order
	 * @return
	 */
	@PostMapping
	@Validated
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {

		if (errors.hasErrors()) {
			return "orderForm";
		}
		log.info("Order submitted: " + order.toString());
		orderRepository.save(order);
		sessionStatus.setComplete();
		return "redirect:/home";
	}

}
