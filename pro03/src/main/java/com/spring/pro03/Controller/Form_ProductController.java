package com.spring.pro03.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.pro03.DTO.Form_ProductsDTO;
import com.spring.pro03.DTO.getStockDTO;
import com.spring.pro03.DTO.putStockDTO;
import com.spring.pro03.service.Form_ProductsService;

@RestController
@RequestMapping("/api")
public class Form_ProductController {

	private Form_ProductsService fpService;
	
	@Autowired
	public Form_ProductController(Form_ProductsService theFpController) {
		fpService = theFpController;
	}
	
	@GetMapping("/form_product")
	public List<Form_ProductsDTO> findAll(){
		return fpService.findAll();
	}
	
	@GetMapping("/form_product/{fpId}")
	public Form_ProductsDTO getForm_Products(@PathVariable long fpId) {
		
		Form_ProductsDTO theFp = fpService.findById(fpId);
		if(theFp == null)
			throw new RuntimeException("No Form_Product found with id - " + fpId);
		
		return theFp;
	}
	
	@PostMapping("/form_product")
	public Form_ProductsDTO addForm_Product(@RequestBody Form_ProductsDTO theFp) {		
		
		theFp.setId((long) 0);
		
		fpService.save(theFp);
		
		return theFp;
	}
	
	@PutMapping("/form_product")
	public Form_ProductsDTO updateForm_Product(@RequestBody Form_ProductsDTO theFP) {
		
		fpService.save(theFP);
		
		return theFP;
	}
	
	@DeleteMapping("/form_product/{fpId}")
	public String deleteForm_Product(@PathVariable long fpId) {
		
		Form_ProductsDTO tmpFP = fpService.findById(fpId);
		
		fpService.delete(fpId);
		
		if(tmpFP == null)
			throw new RuntimeException("No form_product with id - " + fpId);
		
		return "Delete form_product with id - " + fpId;
	}
	
	@GetMapping("/stock")
	public List<putStockDTO> getStock(@RequestBody getStockDTO stock){		
		return 	fpService.getStock(stock);
	}

}
