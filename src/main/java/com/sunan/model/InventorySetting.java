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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "inventory_setting")
public class InventorySetting implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	public int id;
	
	@Column(name = "autoconsumption")
	private String autoConsumption;
	
	@Column(name = "alertkitchen_whenitemreaches_atperstocklevel")
	private String alertKitchenWhenItemReachesAtPerStockLevel;
	
	@Column(name = "shipbilltoconfiguration")
	private String shipBillToConfiguration;
	
	@Column(name = "revertconsumption_duetocancelof_onlineorder")
	private String revertConsumptionDueToCancelOfOnlineOrder;
	
	@Column(name = "enable_cesstaxin_invoices")
	private String enableCessTaxInInvoices;
	
	@Column(name = "allowuserto_raiseinternalsalestock_atthekitchennegativelevel")
	private String allowUserToRaiseInternalSaleStockAtTheKitchenNegativeLevel;
	
	@Column(name = "putitemasoutofstockwhen_itemquantitygetbelow_minimumstocklevel")
	private String putItemAsOutOfStockWhenItemQuantityGetBelowMinimumStockLevel;
	
	@Column(name = "setinvoiceamount_inroundoff")
	private String setInvoiceAmountInRoundOff;
	
	@Column(name = "displaypurchaseprice_asinternalsale")
	private String displayPurchasePriceAsInternalSale;
	
	@Column(name = "getnotification_whenitemreaches_atperstocklevel")
	private String getNotificationWhenItemReachesAtPerStockLevel;
	
	@Column(name = "allowuser_raiserequestfor_purchasewhenstockatkitchen_isnegative")
	private String allowUserRaiseRequestForPurchaseWhenStockAtKitchenIsNegative;
	
	@Column(name = "saleinvoicelabel")
	private String salesInvoiceLabel;
	
	@Column(name = "purchaseinvoicelabel")
	private String purchaseInvoiceLabel;
	

	@JoinColumn(name = "hotel_id")
	@ManyToOne
	public Hotel hotel;

	@JsonIgnore
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Timestamp createdAt;

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at")
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date updatedAt;
	
}
