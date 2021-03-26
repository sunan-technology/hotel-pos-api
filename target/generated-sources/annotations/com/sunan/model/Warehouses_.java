package com.sunan.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Warehouses.class)
public abstract class Warehouses_ {

	public static volatile SingularAttribute<Warehouses, String> address;
	public static volatile SingularAttribute<Warehouses, String> city;
	public static volatile SingularAttribute<Warehouses, Integer> id;
	public static volatile SingularAttribute<Warehouses, WarehouseType> warehouseType;
	public static volatile SingularAttribute<Warehouses, String> isActive;
	public static volatile SingularAttribute<Warehouses, String> warehouseName;

	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String ID = "id";
	public static final String WAREHOUSE_TYPE = "warehouseType";
	public static final String IS_ACTIVE = "isActive";
	public static final String WAREHOUSE_NAME = "warehouseName";

}

