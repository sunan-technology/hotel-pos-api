package com.sunan.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SupplierLedger.class)
public abstract class SupplierLedger_ {

	public static volatile SingularAttribute<SupplierLedger, Date> date;
	public static volatile SingularAttribute<SupplierLedger, Supplier> supplier;
	public static volatile SingularAttribute<SupplierLedger, String> name;
	public static volatile SingularAttribute<SupplierLedger, Integer> id;
	public static volatile SingularAttribute<SupplierLedger, String> label;
	public static volatile SingularAttribute<SupplierLedger, Double> debit;
	public static volatile SingularAttribute<SupplierLedger, Double> credit;
	public static volatile SingularAttribute<SupplierLedger, String> isActive;
	public static volatile SingularAttribute<SupplierLedger, String> ledgerNo;

	public static final String DATE = "date";
	public static final String SUPPLIER = "supplier";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String LABEL = "label";
	public static final String DEBIT = "debit";
	public static final String CREDIT = "credit";
	public static final String IS_ACTIVE = "isActive";
	public static final String LEDGER_NO = "ledgerNo";

}

