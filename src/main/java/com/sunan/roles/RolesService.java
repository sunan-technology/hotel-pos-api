package com.sunan.roles;

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

import com.sunan.model.Hotel;
import com.sunan.model.Roles;
import com.sunan.utils.JsonUtils;

@Service
public class RolesService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RolesService.class);

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	RolesMapper rolesMapper;

	@Autowired
	private JsonUtils utils;
	
	
	@Transactional
	public String save(RolesDto rolesDto) {
		Roles roles = rolesMapper.getRolesBuilder(rolesDto);
		
		rolesRepository.save(roles);
		logger.info("Service: Save roles details");
		return utils.objectMapperSuccess(rolesMapper.getRolesDtoBuilder(roles), "Roles Details Saved");
	}
	
	
	@Transactional
	public String update(RolesDto rolesDto, int id) {
		logger.info("Service: Update roles type details with id {}", id);
		Optional<Roles> optional = rolesRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: roles details found with id {} for update operation", id);
			Roles roles = rolesMapper.getRolesBuilder(rolesDto);
			rolesRepository.save(roles);
			return utils.objectMapperSuccess(rolesMapper.getRolesDtoBuilder(roles), "Roles Details Updated");
		}
		logger.info("Service: Roles details not found with id {} for update operation", id);
		return utils.objectMapperError("Roles Details Not Found !");
	}
	
	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete roles details with id {}", id);
		int isDelete = rolesRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: roles details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Roles Deleted Successfully");
		}
		logger.info("roles details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Roles Deleted Failed");
	}
	
	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching  roles details with id {}", id);
		Optional<Roles> roles = rolesRepository.findById(id);
		if (roles.isPresent()) {
			logger.info("Service: roles details found with id {}", id);
			RolesDto dto = rolesMapper.getRolesDtoBuilder(roles.get());
			return utils.objectMapperSuccess(dto, " Roles Details");
		}
		logger.info("Service:  Roles details not found with id {}", id);
		return utils.objectMapperError(" Roles Details Not found, Id :" + id);
	}
	
	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of roles details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Roles> pagedResult = null;

		pagedResult = rolesRepository.findByIsActive("yes", pageable);

		Page<RolesDto> page = pagedResult.map(new Function<Roles, RolesDto>() {
			@Override
			public RolesDto apply(Roles entity) {
				RolesDto dto = rolesMapper.getRolesDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of roles details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive roles list.");
	}

}
