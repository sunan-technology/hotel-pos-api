package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, Double> sc;
	public static volatile SingularAttribute<Category, Double> st;
	public static volatile SingularAttribute<Category, Timestamp> createdAt;
	public static volatile SingularAttribute<Category, Double> vat;
	public static volatile SingularAttribute<Category, Integer> id;
	public static volatile SingularAttribute<Category, String> isActive;
	public static volatile SingularAttribute<Category, String> categoryName;
	public static volatile SingularAttribute<Category, Date> updatedAt;

	public static final String SC = "sc";
	public static final String ST = "st";
	public static final String CREATED_AT = "createdAt";
	public static final String VAT = "vat";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String CATEGORY_NAME = "categoryName";
	public static final String UPDATED_AT = "updatedAt";

}

