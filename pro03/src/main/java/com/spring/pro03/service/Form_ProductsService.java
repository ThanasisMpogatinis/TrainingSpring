package com.spring.pro03.service;

import java.util.List;
import com.spring.pro03.DTO.Form_ProductsDTO;
import com.spring.pro03.DTO.getStockDTO;
import com.spring.pro03.DTO.putStockDTO;

public interface Form_ProductsService {

	public List<Form_ProductsDTO> findAll();
	
	public Form_ProductsDTO findById(long theId);
	
	public Form_ProductsDTO save(Form_ProductsDTO theForm);
	
	public void delete(long theId);
		
	public List<putStockDTO> getStock(getStockDTO stock);	
	
}
