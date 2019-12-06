package com.spring.pro03.service;

import java.util.List;
import com.spring.pro03.DTO.FullWarehouseDTO;
import com.spring.pro03.DTO.WarehouseDTO;
import com.spring.pro03.DTO.miniWarehouseDTO;

public interface WarehouseService {

	public List<WarehouseDTO> findAll();
	
	public List<FullWarehouseDTO> findEverything();
	
	public miniWarehouseDTO findById(long whId);
	
	public WarehouseDTO save(WarehouseDTO theWarehouse);
	
	public void delete(long whId);
	
}
