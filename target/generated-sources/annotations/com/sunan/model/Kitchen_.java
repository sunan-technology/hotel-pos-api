package com.sunan.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Kitchen.class)
public abstract class Kitchen_ {

	public static volatile SingularAttribute<Kitchen, String> printer;
	public static volatile SingularAttribute<Kitchen, Integer> id;
	public static volatile SingularAttribute<Kitchen, String> isActive;
	public static volatile SingularAttribute<Kitchen, String> kitchenName;

	public static final String PRINTER = "printer";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String KITCHEN_NAME = "kitchenName";

}

