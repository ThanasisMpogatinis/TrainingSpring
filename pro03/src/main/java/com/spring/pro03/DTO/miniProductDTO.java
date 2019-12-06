package com.spring.pro03.DTO;

import com.spring.pro03.entity.Product;

public class miniProductDTO {

	private long id;
	private String barcode;
	private String category;
	private String description;
	private String m_unit;
	
	public miniProductDTO() {}
	
	public miniProductDTO(Product product) {
		this.setId(product.getId()); 
		this.setBarcode(product.getBarcode());
		this.setCategory(product.getCategory());
		this.setDescription(product.getDescription());
		this.setM_unit(product.getM_unit());
	}
	
	public miniProductDTO(long id, String barcode, String category, String description, String m_unit) {
		this.id = id;
		this.barcode = barcode;
		this.category = category;
		this.description = description;
		this.m_unit = m_unit;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getM_unit() {
		return m_unit;
	}
	public void setM_unit(String m_unit) {
		this.m_unit = m_unit;
	}
	
}
