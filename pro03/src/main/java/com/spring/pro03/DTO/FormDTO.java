package com.spring.pro03.DTO;

import java.util.ArrayList;
import java.util.List;
import com.spring.pro03.entity.Form;
import com.spring.pro03.entity.Form_Product;

public class FormDTO {

	private long id;
	private String description;
	private String type;
	private String person_name;
	private String date;
	private List<Form_ProductsDTO> form_products;
	
	public FormDTO(){}
	
	public FormDTO(String description, String type, String person_name, String date, List<Form_ProductsDTO> form_products) {
		this.description = description;
		this.type = type;
		this.person_name = person_name;
		this.date = date;
		this.form_products = form_products;
	}
	
	public FormDTO(Form form) {
		this.setId(form.getId());
		this.setDescription(form.getDescription());
		this.setType(form.getType());
		this.setPerson_name(form.getPerson_name());
		this.setDate(form.getDate());
		
		List<Form_Product> formes = form.getForm_products();
		if (formes == null) {
			formes = new ArrayList<>();
		}
		List<Form_ProductsDTO> f_pDTO = new ArrayList<>();
				
		for(Form_Product fp : formes) {
			Form_ProductsDTO fpDTO = new Form_ProductsDTO();
			fpDTO.setId(fp.getId());
			fpDTO.setForm_id(fp.getForm().getId());
			fpDTO.setShelf_id(fp.getShelf().getId());
			fpDTO.setProduct_id(fp.getProduct().getId());
			fpDTO.setQuantity(fp.getQuantity());
			f_pDTO.add(fpDTO);			
		}
		this.setForm_products(f_pDTO);
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
	public List<Form_ProductsDTO> getForm_products() {
		return form_products;
	}
	public void setForm_products(List<Form_ProductsDTO> form_products) {
		this.form_products = form_products;
	}
		
}
