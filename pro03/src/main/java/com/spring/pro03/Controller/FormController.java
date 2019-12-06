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
import com.spring.pro03.DTO.FormDTO;
import com.spring.pro03.service.FormService;

@RestController
@RequestMapping("/api")
public class FormController {

	private FormService formService;
	
	@Autowired
	public FormController(FormService theFormController) {
		formService = theFormController;
	}
	
	@GetMapping("/form")
	public List<FormDTO> findAll(){
		return formService.findAll();
	}
	
	@GetMapping("/form/{fId}")
	public FormDTO getForm(@PathVariable long fId) {
		
		FormDTO theForm = formService.findById(fId);
		if (theForm == null)
			throw new RuntimeException("No form found with id - " + fId);
		
		return theForm;		
	}
	
	@PostMapping("/form")
	public FormDTO addForm(@RequestBody FormDTO theFormDTO) {
				
			theFormDTO.setId((long) 0);
		
			FormDTO formDTO = formService.save(theFormDTO);
		
			return formDTO;
	}			
	
	@PutMapping("/form")
	public FormDTO updateForm(@RequestBody FormDTO theForm) {
		
		formService.save(theForm);
		
		return theForm;
	}
	
	@DeleteMapping("/form/{fId}")
	public String deleteForm(@PathVariable long fId) {
		
		FormDTO tmpForm = formService.findById(fId);
		
		if(tmpForm == null)
			throw new RuntimeException("No form found with id - " + fId);
		
		return "Deleted form with id - " + fId;
	}
						
}
