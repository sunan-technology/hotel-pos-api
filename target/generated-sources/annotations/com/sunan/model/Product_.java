package com.sunan.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, String> unit;
	public static volatile SingularAttribute<Product, String> productCode;
	public static volatile SingularAttribute<Product, Double> reorderPoint;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, Integer> id;
	public static volatile SingularAttribute<Product, Category> category;
	public static volatile SingularAttribute<Product, String> isActive;
	public static volatile SingularAttribute<Product, String> productName;

	public static final String UNIT = "unit";
	public static final String PRODUCT_CODE = "productCode";
	public static final String REORDER_POINT = "reorderPoint";
	public static final String PRICE = "price";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String IS_ACTIVE = "isActive";
	public static final String PRODUCT_NAME = "productName";

}

