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
@Table(name = "recipe_join")
public class RecipeJoin extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@JoinColumn(name = "recipe_id")
	@ManyToOne
	private Recipe recipe;

	@JoinColumn(name = "product_id")
	@ManyToOne
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "costper_unit")
	private Double costPerUnit;

	@Column(name = "total_itemcost")
	private Double totalItemCost;

	@Column(name = "is_active")
	private String isActive;

}
