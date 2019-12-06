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
import com.spring.pro03.DTO.ProductDTO;
import com.spring.pro03.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService theProductController) {
		productService = theProductController;
	}
	
	@GetMapping("/product")
	public List<ProductDTO> findAll(){
		return productService.findAll();
	}
	
	@GetMapping("/product/{prId}")
	public ProductDTO getProduct(@PathVariable long prId) {
		
		ProductDTO thePro = productService.findById(prId);
		if (thePro == null) 
			throw new RuntimeException("No product found with id - " + prId);		
		
		return thePro;
	}
	
	@PostMapping("/product")
	public ProductDTO addProduct(@RequestBody ProductDTO theProductDTO) {
		
		theProductDTO.setId((long) 0);
		
		ProductDTO ProductDTO = productService.save(theProductDTO);
		
		return ProductDTO;
	}
	
	@PutMapping("/product")
	public ProductDTO updateProduct(@RequestBody ProductDTO thePr) {
		
		productService.save(thePr);
		
		return thePr;
	}
	
	@DeleteMapping("/product/{product}")
	public String deleteProduct(@PathVariable long proId) {
		
		ProductDTO tmpPro = productService.findById(proId);
		
		if(tmpPro == null)
			throw new RuntimeException("No product found with id - " + proId);
		
		productService.delete(proId);
		
		return "Deleted product id - " + proId;
	}
	
}
