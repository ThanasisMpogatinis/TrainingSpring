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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.spring.pro03.DTO.Form_ProductsDTO;

@Entity
@Table(name="form_products")
public class Form_Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@ManyToOne(cascade= {CascadeType.ALL/*CascadeType.DETACH, CascadeType.MERGE,
			 CascadeType.PERSIST, CascadeType.REFRESH*/})
	@JoinColumn(name="form_id")
	private Form form;
	
	@OneToOne(cascade=CascadeType.MERGE, /*orphanRemoval = true,*/ fetch = FetchType.LAZY)
	@JoinColumn(name="shelf_id")
	private Shelf shelf;
			
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne(cascade= CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	public Form_Product() {}
	
	public Form_Product(Shelf shelf, long product_id, Form form, Product product, int quantity) {
		this.shelf = shelf;
		this.form = form;
		this.quantity = quantity;
		this.product = product;
	}
	
	public Form_Product(Form_ProductsDTO fp) {
		this.setId(fp.getId());
		this.setQuantity(fp.getQuantity());
		
		Shelf s = new Shelf();
		s.setId(fp.getShelf_id());
		this.setShelf(s);
		
		Product product = new Product();
		product.setId(fp.getProduct_id());
		this.setProduct(product);
	}

	public long getId() {
		return id;
	}
	public void setId(long id) { 
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public Shelf getShelf() {
		return shelf;
	}
	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
		
}
