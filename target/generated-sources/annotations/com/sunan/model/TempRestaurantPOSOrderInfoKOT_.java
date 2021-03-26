package com.sunan.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TempRestaurantPOSOrderInfoKOT.class)
public abstract class TempRestaurantPOSOrderInfoKOT_ {

	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> kotStatus;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, Double> grandTotal;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> kotType;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, Date> billDate;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> isActive;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> operator;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> groupName;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> ticketNo;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> isEditable;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> ticketNote;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, Integer> id;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> waiter;
	public static volatile SingularAttribute<TempRestaurantPOSOrderInfoKOT, String> tableNo;

	public static final String KOT_STATUS = "kotStatus";
	public static final String GRAND_TOTAL = "grandTotal";
	public static final String KOT_TYPE = "kotType";
	public static final String BILL_DATE = "billDate";
	public static final String IS_ACTIVE = "isActive";
	public static final String OPERATOR = "operator";
	public static final String GROUP_NAME = "groupName";
	public static final String TICKET_NO = "ticketNo";
	public static final String IS_EDITABLE = "isEditable";
	public static final String TICKET_NOTE = "ticketNote";
	public static final String ID = "id";
	public static final String WAITER = "waiter";
	public static final String TABLE_NO = "tableNo";

}

