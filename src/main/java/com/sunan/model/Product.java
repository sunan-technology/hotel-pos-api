package com.sunan.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(name = "product_code")
	private String productCode;

	@Column(name = "product_name")
	private String productName;

	@JoinColumn(name = "category_id")
	@ManyToOne
	private Category category;

	public Product(int id) {
		super();
		this.id = id;
	}

	@Column(name = "description")
	private String description;

	@Column(name = "unit")
	private String unit;

	@Column(name = "price")
	private Double price;

	@Column(name = "reorder_point")
	private Double reorderPoint;

	@Column(name = "is_active")
	private String isActive;

	@Builder
	public Product(Hotel hotelId, Timestamp createdAt, Date updatedAt, int id, String productCode, String productName,
			Category category, String description, String unit, Double price, Double reorderPoint, String isActive) {
		super(hotelId, createdAt, updatedAt);
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.category = category;
		this.description = description;
		this.unit = unit;
		this.price = price;
		this.reorderPoint = reorderPoint;
		this.isActive = isActive;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}
		
}
