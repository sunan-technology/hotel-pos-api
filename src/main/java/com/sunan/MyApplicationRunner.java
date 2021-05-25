package com.sunan;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sunan.constants.DefaultConstantValues;
import com.sunan.model.Roles;
import com.sunan.model.Units;
import com.sunan.roles.RolesRepository;
import com.sunan.unit.UnitsRepository;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private UnitsRepository unitsRepository;
	
	@Transactional
	private void initialRoles() {
		List<Roles> exist = rolesRepository.findByIsActive(DefaultConstantValues.isActiveFlagYes);
		if (exist.size() <= 0) {
			try {
				List<Roles> list = Arrays.asList(
						new Roles(1, DefaultConstantValues.ROLE_SUPERADMIN, DefaultConstantValues.isActiveFlagYes),
						new Roles(2,  DefaultConstantValues.ROLE_ADMIN,  DefaultConstantValues.isActiveFlagYes),
				        new Roles(3, DefaultConstantValues.ROLE_MANAGER,  DefaultConstantValues.isActiveFlagYes),
						new Roles(4, DefaultConstantValues.ROLE_WAITER,  DefaultConstantValues.isActiveFlagYes) ,
						new Roles(5, DefaultConstantValues.ROLE_SHEF,  DefaultConstantValues.isActiveFlagYes));
				       
					
						
				for (Roles role : list) {
					rolesRepository.save(role);
				}
				logger.info("Add default roles ");
				
			}catch(Exception e) {
				logger.error("Error while inserting data in role table, msg {}", e.getMessage());
			}
		}
	}
	
	@Transactional
	private void initialUnits() {
		List<Units> exists=unitsRepository.findByIsActive(DefaultConstantValues.isActiveFlagYes);
		if(exists.size() <= 0) {
			try {
				List<Units> list=Arrays.asList(
						new Units(1,DefaultConstantValues.UNIT_KG,DefaultConstantValues.isActiveFlagYes),
						new Units(2,DefaultConstantValues.UNIT_LTR,DefaultConstantValues.isActiveFlagYes),
						new Units(3,DefaultConstantValues.UNIT_ML,DefaultConstantValues.isActiveFlagYes)
						);
				
				for(Units unit :list) {
					unitsRepository.save(unit);
				}
				
				logger.info("Add default units ");
			}catch(Exception e) {
				logger.error("Error while inserting data in units table, msg {}", e.getMessage());
			}
		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("ApplicationRunner#run()");
		synchronized (this) {
		initialRoles();
		initialUnits();
		}
		logger.info("Started HotelPOSApiApplication...");

	}

}
