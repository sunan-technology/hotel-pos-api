package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Supplier.class)
public abstract class Supplier_ {

	public static volatile SingularAttribute<Supplier, String> supplierName;
	public static volatile SingularAttribute<Supplier, Integer> supplierId;
	public static volatile SingularAttribute<Supplier, String> address;
	public static volatile SingularAttribute<Supplier, String> city;
	public static volatile SingularAttribute<Supplier, String> cst;
	public static volatile SingularAttribute<Supplier, String> accountName;
	public static volatile SingularAttribute<Supplier, String> emailId;
	public static volatile SingularAttribute<Supplier, String> accountNumber;
	public static volatile SingularAttribute<Supplier, String> isActive;
	public static volatile SingularAttribute<Supplier, String> branch;
	public static volatile SingularAttribute<Supplier, String> openingBalanceType;
	public static volatile SingularAttribute<Supplier, String> zipcode;
	public static volatile SingularAttribute<Supplier, Timestamp> createdAt;
	public static volatile SingularAttribute<Supplier, String> bank;
	public static volatile SingularAttribute<Supplier, String> stNo;
	public static volatile SingularAttribute<Supplier, String> tin;
	public static volatile SingularAttribute<Supplier, String> state;
	public static volatile SingularAttribute<Supplier, String> pan;
	public static volatile SingularAttribute<Supplier, String> ifscCode;
	public static volatile SingularAttribute<Supplier, Double> openingBalance;
	public static volatile SingularAttribute<Supplier, String> remarks;
	public static volatile SingularAttribute<Supplier, String> contactNo;
	public static volatile SingularAttribute<Supplier, Date> updatedAt;

	public static final String SUPPLIER_NAME = "supplierName";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String CST = "cst";
	public static final String ACCOUNT_NAME = "accountName";
	public static final String EMAIL_ID = "emailId";
	public static final String ACCOUNT_NUMBER = "accountNumber";
	public static final String IS_ACTIVE = "isActive";
	public static final String BRANCH = "branch";
	public static final String OPENING_BALANCE_TYPE = "openingBalanceType";
	public static final String ZIPCODE = "zipcode";
	public static final String CREATED_AT = "createdAt";
	public static final String BANK = "bank";
	public static final String ST_NO = "stNo";
	public static final String TIN = "tin";
	public static final String STATE = "state";
	public static final String PAN = "pan";
	public static final String IFSC_CODE = "ifscCode";
	public static final String OPENING_BALANCE = "openingBalance";
	public static final String REMARKS = "remarks";
	public static final String CONTACT_NO = "contactNo";
	public static final String UPDATED_AT = "updatedAt";

}

