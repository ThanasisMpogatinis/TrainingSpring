package com.spring.pro03.DTO;

public class putStockDTO {

	private long shelf_id;
	private int quantity;
	
	public putStockDTO() {}

	public long getShelf_id() {
		return shelf_id;
	}
	public void setShelf_id(long shelf_id) {
		this.shelf_id = shelf_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
			
}
