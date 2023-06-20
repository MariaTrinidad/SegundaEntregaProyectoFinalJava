package com.CODERHOUSE.SegundaPreEntregaGuerra.service;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Product;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.RequestProductDetail;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product postProduct(Product product) throws Exception {

        return productRepository.save(product);
    }

    public  List<Product> getProductsById(List<RequestProductDetail> productListId) throws Exception {
        List<Product> productList = new ArrayList<>();
        for (RequestProductDetail requestProduct:
                productListId) {
            Optional<Product> productFound = productRepository.findById(requestProduct.getProductId());
            if (productFound.isEmpty()){
                throw new Exception("Product with id: " + requestProduct.getProductId() + " not found.");
            }
            productList.add(productFound.get());
        }
        return productList;
    }

    public Product getProduct(int id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new Exception("Client with id: " + id + ", not found");
        } else {
            return product.get();
        }
    }/*
    public List<Product> getProductsByInvoiceId(int id) throws Exception{
        Optional<Product> product = productRepository.findById((id);
    }*/
}