package com.spring.pro03.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.pro03.DAO.ShelfRepository;
import com.spring.pro03.DAO.WarehouseRepository;
import com.spring.pro03.DTO.ShelfDTO;
import com.spring.pro03.entity.Shelf;
import com.spring.pro03.entity.Warehouse;

@Service
public class ShelfServiceImpl implements ShelfService {

	private ShelfRepository shelfRepository;
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Autowired
	public ShelfServiceImpl(ShelfRepository theShelfRepository) {
		shelfRepository = theShelfRepository;
	}
	
	@Override
	public List<ShelfDTO> findAll() {
		
		List<Shelf> result = shelfRepository.findAll();
		List<ShelfDTO> shelfDTO = new ArrayList<>();
		
		for(Shelf s : result) {
			ShelfDTO sDTO = new ShelfDTO(s);
			shelfDTO.add(sDTO);
		}
		return shelfDTO;
	}

	@Override
	public ShelfDTO findById(long theId) {
		Optional<Shelf> result = shelfRepository.findById((long) theId);
		
		ShelfDTO shelfDTO = new ShelfDTO();
		if(result.isPresent()) {
			shelfDTO = this.entityToDTO(result.get());
		}
		else {
			throw new RuntimeException("Did not find shelf id - " + theId);
		}
		return shelfDTO;
	}

	@Override
	public ShelfDTO save(ShelfDTO shelfDTO) {
		
		Shelf shelf = this.dtoToEntity(shelfDTO);
		
		shelf = shelfRepository.save(shelf);
		
		return new ShelfDTO(shelf);
	}

	@Override
	public void delete(long theId) {
		shelfRepository.deleteById(theId);
	}

//	private miniShelfDTO miniEntityToDTO(Shelf shelf) {
//		miniShelfDTO shelfDTO = new miniShelfDTO();
//		
//		shelfDTO.setId(shelf.getId());
//		shelfDTO.setCode(shelf.getCode());
//		
//		Long warehouseId = shelf.getWarehouse().getId();
//		String warehouseDescription = shelf.getWarehouse().getDescription();
//		WarehouseDTO wDTO = new WarehouseDTO();
//		wDTO.setId(warehouseId);
//		wDTO.setDescription(warehouseDescription);
//		wDTO.setShelves(null);
//		
//		shelfDTO.setWarehouse(shelf.getWarehouse());
//	
//		return shelfDTO;
//	}
	
	private ShelfDTO entityToDTO(Shelf shelf) {
		ShelfDTO shelfDTO = new ShelfDTO();
		
		shelfDTO.setId(shelf.getId());
		shelfDTO.setCode(shelf.getCode());
		shelfDTO.setWarehouse_id(shelf.getWarehouse().getId());
	
		return shelfDTO;
	}
	
	private Shelf dtoToEntity(ShelfDTO shelfDTO) {
		
		Shelf shelf = new Shelf();
		shelf.setId(shelfDTO.getId());
		shelf.setCode(shelfDTO.getCode());
		
		Long warehouseId = shelfDTO.getWarehouse_id();
		
		Warehouse warehouse = this.warehouseRepository.findById(warehouseId).get();
		
		shelf.setWarehouse(warehouse);
		
		return shelf;
	}
	
}
