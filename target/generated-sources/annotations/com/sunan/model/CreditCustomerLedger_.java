package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreditCustomerLedger.class)
public abstract class CreditCustomerLedger_ {

	public static volatile SingularAttribute<CreditCustomerLedger, Date> date;
	public static volatile SingularAttribute<CreditCustomerLedger, CreditCustomer> creditCustomer;
	public static volatile SingularAttribute<CreditCustomerLedger, Timestamp> createdAt;
	public static volatile SingularAttribute<CreditCustomerLedger, Integer> id;
	public static volatile SingularAttribute<CreditCustomerLedger, String> label;
	public static volatile SingularAttribute<CreditCustomerLedger, Double> debit;
	public static volatile SingularAttribute<CreditCustomerLedger, Double> credit;
	public static volatile SingularAttribute<CreditCustomerLedger, String> isActive;
	public static volatile SingularAttribute<CreditCustomerLedger, String> ledgerNo;
	public static volatile SingularAttribute<CreditCustomerLedger, Date> updatedAt;

	public static final String DATE = "date";
	public static final String CREDIT_CUSTOMER = "creditCustomer";
	public static final String CREATED_AT = "createdAt";
	public static final String ID = "id";
	public static final String LABEL = "label";
	public static final String DEBIT = "debit";
	public static final String CREDIT = "credit";
	public static final String IS_ACTIVE = "isActive";
	public static final String LEDGER_NO = "ledgerNo";
	public static final String UPDATED_AT = "updatedAt";

}

