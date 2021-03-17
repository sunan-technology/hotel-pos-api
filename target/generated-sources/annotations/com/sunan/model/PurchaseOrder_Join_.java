package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PurchaseOrderJoin.class)
public abstract class PurchaseOrder_Join_ {

	public static volatile SingularAttribute<PurchaseOrderJoin, Timestamp> createdAt;
	public static volatile SingularAttribute<PurchaseOrderJoin, Product> product;
	public static volatile SingularAttribute<PurchaseOrderJoin, Double> amount;
	public static volatile SingularAttribute<PurchaseOrderJoin, Integer> quantity;
	public static volatile SingularAttribute<PurchaseOrderJoin, PurchaseOrder> purchaseOrder;
	public static volatile SingularAttribute<PurchaseOrderJoin, Integer> id;
	public static volatile SingularAttribute<PurchaseOrderJoin, String> isActive;
	public static volatile SingularAttribute<PurchaseOrderJoin, Double> pricePerUnit;
	public static volatile SingularAttribute<PurchaseOrderJoin, Date> updatedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String PRODUCT = "product";
	public static final String AMOUNT = "amount";
	public static final String QUANTITY = "quantity";
	public static final String PURCHASE_ORDER = "purchaseOrder";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String PRICE_PER_UNIT = "pricePerUnit";
	public static final String UPDATED_AT = "updatedAt";

}

