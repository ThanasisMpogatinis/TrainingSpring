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
import com.spring.pro03.DTO.ShelfDTO;
import com.spring.pro03.service.ShelfService;

@RestController
@RequestMapping("/api")
public class ShelfController {

	private ShelfService shelfService;
	
	@Autowired
	public ShelfController(ShelfService theShelfController) {
		shelfService = theShelfController;
	}
	
	@GetMapping("/shelf")
	public List<ShelfDTO> findAll(){
		return shelfService.findAll();
	}
	
	@GetMapping("/shelf/{shId}")
	public ShelfDTO getShelf(@PathVariable long shId) {
		
		ShelfDTO theSh = shelfService.findById(shId);
		if (theSh == null) 
			throw new RuntimeException("No shelf found with id - " + shId);		
		
		return theSh;
	}
	
	@PostMapping("/shelf")
	public ShelfDTO addShelf(@RequestBody ShelfDTO theShelfDTO) {
		
		theShelfDTO.setId((long) 0);
		
		ShelfDTO shelfDTO = shelfService.save(theShelfDTO);
		
		return shelfDTO;
	}
	
	@PutMapping("/shelf")
	public ShelfDTO updateShelf(@RequestBody ShelfDTO theSh) {
		
		shelfService.save(theSh);
		
		return theSh;
	}
	
	@DeleteMapping("/shelf/{shId}")
	public String deleteShelf(@PathVariable long shId) {
		
		ShelfDTO tmpSh = shelfService.findById(shId);
		
		if(tmpSh == null)
			throw new RuntimeException("No shelf found with id - " + shId);
		
		shelfService.delete(shId);
		
		return "Deleted shelf id - " + shId;
	}
	
}
