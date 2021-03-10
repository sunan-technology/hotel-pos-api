package com.sunan.produt_openingStock;

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

import com.sunan.model.Product_OpeningStock;
import com.sunan.utils.JsonUtils;

@Service
public class Product_OpeningStockService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(Product_OpeningStockService.class);

	@Autowired
	private Product_OpeningStockRepository productOpeningStockRepository;

	@Autowired
	Product_OpeningStockMapper productOpeningStockMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(Product_OpeningStockDto productOpeningStockDto) {
		Product_OpeningStock productOpeningStock = productOpeningStockMapper
				.getProductOpeningStockBuilder(productOpeningStockDto);
		productOpeningStockRepository.save(productOpeningStock);
		logger.info("Service: product opening stock details");
		return utils.objectMapperSuccess(
				productOpeningStockMapper.getProductOpeningStockDtoBuilder(productOpeningStock),
				" Product opening stock Details Saved");
	}

	@Transactional
	public String update(Product_OpeningStockDto productOpeningStockDto, int id) {
		logger.info("Service: Update product opening stock details with id {}", id);
		Optional<Product_OpeningStock> optional = productOpeningStockRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: product opening stock details found with id {} for update operation", id);
			Product_OpeningStock productOpeningStock = productOpeningStockMapper
					.getProductOpeningStockBuilder(productOpeningStockDto);
			productOpeningStockRepository.save(productOpeningStock);
			return utils.objectMapperSuccess(
					productOpeningStockMapper.getProductOpeningStockDtoBuilder(productOpeningStock),
					"Product opening stock Details Updated");
		}
		logger.info("Service: Product opening stock details not found with id {} for update operation", id);
		return utils.objectMapperError("Product opening stock Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete product opening stock details with id {}", id);
		int isDelete = productOpeningStockRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: product opening stock details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Product opening stock Deleted Successfully");
		}
		logger.info("Service: product opening stock details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Product opening stock Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching  product opening stock details with id {}", id);
		Optional<Product_OpeningStock> productOpeningStock = productOpeningStockRepository.findById(id);
		if (productOpeningStock.isPresent()) {
			logger.info("Service:  product opening stock details found with id {}", id);
			Product_OpeningStockDto dto = productOpeningStockMapper
					.getProductOpeningStockDtoBuilder(productOpeningStock.get());
			return utils.objectMapperSuccess(dto, " Product opening stock Details");
		}
		logger.info("Service:  Product opening stock details not found with id {}", id);
		return utils.objectMapperError(" Product opening stock Details Not found, Id :" + id);
	}

	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of product details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product_OpeningStock> pagedResult = null;

		pagedResult = productOpeningStockRepository.findByIsActive("yes", pageable);

		Page<Product_OpeningStockDto> page = pagedResult
				.map(new Function<Product_OpeningStock, Product_OpeningStockDto>() {
					@Override
					public Product_OpeningStockDto apply(Product_OpeningStock entity) {
						Product_OpeningStockDto dto = productOpeningStockMapper
								.getProductOpeningStockDtoBuilder(entity);
						return dto;
					}
				});
		logger.info("Service: Fetching list of  product opening stock details, total records: {}",
				page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive  product opening stock list.");
	}
}
