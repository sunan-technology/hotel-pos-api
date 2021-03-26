package com.sunan.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Recipe.class)
public abstract class Recipe_ {

	public static volatile SingularAttribute<Recipe, String> recipeName;
	public static volatile SingularAttribute<Recipe, String> discription;
	public static volatile SingularAttribute<Recipe, Dish> dish;
	public static volatile SingularAttribute<Recipe, Double> fixedCost;
	public static volatile SingularAttribute<Recipe, Integer> id;
	public static volatile SingularAttribute<Recipe, String> isActive;

	public static final String RECIPE_NAME = "recipeName";
	public static final String DISCRIPTION = "discription";
	public static final String DISH = "dish";
	public static final String FIXED_COST = "fixedCost";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";

}

