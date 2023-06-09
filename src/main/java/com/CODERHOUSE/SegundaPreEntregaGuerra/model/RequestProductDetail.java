package com.CODERHOUSE.SegundaPreEntregaGuerra.model;

public class RequestProductDetail {
    private int productId;
    private int quantity;

    public RequestProductDetail(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RequestProductDetail{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}