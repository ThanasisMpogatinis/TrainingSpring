package com.spring.pro03.service;

import java.util.List;
import com.spring.pro03.DTO.ShelfDTO;

public interface ShelfService {

	public List<ShelfDTO> findAll();
	
	public ShelfDTO findById(long shId);
	
	public ShelfDTO save(ShelfDTO theShelf);
	
	public void delete(long theId);
	
}
