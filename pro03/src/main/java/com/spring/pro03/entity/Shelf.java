package com.spring.pro03.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.spring.pro03.DTO.ShelfDTO;
import com.spring.pro03.DTO.miniShelfDTO;

@Entity
@Table(name="shelf")
public class Shelf {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="code")
	private int code;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE,
			 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="warehouse_id")
	private Warehouse warehouse;
	
	@OneToOne(mappedBy="shelf",cascade=CascadeType.MERGE, /*orphanRemoval = true,*/ fetch = FetchType.LAZY) 
	private Form_Product form_product;
	
	public Shelf(ShelfDTO shelfDTO) {
		this.setId(shelfDTO.getId());
		this.setCode(shelfDTO.getCode());		
	}
	
	public Shelf(miniShelfDTO shelfDTO) {
		this.setId(shelfDTO.getId());
		this.setCode(shelfDTO.getCode());		
	}
	
	public Shelf() {}
	
	public Shelf(int code, Warehouse wh) {
		this.code = code;
		this.warehouse = wh;
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
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Override
	public String toString() {
		return "Shelf [code=" + code + ", wh=" + warehouse + "]";
	}
	
}
