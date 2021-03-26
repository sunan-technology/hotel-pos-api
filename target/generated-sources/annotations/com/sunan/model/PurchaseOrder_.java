package com.sunan.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PurchaseOrder.class)
public abstract class PurchaseOrder_ {

	public static volatile SingularAttribute<PurchaseOrder, Date> date;
	public static volatile SingularAttribute<PurchaseOrder, Double> grandTotal;
	public static volatile SingularAttribute<PurchaseOrder, Double> subTotal;
	public static volatile SingularAttribute<PurchaseOrder, String> isActive;
	public static volatile SingularAttribute<PurchaseOrder, Double> vatAmount;
	public static volatile SingularAttribute<PurchaseOrder, String> terms;
	public static volatile SingularAttribute<PurchaseOrder, Supplier> supplier;
	public static volatile SingularAttribute<PurchaseOrder, Double> addVatAmount;
	public static volatile SingularAttribute<PurchaseOrder, Integer> id;
	public static volatile SingularAttribute<PurchaseOrder, Double> vatPer;
	public static volatile SingularAttribute<PurchaseOrder, Integer> poNumber;
	public static volatile SingularAttribute<PurchaseOrder, Double> addVat;
	public static volatile SingularAttribute<PurchaseOrder, String> taxType;

	public static final String DATE = "date";
	public static final String GRAND_TOTAL = "grandTotal";
	public static final String SUB_TOTAL = "subTotal";
	public static final String IS_ACTIVE = "isActive";
	public static final String VAT_AMOUNT = "vatAmount";
	public static final String TERMS = "terms";
	public static final String SUPPLIER = "supplier";
	public static final String ADD_VAT_AMOUNT = "addVatAmount";
	public static final String ID = "id";
	public static final String VAT_PER = "vatPer";
	public static final String PO_NUMBER = "poNumber";
	public static final String ADD_VAT = "addVat";
	public static final String TAX_TYPE = "taxType";

}

