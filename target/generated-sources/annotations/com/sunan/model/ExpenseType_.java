package com.sunan.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExpenseType.class)
public abstract class ExpenseType_ {

	public static volatile SingularAttribute<ExpenseType, String> name;
	public static volatile SingularAttribute<ExpenseType, Integer> id;
	public static volatile SingularAttribute<ExpenseType, String> isActive;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";

}

