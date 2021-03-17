package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RecipeJoin.class)
public abstract class Recipe_Join_ {

	public static volatile SingularAttribute<RecipeJoin, Timestamp> createdAt;
	public static volatile SingularAttribute<RecipeJoin, Product> product;
	public static volatile SingularAttribute<RecipeJoin, Integer> quantity;
	public static volatile SingularAttribute<RecipeJoin, Recipe> recipe;
	public static volatile SingularAttribute<RecipeJoin, Integer> id;
	public static volatile SingularAttribute<RecipeJoin, String> isActive;
	public static volatile SingularAttribute<RecipeJoin, Double> costPerUnit;
	public static volatile SingularAttribute<RecipeJoin, Double> totalItemCost;
	public static volatile SingularAttribute<RecipeJoin, Date> updatedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String PRODUCT = "product";
	public static final String QUANTITY = "quantity";
	public static final String RECIPE = "recipe";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String COST_PER_UNIT = "costPerUnit";
	public static final String TOTAL_ITEM_COST = "totalItemCost";
	public static final String UPDATED_AT = "updatedAt";

}

