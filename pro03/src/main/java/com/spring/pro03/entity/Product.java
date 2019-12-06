package com.spring.pro03.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.spring.pro03.DTO.ProductDTO;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="barcode")
	private String barcode;
	
	@Column(name="category")
	private String category;

	@Column(name="description")
	private String description;
	
	@Column(name="m_unit")
	private String m_unit;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL)
	private List<Form_Product> form_product;
	
	public Product() {}
	
	public Product(ProductDTO productDTO) {
		this.setId(productDTO.getId());
		this.setBarcode(productDTO.getBarcode());
		this.setCategory(productDTO.getCategory());
		this.setDescription(productDTO.getDescription());
	}
	
	public Product(String barcode,String category, String description, String m_unit) {
		this.barcode = barcode;
		this.category = category;
		this.description = description;
		this.m_unit = m_unit;
	}
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getM_unit() {
		return m_unit;
	}
	public void setM_unit(String m_unit) {
		this.m_unit = m_unit;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", barcode=" + barcode + ", category=" + category + ", description=" + description
				+ ", m_unit=" + m_unit + "]";
	}

}
