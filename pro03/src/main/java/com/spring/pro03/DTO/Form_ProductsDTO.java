package com.spring.pro03.DTO;

import com.spring.pro03.entity.Form_Product;

public class Form_ProductsDTO {

	private long id;
	private long form_id;
	private long shelf_id;
	private long product_id;
	private int quantity;
	
	public Form_ProductsDTO() {}

	public Form_ProductsDTO(long form_id, long shelf_id, long product_id, int quantity) {
		this.form_id = form_id;
		this.shelf_id = shelf_id;
		this.product_id = product_id;
		this.quantity = quantity;
	}
	
	public Form_ProductsDTO(Form_Product f_p) {
		this.setId(f_p.getId());
		this.setForm_id(f_p.getForm().getId());
		this.setShelf_id(f_p.getShelf().getId());
		this.setProduct_id(f_p.getId());
		this.setQuantity(f_p.getQuantity());		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getForm_id() {
		return form_id;
	}
	public void setForm_id(long form_id) {
		this.form_id = form_id;
	}
	public long getShelf_id() {
		return shelf_id;
	}
	public void setShelf_id(long shelf_id) {
		this.shelf_id = shelf_id;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
		
}
