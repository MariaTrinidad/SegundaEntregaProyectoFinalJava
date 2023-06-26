package com.CODERHOUSE.SegundaPreEntregaGuerra.service;

import com.CODERHOUSE.SegundaPreEntregaGuerra.middleware.ResponseHandler;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Client;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Product;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.RequestProductDetail;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product postProduct(Product product) throws Exception {
        String productoIngresado = validarProduct(product);

        if (!productoIngresado.isEmpty()) {

            throw new Exception("El valor del atributo '" + productoIngresado + "' es nulo");
        }
        return productRepository.save(product);
    }

    public  List<Product> getProductsById(List<RequestProductDetail> productListId) throws Exception {
        List<Product> productList = new ArrayList<>();

        for (RequestProductDetail requestProduct:
                productListId) {
            System.out.println(requestProduct.getClass());
            Optional<Product> productFound = productRepository.findById(requestProduct.getProductId());
            if (productFound.isEmpty()){
                throw new Exception("Product with id: " + requestProduct.getProductId() + " not found.");
            }
            productList.add(productFound.get());
        }
        System.out.println("productList "  + productList);
        return productList;
    }

    public Product getProduct(int id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new Exception("Product with id: " + id + ", not found");
        } else {
            return product.get();
        }
    }

    public void decreaseStockProducts(List<RequestProductDetail>products ){
        for (RequestProductDetail product:
             products) {
               Optional<Product> porductoAModificar = productRepository.findById(product.getProductId());
               int stockAnterior = porductoAModificar.get().getStock();
               int stockADescontar = product.getQuantity();
               porductoAModificar.get().setStock(stockAnterior-stockADescontar);
        }
    }

    public void updateProduct(Product product){

        productRepository.saveAndFlush(product);


    }
    public void deleteProduct(Integer productId){

            Optional<Product> producto = productRepository.findById(productId);
            System.out.println("producto a inactivar "+ producto);
            producto.get().setIs_active(false);
            productRepository.saveAndFlush(producto.get());
            System.out.println(producto.get());
            System.out.println(productRepository.findById(productId));

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
                System.out.println(field);
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

}