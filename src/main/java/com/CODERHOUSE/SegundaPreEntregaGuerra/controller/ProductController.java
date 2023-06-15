package com.CODERHOUSE.SegundaPreEntregaGuerra.controller;

import com.CODERHOUSE.SegundaPreEntregaGuerra.middleware.ResponseHandler;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Product;
import com.CODERHOUSE.SegundaPreEntregaGuerra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.jar.Attributes;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //Create
    @PostMapping
    public ResponseEntity<Object> postProduct(@RequestBody Product product) {
        System.out.println(product);
        try {
            System.out.println(product);

            String productoIngresado = validarProduct(product);

            if (!productoIngresado.isEmpty()) {
                return ResponseHandler.generateResponse(
                       "El valor del atributo '" + productoIngresado + "' es nulo",
                        HttpStatus.BAD_REQUEST,
                        null
                );
            }
            //Agregar que si es otro tipo el tipo


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

    public String validarProduct(Product product) {
         Class<?> claseAux = product.getClass();
        Field[] fields = claseAux.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field);
            //Esto permite la accesibilidad al campo/atrributo para leerlo
            field.setAccessible(true);

            try {
                Object value = field.get(product);
                if (value == null) {
                    System.out.println(field.getName());
                    return field.getName();
                }
            } catch (IllegalAccessException e) {
                System.out.println("entro al catch");
                e.printStackTrace();
            }
        }

        return "";
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getProduct(@PathVariable() int id) {
        try {
            System.out.println(id);
            Product productFound = productService.getProduct(id);
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
 /*
    @GetMapping(path = "/getProductsByInvoice_Id/{invoice_id}")
    public ResponseEntity<Object> getProductsByInvoiceId (@PathVariable int invoice_id){
        try {
            List<Product> data = productService.getProductsByInvoiceId(clientId);
            return ResponseHandler.generateResponse(
                    "Get Invoices by Client id successful",
                    HttpStatus.OK,
                    data
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }*/
}