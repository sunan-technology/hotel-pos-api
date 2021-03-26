package com.sunan.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PurchaseOrderJoin.class)
public abstract class PurchaseOrderJoin_ {

	public static volatile SingularAttribute<PurchaseOrderJoin, Product> product;
	public static volatile SingularAttribute<PurchaseOrderJoin, Double> amount;
	public static volatile SingularAttribute<PurchaseOrderJoin, Integer> quantity;
	public static volatile SingularAttribute<PurchaseOrderJoin, PurchaseOrder> purchaseOrder;
	public static volatile SingularAttribute<PurchaseOrderJoin, Integer> id;
	public static volatile SingularAttribute<PurchaseOrderJoin, String> isActive;
	public static volatile SingularAttribute<PurchaseOrderJoin, Double> pricePerUnit;

	public static final String PRODUCT = "product";
	public static final String AMOUNT = "amount";
	public static final String QUANTITY = "quantity";
	public static final String PURCHASE_ORDER = "purchaseOrder";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String PRICE_PER_UNIT = "pricePerUnit";

}

