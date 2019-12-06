package com.spring.pro03.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.pro03.DAO.ProductRepository;
import com.spring.pro03.DTO.ProductDTO;
import com.spring.pro03.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository theProductRepository) {
		productRepository = theProductRepository;
	}
	
	@Override
	public List<ProductDTO> findAll() {
		
		List<Product> result = productRepository.findAll();
		List<ProductDTO> proDTO = new ArrayList<>();
		
		for(Product p : result) {
			ProductDTO pDTO = new ProductDTO(p);
			proDTO.add(pDTO);
		}
		return proDTO; 
	}

	@Override
	public ProductDTO findById(long prId) {
		Optional<Product> result = productRepository.findById(prId);
		
		ProductDTO theProduct = new ProductDTO();
		if(result.isPresent()) {
			theProduct = entityToDTO(result.get());
		}
		else {
			throw new RuntimeException("Did not find product with id - " + theProduct);
		}
		return theProduct;
	}

	@Override
	public ProductDTO save(ProductDTO theProduct) {
		
		Product product  = this.dtoToEntity(theProduct);
		
		product = productRepository.save(product);
		
		return new ProductDTO(product);
	}

	@Override
	public void delete(long prId) {
		productRepository.deleteById(prId);
	}
	
	private ProductDTO entityToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setId(product.getId());
		productDTO.setBarcode(product.getBarcode());
		productDTO.setCategory(product.getCategory());
		productDTO.setDescription(product.getDescription());
		productDTO.setM_unit(product.getM_unit());
	
		return productDTO;
	}
	
	private Product dtoToEntity(ProductDTO productDTO) {
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setBarcode(productDTO.getBarcode());
		product.setCategory(productDTO.getCategory());
		product.setDescription(productDTO.getDescription());
		product.setM_unit(productDTO.getM_unit());
				
		return product;
	}

}
