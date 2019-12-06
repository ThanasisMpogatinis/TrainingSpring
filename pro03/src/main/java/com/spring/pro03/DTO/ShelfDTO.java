package com.spring.pro03.DTO;

import com.spring.pro03.entity.Shelf;
import com.spring.pro03.entity.Warehouse;

public class ShelfDTO {

	private long id;
	private int code;
	private long warehouse_id;
	
	public ShelfDTO() {}

	public ShelfDTO(int code, long warehouse_id) {
		this.code = code;
		this.warehouse_id = warehouse_id;
	}
	
	public ShelfDTO(Shelf shelf) {
		this.setId(shelf.getId());
		this.setCode(shelf.getCode());
		
		Warehouse warehouse = shelf.getWarehouse();
		
		this.setWarehouse_id(warehouse.getId());
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public long getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
		
}
