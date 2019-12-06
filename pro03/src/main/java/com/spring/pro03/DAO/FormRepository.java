package com.spring.pro03.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spring.pro03.entity.Form;
import com.spring.pro03.entity.Form_Product;

public interface FormRepository extends JpaRepository<Form, Long> {

	  @Query("select fp from Form_Product fp where fp.shelf.id = ?1")
	  Form_Product findForm_ProductIdByShelfId(long shelf_id);  
	  
}
