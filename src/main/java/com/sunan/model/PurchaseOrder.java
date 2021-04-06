package com.sunan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase_order")
public class PurchaseOrder extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(name = "po_number")
	private int poNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@JoinColumn(name = "supplier_id")
	@ManyToOne
	private Supplier supplier;

	@Column(name = "terms")
	private String terms;

	@Column(name = "subtotal")
	private Double subTotal;

	@Column(name = "vatper")
	private Double vatPer;

	@Column(name = "vat_amount")
	private Double vatAmount;

	@Column(name = "add_vat")
	private Double addVat;

	@Column(name = "add_vatamount")
	private Double addVatAmount;

	@Column(name = "grand_total")
	private Double grandTotal;

	@Column(name = "taxtype")
	private String taxType;

	@Column(name = "is_active")
	private String isActive;

	public PurchaseOrder(int id) {
		super();
		this.id = id;
	}

}
