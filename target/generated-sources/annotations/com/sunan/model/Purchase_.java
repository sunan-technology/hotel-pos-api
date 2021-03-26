package com.sunan.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Purchase.class)
public abstract class Purchase_ {

	public static volatile SingularAttribute<Purchase, Date> date;
	public static volatile SingularAttribute<Purchase, Double> grandTotal;
	public static volatile SingularAttribute<Purchase, Double> otherCharges;
	public static volatile SingularAttribute<Purchase, Double> discount;
	public static volatile SingularAttribute<Purchase, Double> subTotal;
	public static volatile SingularAttribute<Purchase, Double> paymentDue;
	public static volatile SingularAttribute<Purchase, String> isActive;
	public static volatile SingularAttribute<Purchase, String> purchaseType;
	public static volatile SingularAttribute<Purchase, Double> total;
	public static volatile SingularAttribute<Purchase, Double> totalPayment;
	public static volatile SingularAttribute<Purchase, Double> previousDue;
	public static volatile SingularAttribute<Purchase, Supplier> supplier;
	public static volatile SingularAttribute<Purchase, Double> discountPer;
	public static volatile SingularAttribute<Purchase, Integer> id;
	public static volatile SingularAttribute<Purchase, String> invoiceNo;
	public static volatile SingularAttribute<Purchase, Double> roundOff;
	public static volatile SingularAttribute<Purchase, Double> freightCharges;
	public static volatile SingularAttribute<Purchase, String> remarks;

	public static final String DATE = "date";
	public static final String GRAND_TOTAL = "grandTotal";
	public static final String OTHER_CHARGES = "otherCharges";
	public static final String DISCOUNT = "discount";
	public static final String SUB_TOTAL = "subTotal";
	public static final String PAYMENT_DUE = "paymentDue";
	public static final String IS_ACTIVE = "isActive";
	public static final String PURCHASE_TYPE = "purchaseType";
	public static final String TOTAL = "total";
	public static final String TOTAL_PAYMENT = "totalPayment";
	public static final String PREVIOUS_DUE = "previousDue";
	public static final String SUPPLIER = "supplier";
	public static final String DISCOUNT_PER = "discountPer";
	public static final String ID = "id";
	public static final String INVOICE_NO = "invoiceNo";
	public static final String ROUND_OFF = "roundOff";
	public static final String FREIGHT_CHARGES = "freightCharges";
	public static final String REMARKS = "remarks";

}

