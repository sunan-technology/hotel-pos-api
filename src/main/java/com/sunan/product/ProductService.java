package com.sunan.product;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.model.Hotel;
import com.sunan.model.Product;
import com.sunan.model.ProductOpeningStock;
import com.sunan.produt_openingStock.ProductOpeningStockMapper;
import com.sunan.produt_openingStock.ProductOpeningStockRepository;
import com.sunan.utils.JsonUtils;

@Service
public class ProductService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	ProductOpeningStockMapper productOpeningStockMapper;
	
	@Autowired
	ProductOpeningStockRepository productOpeningStockRepository;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(ProductRequestDto productRequestDto,int hotelId) {
		//todo:// request validation
		Product product = productMapper.getProductBuilder(productRequestDto);
		product.setHotel(new Hotel(hotelId));
		productRepository.save(product);
		//add product entry in the product opening stock table 
		List<ProductOpeningStock> list =  productOpeningStockMapper.getProductOpeningStockBuilder(productRequestDto.getOpeningStockDtos(), product.getId(),hotelId);
		
		productOpeningStockRepository.saveAll(list);
		
		logger.info("Service: Save product details with opening stock");
		return utils.objectMapperSuccess(productMapper.getProductDtoBuilder(product), "Product Details Saved");
	}

	@Transactional
	public String update(ProductDto productDto, int id) {
		logger.info("Service: Update product details with id {}", id);
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: product details found with id {} for update operation", id);
			Product product = productMapper.getProductBuilder(productDto);
			productRepository.save(product);
			return utils.objectMapperSuccess(productMapper.getProductDtoBuilder(product), "Product Details Updated");
		}
		logger.info("Service: product details not found with id {} for update operation", id);
		return utils.objectMapperError("Product Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete product details with id {}", id);
		int isDelete = productRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: product details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Product Deleted Successfully");
		}
		logger.info("Service: product details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Product Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching product details with id {}", id);
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			logger.info("Service: product details found with id {}", id);
			ProductDto dto = productMapper.getProductDtoBuilder(product.get());
			return utils.objectMapperSuccess(dto, "Product Details");
		}
		logger.info("Service: product details not found with id {}", id);
		return utils.objectMapperError("Product Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of product details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pagedResult = null;
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = productRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = productRepository.findByProductNameContainingIgnoreCaseAndIsActive(searchTerm, "yes",
					pageable);
		}

		Page<ProductDto> page = pagedResult.map(new Function<Product, ProductDto>() {
			@Override
			public ProductDto apply(Product entity) {
				ProductDto dto = productMapper.getProductDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of products details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive products list.");
	}

}
