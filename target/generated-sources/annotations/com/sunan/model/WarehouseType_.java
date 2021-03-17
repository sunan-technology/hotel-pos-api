package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WarehouseType.class)
public abstract class WarehouseType_ {

	public static volatile SingularAttribute<WarehouseType, Timestamp> createdAt;
	public static volatile SingularAttribute<WarehouseType, String> name;
	public static volatile SingularAttribute<WarehouseType, Integer> id;
	public static volatile SingularAttribute<WarehouseType, String> isActive;
	public static volatile SingularAttribute<WarehouseType, Date> updatedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String UPDATED_AT = "updatedAt";

}

