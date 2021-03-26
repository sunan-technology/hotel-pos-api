package com.sunan.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WalletType.class)
public abstract class WalletType_ {

	public static volatile SingularAttribute<WalletType, String> name;
	public static volatile SingularAttribute<WalletType, Integer> id;
	public static volatile SingularAttribute<WalletType, String> isActive;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";

}

