package com.spring.pro03.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.spring.pro03.DTO.FormDTO;
import com.spring.pro03.DTO.Form_ProductsDTO;

@Entity
@Table(name="form")
public class Form {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="type")
	private String type; //(import / export)
	
	@Column(name="person_name")
	private String person_name;
	
	@Column(name="date")
	private String date;
	
	@OneToMany(mappedBy="form", cascade={CascadeType.DETACH, CascadeType.MERGE,
									CascadeType.REFRESH, CascadeType.PERSIST})
	private List<Form_Product> form_products;
	
	public Form() {}
	
	public Form(String description, String type, String person_name, String date, List<Form_Product> form_products) {
		this.description = description;
		this.type = type;
		this.person_name = person_name;
		this.date = date;
		this.form_products = form_products;
	}
	
	public Form(FormDTO formDTO) {
		this.setId(formDTO.getId());
		this.setDescription(formDTO.getDescription());
		this.setType(formDTO.getType());
		this.setPerson_name(formDTO.getPerson_name());
		this.setDate(formDTO.getDate());
		
		List<Form_ProductsDTO> fpresult = formDTO.getForm_products();
		List<Form_Product> fp = new ArrayList<>();
		
		for(Form_ProductsDTO i : fpresult) {
			Form_Product f_p = new Form_Product(i);
			
			Product product = new Product();
			product.setId(i.getProduct_id());
			f_p.setProduct(product);
			
			Shelf shelf = new Shelf();
			shelf.setId(i.getShelf_id());
			f_p.setShelf(shelf);

			fp.add(f_p);
		}
		this.setForm_products(fp);
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Form_Product> getForm_products() {
		return form_products;
	}
	public void setForm_products(List<Form_Product> form_products) {
		this.form_products = form_products;
	}	

}
	

