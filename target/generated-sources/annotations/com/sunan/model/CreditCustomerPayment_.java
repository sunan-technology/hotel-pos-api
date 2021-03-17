package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreditCustomerPayment.class)
public abstract class CreditCustomerPayment_ {

	public static volatile SingularAttribute<CreditCustomerPayment, Date> date;
	public static volatile SingularAttribute<CreditCustomerPayment, CreditCustomer> creditCustomer;
	public static volatile SingularAttribute<CreditCustomerPayment, Timestamp> createdAt;
	public static volatile SingularAttribute<CreditCustomerPayment, Double> amount;
	public static volatile SingularAttribute<CreditCustomerPayment, String> paymentMode;
	public static volatile SingularAttribute<CreditCustomerPayment, String> paymentModeDetails;
	public static volatile SingularAttribute<CreditCustomerPayment, String> remark;
	public static volatile SingularAttribute<CreditCustomerPayment, Integer> id;
	public static volatile SingularAttribute<CreditCustomerPayment, String> isActive;
	public static volatile SingularAttribute<CreditCustomerPayment, Date> updatedAt;

	public static final String DATE = "date";
	public static final String CREDIT_CUSTOMER = "creditCustomer";
	public static final String CREATED_AT = "createdAt";
	public static final String AMOUNT = "amount";
	public static final String PAYMENT_MODE = "paymentMode";
	public static final String PAYMENT_MODE_DETAILS = "paymentModeDetails";
	public static final String REMARK = "remark";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String UPDATED_AT = "updatedAt";

}

