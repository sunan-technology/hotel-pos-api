package com.sunan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(name = "recipe_name")
	private String recipeName;

	@JoinColumn(name = "dish_id")
	@ManyToOne
	private Dish dish;

	@Column(name = "fixed_cost")
	private Double fixedCost;

	@Column(name = "description")
	private String discription;

	@Column(name = "is_active")
	private String isActive;

	public Recipe(int id) {
		super();
		this.id = id;
	}

}
