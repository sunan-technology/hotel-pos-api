package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HotelTable.class)
public abstract class HotelTable_ {

	public static volatile SingularAttribute<HotelTable, String> tableType;
	public static volatile SingularAttribute<HotelTable, Timestamp> createdAt;
	public static volatile SingularAttribute<HotelTable, String> floorNo;
	public static volatile SingularAttribute<HotelTable, String> isActive;
	public static volatile SingularAttribute<HotelTable, Integer> tableNo;
	public static volatile SingularAttribute<HotelTable, String> status;
	public static volatile SingularAttribute<HotelTable, Date> updatedAt;

	public static final String TABLE_TYPE = "tableType";
	public static final String CREATED_AT = "createdAt";
	public static final String FLOOR_NO = "floorNo";
	public static final String IS_ACTIVE = "isActive";
	public static final String TABLE_NO = "tableNo";
	public static final String STATUS = "status";
	public static final String UPDATED_AT = "updatedAt";

}

