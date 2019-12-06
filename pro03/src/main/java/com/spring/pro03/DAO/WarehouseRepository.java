package com.spring.pro03.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pro03.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
