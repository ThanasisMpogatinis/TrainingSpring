package com.spring.pro03.DTO;

import java.util.List;
import com.spring.pro03.entity.Shelf;

public class FullShelfDTO {

	private long id;
	private int code;
	private List<miniProductDTO> products;
	
	public FullShelfDTO() {}

	public FullShelfDTO(int code, List<miniProductDTO> products) {
		this.code = code;
		this.products = products;
	}
	
	public FullShelfDTO(Shelf shelf) {
		this.setId(shelf.getId());
		this.setCode(shelf.getCode());		
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
	public List<miniProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<miniProductDTO> products) {
		this.products = products;
	}
	
}
