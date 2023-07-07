package com.CODERHOUSE.SegundaPreEntregaGuerra.controller;

import com.CODERHOUSE.SegundaPreEntregaGuerra.middleware.ResponseHandler;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Client;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Product;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.RequestProductDetail;
import com.CODERHOUSE.SegundaPreEntregaGuerra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.jar.Attributes;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //Create
    @PostMapping
    public ResponseEntity<Object> postProduct(@RequestBody Product product) {

        try {

            Product productSaved = productService.postProduct(product);

            return ResponseHandler.generateResponse(
                    "Data retrieved successfully",
                    HttpStatus.OK,
                    productSaved
            );
        } catch (Exception e) {

            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }



    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getProduct(@PathVariable() int id) {
        try {
            System.out.println(id);
            Product productFound = productService.getProduct(id);
            System.out.println(productFound);
            return ResponseHandler.generateResponse(
                    "Client get successfully",
                    HttpStatus.OK,
                    productFound
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }


    }

    @GetMapping(path = "/ProductsDetails")
    public ResponseEntity<Object> getProduct(@RequestBody List<RequestProductDetail> productListId) {
        try {
            System.out.println(productListId);
             List <Product> productsFound = productService.getProductsById(productListId);
            return ResponseHandler.generateResponse(
                    "Client get successfully",
                    HttpStatus.OK,
                    productsFound
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }



    }
    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable() int id, @RequestBody Product product){
        try {
            System.out.println(product);
            System.out.println(id);
            productService.updateProduct(id, product);

            return ResponseHandler.generateResponse(
                    "Product Update successfully",
                    HttpStatus.OK,
                    product
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
    @DeleteMapping(path = "/deleteProduct/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable() Integer id){
        try {
            System.out.println("Producto a borrar" + productService.getProduct(id));
            productService.deleteProduct(id);
            System.out.println(productService.getProduct(id));

            return ResponseHandler.generateResponse(
                    "Product delete successfully",
                    HttpStatus.OK,
                    productService.getProduct(id)
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

}