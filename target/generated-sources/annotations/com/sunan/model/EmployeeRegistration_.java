package com.sunan.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeRegistration.class)
public abstract class EmployeeRegistration_ {

	public static volatile SingularAttribute<EmployeeRegistration, String> employeeName;
	public static volatile SingularAttribute<EmployeeRegistration, Date> dateOfJoining;
	public static volatile SingularAttribute<EmployeeRegistration, String> address;
	public static volatile SingularAttribute<EmployeeRegistration, String> city;
	public static volatile SingularAttribute<EmployeeRegistration, String> photo;
	public static volatile SingularAttribute<EmployeeRegistration, String> employeeId;
	public static volatile SingularAttribute<EmployeeRegistration, Integer> id;
	public static volatile SingularAttribute<EmployeeRegistration, String> isActive;
	public static volatile SingularAttribute<EmployeeRegistration, String> email;
	public static volatile SingularAttribute<EmployeeRegistration, String> contactNo;

	public static final String EMPLOYEE_NAME = "employeeName";
	public static final String DATE_OF_JOINING = "dateOfJoining";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String PHOTO = "photo";
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String EMAIL = "email";
	public static final String CONTACT_NO = "contactNo";

}

