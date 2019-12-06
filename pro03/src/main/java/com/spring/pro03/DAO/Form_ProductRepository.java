package com.spring.pro03.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spring.pro03.entity.Form;
import com.spring.pro03.entity.Form_Product;

public interface Form_ProductRepository extends JpaRepository<Form_Product, Long> {
	
	@Query("select f from Form f where f.date <= ?1")
	List<Form> findFormsByDate (String date);  
	
	@Query("select fp from Form_Product fp where fp.product.id = ?1")
	List<Form_Product> findForm_ProductsByProductId (long product_id);
	
}
