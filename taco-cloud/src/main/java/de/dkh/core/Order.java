//tag::all[]
//tag::allButValidation[]
package de.dkh.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
//requered since the tablename here not equal to classname
@Table(name = "Orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 3845593155841951420L;
	public static final String TABLE_NAME = "Orders";
	public static final String PROP_ID = "id";
	public static final String PROP_CREATED = "placedAt";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date placedAt;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Street is required")
	private String street;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Zip code is required")
	private String zip;

	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCVV;

	@ManyToMany(targetEntity = Taco.class)
	private List<Taco> tacos = new ArrayList<>();

	public void addDesign(Taco design) {
		tacos.add(design);
	}

	@PrePersist
	void placeedAt() {
		placedAt = new Date();
	}

}