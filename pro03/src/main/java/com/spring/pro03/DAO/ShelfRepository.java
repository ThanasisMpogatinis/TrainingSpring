package com.spring.pro03.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pro03.entity.Shelf;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {

}
