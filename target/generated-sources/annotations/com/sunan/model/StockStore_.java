package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StockStore.class)
public abstract class StockStore_ {

	public static volatile SingularAttribute<StockStore, Timestamp> createdAt;
	public static volatile SingularAttribute<StockStore, Integer> quantity;
	public static volatile SingularAttribute<StockStore, Dish> dish;
	public static volatile SingularAttribute<StockStore, Integer> id;
	public static volatile SingularAttribute<StockStore, String> isActive;
	public static volatile SingularAttribute<StockStore, Date> updatedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String QUANTITY = "quantity";
	public static final String DISH = "dish";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String UPDATED_AT = "updatedAt";

}

