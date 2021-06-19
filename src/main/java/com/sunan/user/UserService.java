package com.sunan.user;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.hotel.HotelRepository;
import com.sunan.model.User;
import com.sunan.model.Hotel;
import com.sunan.utils.EmailUtil;
import com.sunan.utils.JsonUtils;

@Service
public class UserService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	EmailUtil emailUtil;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(UserDto userDto, int hotelId) {
		Optional<Hotel> hotel=hotelRepository.findById(hotelId);
		if(hotel.isPresent()) {
		User user = userMapper
				.getUserBuilder(userDto);
		user.setHotel(new Hotel(hotelId));
		userRepository.save(user);
		emailUtil.sendEmail(user.getEmail(), emailUtil.getEmailMsg());
		logger.info("Service: Save user details");
		return utils.objectMapperSuccess(
				userMapper.getUserDtoBuilder(user),
				"User Details Saved");
		}else {
			logger.info("Service: hotel not found");
			return utils.objectMapperError("Hotel not found");
		}
	}

	@Transactional
	public String update(UserDto userDto, int id,int hotelId) {
		logger.info("Service: Update user details with id {}", id);
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: user details found with id {} for update operation", id);
			User user = userMapper
					.getUserBuilder(userDto);
			user.setHotel(new Hotel(hotelId));
			userRepository.save(user);
			return utils.objectMapperSuccess(
					userMapper.getUserDtoBuilder(user),
					"User Details Updated");
		}
		logger.info("Service: user details not found with id {} for update operation", id);
		return utils.objectMapperError("User Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete user details with id {}", id);
		int isDelete = userRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: user details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("User Deleted Successfully");
		}
		logger.info("Service: user details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("User Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching User details with id {}", id);
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			logger.info("Service: user details found with id {}", id);
			UserDto dto = userMapper
					.getUserDtoBuilder(user.get());
			return utils.objectMapperSuccess(dto, "User Details");
		}
		logger.info("Service: user details not found with id {}", id);
		return utils.objectMapperError("User Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of user details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<User> pagedResult = null;

		pagedResult = userRepository.findByIsActive("yes", pageable);

		Page<UserDto> page = pagedResult
				.map(new Function<User, UserDto>() {
					@Override
					public UserDto apply(User entity) {
						UserDto dto = userMapper
								.getUserDtoBuilder(entity);
						return dto;
					}
				});
		logger.info("Service: Fetching list of user details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive user list.");
	}

	@Transactional
	public String checkEmployeeExist(int id, int hotelId) {
		logger.info("Service : checking user is present or not");
		
	
		if(userRepository.existsByIdAndHotel(id, new Hotel(hotelId))) {
			logger.info("Service : user is already present");
			return utils.objectMapperSuccess("User is already present");
		}else {
			logger.info("Service : user is not present");
			return utils.objectMapperSuccess("User is not present");
		}
		
	}

}
