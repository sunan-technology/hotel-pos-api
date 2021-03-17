package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PerchaseJoin.class)
public abstract class PerchaseJoin_ {

	public static volatile SingularAttribute<PerchaseJoin, Product> product;
	public static volatile SingularAttribute<PerchaseJoin, Integer> quantity;
	public static volatile SingularAttribute<PerchaseJoin, Purchase> purchase;
	public static volatile SingularAttribute<PerchaseJoin, Warehouses> warehouses;
	public static volatile SingularAttribute<PerchaseJoin, String> isActive;
	public static volatile SingularAttribute<PerchaseJoin, Date> expiryDate;
	public static volatile SingularAttribute<PerchaseJoin, Double> totalAmount;
	public static volatile SingularAttribute<PerchaseJoin, Integer> hasExpiryDate;
	public static volatile SingularAttribute<PerchaseJoin, Timestamp> createdAt;
	public static volatile SingularAttribute<PerchaseJoin, Double> price;
	public static volatile SingularAttribute<PerchaseJoin, StorageType> storageType;
	public static volatile SingularAttribute<PerchaseJoin, Integer> id;
	public static volatile SingularAttribute<PerchaseJoin, Date> updatedAt;

	public static final String PRODUCT = "product";
	public static final String QUANTITY = "quantity";
	public static final String PURCHASE = "purchase";
	public static final String WAREHOUSES = "warehouses";
	public static final String IS_ACTIVE = "isActive";
	public static final String EXPIRY_DATE = "expiryDate";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String HAS_EXPIRY_DATE = "hasExpiryDate";
	public static final String CREATED_AT = "createdAt";
	public static final String PRICE = "price";
	public static final String STORAGE_TYPE = "storageType";
	public static final String ID = "id";
	public static final String UPDATED_AT = "updatedAt";

}

