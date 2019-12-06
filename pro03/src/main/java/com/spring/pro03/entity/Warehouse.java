package com.spring.pro03.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.spring.pro03.DTO.WarehouseDTO;
import com.spring.pro03.DTO.miniShelfDTO;
import com.spring.pro03.DTO.miniWarehouseDTO;

@Entity
@Table(name="warehouse")
public class Warehouse {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="warehouse", cascade={CascadeType.MERGE})
	private List<Shelf> shelves;
	
	public Warehouse() {}
	
	public Warehouse(String description, List<Shelf> shelves) {
		this.description = description;
		this.shelves = shelves;
	}
	public Warehouse(miniWarehouseDTO warehouseDTO) {
		this.setId(warehouseDTO.getId());
		this.setDescription(warehouseDTO.getDescription());
		
		List<miniShelfDTO> shelvesDTO = warehouseDTO.getShelves();
		List<Shelf> shelves = new ArrayList<>();
		
		for(miniShelfDTO sDTO : shelvesDTO) {
			Shelf s = new Shelf();
			s.setId(sDTO.getId());
			s.setCode(sDTO.getCode());
			shelves.add(s);
		}
		this.setShelves(shelves);
	}

	public Warehouse(WarehouseDTO warehouseDTO) {
		this.setId(warehouseDTO.getId());
		this.setDescription(warehouseDTO.getDescription());
		
		List<miniShelfDTO> miniShelvesDTO = warehouseDTO.getShelves();
		List<Shelf> shelves = new ArrayList<>();
		
		Shelf s = new Shelf();
		
		for(miniShelfDTO sDTO : miniShelvesDTO) {	
			s.setId(sDTO.getId());
			s.setCode(sDTO.getCode());
			
			shelves.add(s);
		}
		this.setShelves(shelves);
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
	public List<Shelf> getShelves() {
		return shelves;
	}
	public void setShelves(List<Shelf> shelves) {
		this.shelves = shelves;
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", description=" + description + "]";
	}
	
}
