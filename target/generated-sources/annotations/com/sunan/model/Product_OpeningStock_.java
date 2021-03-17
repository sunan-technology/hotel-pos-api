package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductOpeningStock.class)
public abstract class Product_OpeningStock_ {

	public static volatile SingularAttribute<ProductOpeningStock, Date> expiryDate;
	public static volatile SingularAttribute<ProductOpeningStock, Integer> hasExpiryDate;
	public static volatile SingularAttribute<ProductOpeningStock, Timestamp> createdAt;
	public static volatile SingularAttribute<ProductOpeningStock, Product> product;
	public static volatile SingularAttribute<ProductOpeningStock, Integer> quantity;
	public static volatile SingularAttribute<ProductOpeningStock, Warehouses> warehouses;
	public static volatile SingularAttribute<ProductOpeningStock, StorageType> storageType;
	public static volatile SingularAttribute<ProductOpeningStock, Integer> id;
	public static volatile SingularAttribute<ProductOpeningStock, String> isActive;
	public static volatile SingularAttribute<ProductOpeningStock, Date> updatedAt;

	public static final String EXPIRY_DATE = "expiryDate";
	public static final String HAS_EXPIRY_DATE = "hasExpiryDate";
	public static final String CREATED_AT = "createdAt";
	public static final String PRODUCT = "product";
	public static final String QUANTITY = "quantity";
	public static final String WAREHOUSES = "warehouses";
	public static final String STORAGE_TYPE = "storageType";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String UPDATED_AT = "updatedAt";

}

