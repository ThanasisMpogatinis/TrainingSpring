package com.spring.pro03.service;

import java.util.List;
import com.spring.pro03.DTO.FormDTO;

public interface FormService {

	public List<FormDTO> findAll();
	
	public FormDTO findById(long theId);
	
	public FormDTO save(FormDTO theForm);
	
	public void delete(long theId);

}
