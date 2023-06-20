package com.CODERHOUSE.SegundaPreEntregaGuerra.repository;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Client;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCode(String code);


}