package com.sunan.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Member.class)
public abstract class Member_ {

	public static volatile SingularAttribute<Member, String> address;
	public static volatile SingularAttribute<Member, Date> registerationDate;
	public static volatile ListAttribute<Member, MemberLedger> memberLedgers;
	public static volatile SingularAttribute<Member, String> name;
	public static volatile SingularAttribute<Member, String> isActive;
	public static volatile SingularAttribute<Member, Integer> cardNo;
	public static volatile SingularAttribute<Member, Integer> memberId;
	public static volatile SingularAttribute<Member, Integer> contactNo;

	public static final String ADDRESS = "address";
	public static final String REGISTERATION_DATE = "registerationDate";
	public static final String MEMBER_LEDGERS = "memberLedgers";
	public static final String NAME = "name";
	public static final String IS_ACTIVE = "isActive";
	public static final String CARD_NO = "cardNo";
	public static final String MEMBER_ID = "memberId";
	public static final String CONTACT_NO = "contactNo";

}

