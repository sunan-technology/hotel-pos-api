package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dish.class)
public abstract class Dish_ {

	public static volatile SingularAttribute<Dish, Double> taRate;
	public static volatile SingularAttribute<Dish, Double> uRate;
	public static volatile SingularAttribute<Dish, Double> hdRate;
	public static volatile SingularAttribute<Dish, Double> zRate;
	public static volatile SingularAttribute<Dish, Double> discount;
	public static volatile SingularAttribute<Dish, String> isActive;
	public static volatile SingularAttribute<Dish, Timestamp> createdAt;
	public static volatile SingularAttribute<Dish, Double> nonAcRate;
	public static volatile SingularAttribute<Dish, Double> rate;
	public static volatile SingularAttribute<Dish, Integer> dishId;
	public static volatile SingularAttribute<Dish, Double> ebRate;
	public static volatile SingularAttribute<Dish, String> dishName;
	public static volatile SingularAttribute<Dish, Double> expressRate;
	public static volatile SingularAttribute<Dish, Category> category;
	public static volatile SingularAttribute<Dish, Kitchen> kitchen;
	public static volatile SingularAttribute<Dish, String> barcode;
	public static volatile SingularAttribute<Dish, Date> updatedAt;

	public static final String TA_RATE = "taRate";
	public static final String U_RATE = "uRate";
	public static final String HD_RATE = "hdRate";
	public static final String Z_RATE = "zRate";
	public static final String DISCOUNT = "discount";
	public static final String IS_ACTIVE = "isActive";
	public static final String CREATED_AT = "createdAt";
	public static final String NON_AC_RATE = "nonAcRate";
	public static final String RATE = "rate";
	public static final String DISH_ID = "dishId";
	public static final String EB_RATE = "ebRate";
	public static final String DISH_NAME = "dishName";
	public static final String EXPRESS_RATE = "expressRate";
	public static final String CATEGORY = "category";
	public static final String KITCHEN = "kitchen";
	public static final String BARCODE = "barcode";
	public static final String UPDATED_AT = "updatedAt";

}

