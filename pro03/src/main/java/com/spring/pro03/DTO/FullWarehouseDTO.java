package com.spring.pro03.DTO;

import java.util.ArrayList;
import java.util.List;
import com.spring.pro03.entity.Shelf;
import com.spring.pro03.entity.Warehouse;

public class FullWarehouseDTO {

	private long id;
	private String description;
	private List<FullShelfDTO> shelves;
	
	public FullWarehouseDTO() {}
	
	public FullWarehouseDTO(String description, List<FullShelfDTO> shelves) {
		this.description = description;
		this.shelves = shelves;
	}

	public FullWarehouseDTO(Warehouse warehouse) {
		this.setId(warehouse.getId());
		this.setDescription(warehouse.getDescription());
		
		List<Shelf> shelves = warehouse.getShelves();
		List<FullShelfDTO> fullShelvesDTO = new ArrayList<>();
				
		for(Shelf s : shelves) {
			FullShelfDTO fsDTO = new FullShelfDTO();
			fsDTO.setId(s.getId());
			fsDTO.setCode(s.getCode());
					
			fullShelvesDTO.add(fsDTO);			
		}
		this.setShelves(fullShelvesDTO);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<FullShelfDTO> getShelves() {
		return shelves;
	}
	public void setShelves(List<FullShelfDTO> shelves) {
		this.shelves = shelves;
	}
	
}
