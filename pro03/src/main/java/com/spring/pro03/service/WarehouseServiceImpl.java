package com.spring.pro03.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.pro03.DAO.WarehouseRepository;
import com.spring.pro03.DTO.FullWarehouseDTO;
import com.spring.pro03.DTO.WarehouseDTO;
import com.spring.pro03.DTO.miniShelfDTO;
import com.spring.pro03.DTO.miniWarehouseDTO;
import com.spring.pro03.entity.Shelf;
import com.spring.pro03.entity.Warehouse;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	private WarehouseRepository warehouseRepository;
	
	@Autowired
	public WarehouseServiceImpl(WarehouseRepository theWarehouseRepository) {
		warehouseRepository = theWarehouseRepository;
	}
	
	@Override
	public List<WarehouseDTO> findAll() {
	
		List<Warehouse> result = warehouseRepository.findAll();		
		List<WarehouseDTO> warehouseDTO = new ArrayList<>();
		
		for(Warehouse w : result) {		
			WarehouseDTO whDTO = new WarehouseDTO(w);		
			warehouseDTO.add(whDTO);	
		}
		return warehouseDTO;
	}
	
	@Override
	public List<FullWarehouseDTO> findEverything() {
		
		List<Warehouse> result = warehouseRepository.findAll();		
		List<FullWarehouseDTO> fwarehouseDTO = new ArrayList<>();
		
		for(Warehouse w : result) {		
			FullWarehouseDTO whDTO = new FullWarehouseDTO(w);		
			fwarehouseDTO.add(whDTO);	
		}
		return fwarehouseDTO;
	}
	
	@Override
	public miniWarehouseDTO findById(long theId) {
		Optional<Warehouse> result = warehouseRepository.findById(theId);
		
		miniWarehouseDTO warehouseDTO = new miniWarehouseDTO();
		
		if(result.isPresent()) {
			warehouseDTO = this.entityToMiniDTO(result.get());
		}
		else {
			throw new RuntimeException("Did not find warehouse id - " + theId);
		}
		return warehouseDTO;
	}
	
	@Override
	public WarehouseDTO save(WarehouseDTO warehouseDTO) {
				
		Warehouse warehouse = new Warehouse();
		warehouse.setId(warehouseDTO.getId());
		warehouse.setDescription(warehouseDTO.getDescription());
		
		List<miniShelfDTO> shDTO = warehouseDTO.getShelves();
		List<Shelf> shelves = new ArrayList<>();
		
		for(miniShelfDTO s : shDTO) {
			Shelf shelf = new Shelf(s);
			shelf.setWarehouse(warehouse);
			shelves.add(shelf);
		}
		warehouse.setShelves(shelves);
		warehouse = warehouseRepository.save(warehouse);
		
		return new WarehouseDTO(warehouse);
	}
	
	@Override
	public void delete(long theId) {
		
		warehouseRepository.deleteById(theId);
	}
	
//	private WarehouseDTO entityToDTO(Warehouse warehouse) {
//		WarehouseDTO warehouseDTO = new WarehouseDTO();
//		
//		warehouseDTO.setId(warehouse.getId());
//		warehouseDTO.setDescription(warehouse.getDescription());
//		
//		List<Shelf> shelfList = warehouse.getShelves();
//		List<ShelfDTO> shelfDTOList = new ArrayList<ShelfDTO>();
//		
//		for(Shelf s : shelfList) {
//			ShelfDTO mshDTO = new ShelfDTO(s);
//			mshDTO.setId(s.getId());
//			mshDTO.setCode(s.getCode());
//			shelfDTOList.add(mshDTO);
//		}
//		warehouseDTO.setShelves(shelfDTOList);
//		
//		return warehouseDTO;
//	}
	
	private miniWarehouseDTO entityToMiniDTO(Warehouse warehouse) {
		miniWarehouseDTO warehouseDTO = new miniWarehouseDTO();
		
		warehouseDTO.setId(warehouse.getId());
		warehouseDTO.setDescription(warehouse.getDescription());
		
		List<Shelf> shelfList = warehouse.getShelves();
		List<miniShelfDTO> shelfDTOList = new ArrayList<miniShelfDTO>();
		
		for(Shelf s : shelfList) {
			miniShelfDTO mshDTO = new miniShelfDTO(s);
			mshDTO.setId(s.getId());
			mshDTO.setCode(s.getCode());
			shelfDTOList.add(mshDTO);
		}
		warehouseDTO.setShelves(shelfDTOList);
		
		return warehouseDTO;
	}
	
//	private Warehouse dtoToEntity(WarehouseDTO warehouseDTO) {
//		
//		Warehouse warehouse = new Warehouse();
//		
//		warehouse.setId(warehouseDTO.getId());
//		warehouse.setDescription(warehouse.getDescription());
//		
//		List<miniShelfDTO> shelfDTOs = warehouseDTO.getShelves();
//		List<Shelf> shelves = new ArrayList<>();
//		
//		for(miniShelfDTO sh : shelfDTOs) {
//			Shelf newShelf = new Shelf();
//			
//			newShelf.setId(sh.getId());
//			newShelf.setCode(sh.getCode());
//			
//			shelves.add(newShelf);
//		}
//		
//		warehouse.setShelves(shelves);
//		
//		shelf.setId(shelfDTO.getId());
//		shelf.setCode(shelfDTO.getCode());
//		
//		Long warehouseId = shelfDTO.getWarehouse_id();
//		
//		Warehouse warehouse = this.warehouseRepository.findById(warehouseId).get();
//		
//		shelf.setWarehouse(warehouse);
//		
//		return warehouse;
//	}
	
}
