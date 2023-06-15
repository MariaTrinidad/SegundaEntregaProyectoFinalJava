package com.CODERHOUSE.SegundaPreEntregaGuerra.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String code;
    //@Column(nullable = false)
    private Double price;
    //@Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private String title;

    //Getters y setters y override methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", title='" + title + '\'' +
                '}';
    }
}