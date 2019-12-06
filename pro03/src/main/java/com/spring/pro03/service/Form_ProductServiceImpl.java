package com.spring.pro03.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.pro03.DAO.FormRepository;
import com.spring.pro03.DAO.Form_ProductRepository;
import com.spring.pro03.DAO.ProductRepository;
import com.spring.pro03.DAO.ShelfRepository;
import com.spring.pro03.DTO.Form_ProductsDTO;
import com.spring.pro03.DTO.getStockDTO;
import com.spring.pro03.DTO.putStockDTO;
import com.spring.pro03.entity.Form;
import com.spring.pro03.entity.Form_Product;
import com.spring.pro03.entity.Product;
import com.spring.pro03.entity.Shelf;

@Service
public class Form_ProductServiceImpl implements Form_ProductsService {

	private Form_ProductRepository f_pRepository;
	
	@Autowired
	private ShelfRepository shelfRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FormRepository formRepository;
	
	@Autowired
	public Form_ProductServiceImpl(Form_ProductRepository theF_pRepository) {
		f_pRepository = theF_pRepository;
	}
	
	@Override
	public List<Form_ProductsDTO> findAll() {
		
		List<Form_Product> result = f_pRepository.findAll();
		List<Form_ProductsDTO> fpDTO = new ArrayList<>();
		
		for(Form_Product f : result) {
			Form_ProductsDTO DTO = new Form_ProductsDTO(f);
			fpDTO.add(DTO);
		}
		return fpDTO;
	}

	@Override
	public Form_ProductsDTO findById(long theId) {

		Optional<Form_Product> result = f_pRepository.findById(theId);
		
		if(result.isPresent()) {
			Form_ProductsDTO theForm = new Form_ProductsDTO(result.get());
			return theForm;
		}
		return null;
	}

	@Override
	public Form_ProductsDTO save(Form_ProductsDTO theForm) {
		
		Form form = formRepository.findById(theForm.getForm_id()).get();
		if(form.getType().equals("export")) {
			return null;
		}
		
		Form_Product formp = this.dtoToEntity(theForm);
		
		formp = f_pRepository.save(formp);		
		
		return new Form_ProductsDTO(formp);
	}

	@Override
	public void delete(long theId) {
		f_pRepository.deleteById(theId);
	}
	
	@Override
	public List<putStockDTO> getStock(getStockDTO stock){
		
		List<Form_Product> fpList = f_pRepository.findForm_ProductsByProductId(stock.getProduct_id());
		List<Form> fList = f_pRepository.findFormsByDate(stock.getDate());
		
		List<putStockDTO> stockList = new ArrayList<>();
		
		for(Form_Product fp : fpList) {
			for(Form f : fList) {	
				if(f.getId() == fp.getForm().getId()) {
					putStockDTO s = new putStockDTO();
					s.setShelf_id(fp.getShelf().getId());
					s.setQuantity(fp.getQuantity());
					stockList.add(s);
				}
			}
		}
				
		return stockList;
	}
	
	private Form_Product dtoToEntity(Form_ProductsDTO form_ProductDTO) {
		
		Shelf shelf = this.shelfRepository.findById(form_ProductDTO.getShelf_id()).get();
		Product product = this.productRepository.findById(form_ProductDTO.getProduct_id()).get();
		Form form = this.formRepository.findById(form_ProductDTO.getForm_id()).get();
		
		Form_Product form_Product = new Form_Product();
		
		form_Product.setId(form_ProductDTO.getId());
		form_Product.setQuantity(form_ProductDTO.getQuantity());
		form_Product.setShelf(shelf);
		form_Product.setProduct(product);	
		form_Product.setForm(form);
		
		return form_Product;
	}

}
