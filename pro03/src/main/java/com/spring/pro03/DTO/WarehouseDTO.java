package com.spring.pro03.DTO;

import java.util.ArrayList;
import java.util.List;

import com.spring.pro03.entity.Shelf;
import com.spring.pro03.entity.Warehouse;

public class WarehouseDTO {
	
	private long id;
	private String description;
	private List<miniShelfDTO> shelves;
	
	public WarehouseDTO() {}
	
	public WarehouseDTO(String description, List<miniShelfDTO> shelves) {
		this.description = description;
		this.shelves = shelves;
	}

	public WarehouseDTO(Warehouse warehouse) {
		this.setId(warehouse.getId());
		this.setDescription(warehouse.getDescription());
		
		List<Shelf> shelves = warehouse.getShelves();
		List<miniShelfDTO> miniShelvesDTO = new ArrayList<>();
				
		for(Shelf s : shelves) {
			miniShelfDTO msDTO = new miniShelfDTO();
			msDTO.setId(s.getId());
			msDTO.setCode(s.getCode());
			miniShelvesDTO.add(msDTO);			
		}
		this.setShelves(miniShelvesDTO);
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
	public List<miniShelfDTO> getShelves() {
		return shelves;
	}
	public void setShelves(List<miniShelfDTO> shelfDTOList) {
		this.shelves = shelfDTOList;
	}

}
