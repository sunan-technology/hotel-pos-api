package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreditCustomer.class)
public abstract class CreditCustomer_ {

	public static volatile SingularAttribute<CreditCustomer, Timestamp> createdAt;
	public static volatile SingularAttribute<CreditCustomer, String> address;
	public static volatile SingularAttribute<CreditCustomer, Date> registerationDate;
	public static volatile SingularAttribute<CreditCustomer, String> name;
	public static volatile SingularAttribute<CreditCustomer, String> isActive;
	public static volatile SingularAttribute<CreditCustomer, Integer> creditCustomerId;
	public static volatile SingularAttribute<CreditCustomer, String> contactNo;
	public static volatile SingularAttribute<CreditCustomer, Date> updatedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String ADDRESS = "address";
	public static final String REGISTERATION_DATE = "registerationDate";
	public static final String NAME = "name";
	public static final String IS_ACTIVE = "isActive";
	public static final String CREDIT_CUSTOMER_ID = "creditCustomerId";
	public static final String CONTACT_NO = "contactNo";
	public static final String UPDATED_AT = "updatedAt";

}

