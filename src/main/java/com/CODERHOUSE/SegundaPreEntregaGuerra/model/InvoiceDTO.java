package com.CODERHOUSE.SegundaPreEntregaGuerra.model;

public class InvoiceDTO {
    private int invoice_id;
    private int client_id;
    private String created_at;
    private double total;

    public InvoiceDTO(int invoice_id, int client_id, String created_at, double total) {
        this.invoice_id = invoice_id;
        this.client_id = client_id;
        this.created_at = created_at;
        this.total = total;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "invoice_id=" + invoice_id +
                ", client_id=" + client_id +
                ", created_at='" + created_at + '\'' +
                ", total=" + total +
                '}';
    }
}