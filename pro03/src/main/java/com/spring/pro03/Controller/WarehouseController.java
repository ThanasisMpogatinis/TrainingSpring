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

import com.spring.pro03.DTO.FullWarehouseDTO;
import com.spring.pro03.DTO.WarehouseDTO;
import com.spring.pro03.DTO.miniWarehouseDTO;
import com.spring.pro03.service.WarehouseService;

@RestController
@RequestMapping("/api")
public class WarehouseController {

	private WarehouseService warehouseService;
	
	@Autowired
	public WarehouseController(WarehouseService theWarehouseService) {
		warehouseService = theWarehouseService;
	}
	
	@GetMapping("/warehouse")
	public List<WarehouseDTO> findAll(){		
		return warehouseService.findAll();
	}
	
	@GetMapping("/warehouseFull")
	public List<FullWarehouseDTO> findEverything(){		
		return warehouseService.findEverything();
	}
	
	@GetMapping("/warehouse/{whId}")
	public miniWarehouseDTO getWarehouse(@PathVariable long whId) {
		
		miniWarehouseDTO theWh = warehouseService.findById(whId);		
		if(theWh == null)
			throw new RuntimeException("No warehouse found with id - " + whId);
		
		return theWh;
	}
	
	@PostMapping("/warehouse")
	public WarehouseDTO addWarehouse(@RequestBody WarehouseDTO theWarehouseDTO) {
		 
		theWarehouseDTO.setId((long) 0);
		
		return warehouseService.save(theWarehouseDTO);	
	}
	
	@PutMapping("/warehouse")
	public WarehouseDTO updateWarehouse(@RequestBody WarehouseDTO theWh) {
		
		warehouseService.save(theWh);
		
		return theWh;
	}
	
	@DeleteMapping("/warehouse/{whId}")
	public String DeleteWarehouse(@PathVariable long whId) {
		
		miniWarehouseDTO tmpWh = warehouseService.findById(whId);
		
		if(tmpWh == null)
			throw new RuntimeException("No warehouse found with id : " + whId);
		
		warehouseService.delete(whId);
		
		return "Deleted warehouse id - " + whId;		
	}
	
}
