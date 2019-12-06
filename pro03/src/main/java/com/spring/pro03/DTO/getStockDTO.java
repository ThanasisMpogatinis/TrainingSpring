package com.spring.pro03.DTO;

public class getStockDTO {

	private long product_id;
	private String date;
	
	public getStockDTO() {}
	
	public getStockDTO(Form_ProductsDTO formProduct, FormDTO form) {
		this.product_id = formProduct.getProduct_id();
		this.date = form.getDate();
	}
	
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
