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
import com.spring.pro03.DTO.FormDTO;
import com.spring.pro03.DTO.Form_ProductsDTO;
import com.spring.pro03.entity.Form;
import com.spring.pro03.entity.Form_Product;
import com.spring.pro03.entity.Product;
import com.spring.pro03.entity.Shelf;

@Service
public class FormServiceImpl implements FormService {

	private FormRepository formRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ShelfRepository shelfRepository;
	
	@Autowired
	private Form_ProductRepository fpRepository;
	
	@Autowired
	public FormServiceImpl(FormRepository theFormRepository) {
		formRepository = theFormRepository;
	}
	 
	@Override
	public List<FormDTO> findAll() {
		
		List<Form> result = formRepository.findAll();
		List<FormDTO> formDTO = new ArrayList<>();
		
		for(Form f : result) {
			FormDTO fDTO = new FormDTO(f);
			formDTO.add(fDTO);
		}
		return formDTO;
	}

	@Override
	public FormDTO findById(long theId) {
		
		Optional<Form> result = formRepository.findById(theId);
		
		if(result.isPresent()) {
			FormDTO theForm = new FormDTO(result.get());
//			theForm = entityToDTO(result.get());
			return theForm;
		}
		return null;
	}

	@Override
	public FormDTO save(FormDTO theForm) {
		
		if( theForm.getType().equals("import") ) {
			
			Form form = this.dtoToEntity(theForm);
		
			form = formRepository.save(form);
			
			return new FormDTO(form);
		}
		else if(theForm.getType().equals("export") ){
			
			int givenQuantity, baseQuantity, newQuantity;
			long givenShelf;
			
			List<Form_ProductsDTO> tmpFp = theForm.getForm_products();
			for(Form_ProductsDTO fp : tmpFp) {
				
				givenQuantity = fp.getQuantity();
				givenShelf = fp.getShelf_id();
				
				Form_Product f_p = formRepository.findForm_ProductIdByShelfId(fp.getShelf_id());
				
				if(f_p.getShelf().getId() != givenShelf) {
					throw new RuntimeException("There is no such product in self : " + givenShelf);
				}

				baseQuantity = f_p.getQuantity();				
				if(givenQuantity > baseQuantity) {
					throw new RuntimeException("There are less products in self : " + givenShelf);
				}
				else {
					newQuantity = baseQuantity - givenQuantity;
					
					f_p.setQuantity(newQuantity);
					
					if (newQuantity == 0) {
						fpRepository.delete(f_p);
					}					
					
					Form form = this.dtoToEntity(theForm);
					
					form.setForm_products(null);
					
					form = formRepository.save(form);
					
					return new FormDTO(form);
				}	
			}
		}
		else
			throw new RuntimeException("type must be : import or export");
		
		return null;
	}

	@Override
	public void delete(long theId) {
		formRepository.deleteById(theId);
	}
	
	private Form dtoToEntity(FormDTO formDTO) {
		
		Form form = new Form();
		
		form.setId(formDTO.getId());
		form.setDescription(formDTO.getDescription());
		form.setType(formDTO.getType());
		form.setPerson_name(formDTO.getPerson_name());
		form.setDate(formDTO.getDate());
		
		List<Form_Product> fpList = new ArrayList<>();
		
		for(Form_ProductsDTO fp : formDTO.getForm_products()) {
			
			Shelf shelf = this.shelfRepository.findById(fp.getShelf_id()).get();
			Product product = this.productRepository.findById(fp.getProduct_id()).get();
			
			Form_Product form_Product = new Form_Product();
			
			form_Product.setId(fp.getId());
			form_Product.setQuantity(fp.getQuantity());
			form_Product.setShelf(shelf);
			form_Product.setProduct(product);	
			form_Product.setForm(form);
			
			fpList.add(form_Product);
		}
		form.setForm_products(fpList);
		
		return form;
	}
//	private FormDTO entityToDTO(Form form) {
//		FormDTO formDTO = new FormDTO();
//		
//		formDTO.setId(form.getId());
//		formDTO.setDescription(form.getDescription());
//		formDTO.setPerson_name(form.getPerson_name());
//		
//		
//		List<Shelf> shelves = product.getShelves();
//		List<miniShelfDTO> miniShelves = new ArrayList<>();
//		
//		for(Shelf s : shelves) {
//			miniShelfDTO sDTO = new miniShelfDTO(s);
//			miniShelves.add(sDTO);
//		}
//		productDTO.setShelves(miniShelves);
//		
//		return productDTO;
//	}
	
}
