package com.spring.pro03.service;

import java.util.List;
import com.spring.pro03.DTO.ProductDTO;

public interface ProductService {

	public List<ProductDTO> findAll();
	
	public ProductDTO findById(long theId);
	
	public ProductDTO save(ProductDTO theProduct);
	
	public void delete(long theId);
	
}
