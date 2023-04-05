package com.example.demo.repository;

import com.example.demo.model.CoffeeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoffeeShopRepository
        extends JpaRepository<CoffeeShop, Long>, JpaSpecificationExecutor<CoffeeShop> {
}
