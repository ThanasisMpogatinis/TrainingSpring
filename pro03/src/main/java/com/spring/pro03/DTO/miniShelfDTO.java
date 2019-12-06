package com.spring.pro03.DTO;

import com.spring.pro03.entity.Shelf;

public class miniShelfDTO {

	private long id;
	private int code;

	public miniShelfDTO() {}	
	
	public miniShelfDTO(long id, int code) {
		this.id = id;
		this.code = code;
	}
	
	public miniShelfDTO(Shelf shelf) {
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

}
