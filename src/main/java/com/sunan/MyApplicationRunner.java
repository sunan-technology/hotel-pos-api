package com.sunan;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sunan.constants.DefaultConstantValues;
import com.sunan.hotel.HotelRepository;
import com.sunan.model.Hotel;
import com.sunan.model.Roles;
import com.sunan.model.Units;
import com.sunan.model.User;
import com.sunan.roles.RolesRepository;
import com.sunan.unit.UnitsRepository;
import com.sunan.user.UserRepository;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private UnitsRepository unitsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	
//	@Autowired
//	PasswordEncoder encoder;
	
	
//	@Value("${hotel.pos.username}")
	private String superAdminUserName="admin";

	
//	@Value("${hotel.pos.password}")
	private String superAdminPassword="admin";
	
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
	private Hotel addDefaultHotel() {
		
		Optional<Hotel> opt=hotelRepository.findByEmail("superhotel@gmail.com");
		if(!opt.isPresent()) {
			
				Hotel hotel=new Hotel(1,"super hotel","pune","1234567890","superhotel@gmail.com","yes");
				hotelRepository.save(hotel);
				return hotel;
					
		}else {
			return opt.get();
		}
		
		
	}
	
	
	@Transactional
	private User addSuperAdmin() {	 
		Optional<User> opt = userRepository.findByUserName(superAdminUserName);
		if(!opt.isPresent()) {
//			superAdminPassword = PasswordUtil.decrypt(superAdminPassword);
			User superAdmin = new User(1, "sunan", superAdminUserName, superAdminPassword,"Islampur","superadmin@gmail.com", new Roles(1),DefaultConstantValues.isActiveFlagYes,new Hotel(1));
			userRepository.save(superAdmin);
			return superAdmin;
		}else {
			return opt.get();
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
	    addDefaultHotel();
		initialRoles();
		addSuperAdmin();
		initialUnits();
		}
		logger.info("Started HotelPOSApiApplication...");

	}

}
