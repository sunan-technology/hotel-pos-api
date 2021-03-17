package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MemberLedger.class)
public abstract class MemberLedger_ {

	public static volatile SingularAttribute<MemberLedger, Date> date;
	public static volatile SingularAttribute<MemberLedger, Timestamp> createdAt;
	public static volatile SingularAttribute<MemberLedger, Member> member;
	public static volatile SingularAttribute<MemberLedger, String> lable;
	public static volatile SingularAttribute<MemberLedger, Double> credit;
	public static volatile SingularAttribute<MemberLedger, Double> debit;
	public static volatile SingularAttribute<MemberLedger, Integer> ledgerNo;
	public static volatile SingularAttribute<MemberLedger, Date> updatedAt;

	public static final String DATE = "date";
	public static final String CREATED_AT = "createdAt";
	public static final String MEMBER = "member";
	public static final String LABLE = "lable";
	public static final String CREDIT = "credit";
	public static final String DEBIT = "debit";
	public static final String LEDGER_NO = "ledgerNo";
	public static final String UPDATED_AT = "updatedAt";

}

