package com.sunan.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, Date> anniversaryDate;
	public static volatile SingularAttribute<Customer, String> address;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, Date> registrationDate;
	public static volatile SingularAttribute<Customer, Integer> id;
	public static volatile SingularAttribute<Customer, String> isActive;
	public static volatile SingularAttribute<Customer, Date> birthDate;
	public static volatile SingularAttribute<Customer, String> email;
	public static volatile SingularAttribute<Customer, String> contactNo;

	public static final String ANNIVERSARY_DATE = "anniversaryDate";
	public static final String ADDRESS = "address";
	public static final String NAME = "name";
	public static final String REGISTRATION_DATE = "registrationDate";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String BIRTH_DATE = "birthDate";
	public static final String EMAIL = "email";
	public static final String CONTACT_NO = "contactNo";

}

