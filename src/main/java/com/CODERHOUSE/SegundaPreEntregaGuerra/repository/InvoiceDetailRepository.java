package com.CODERHOUSE.SegundaPreEntregaGuerra.repository;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.InvoiceDetail;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.InvoiceDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {
    @Query("SELECT new com.CODERHOUSE.SegundaPreEntregaGuerra.model.InvoiceDetailDTO(" +
            "p.title, "+
            "p.description, " +
            "p.code, " +
            "d.price, " +
            "d.quantity" +
            ") FROM Invoice i JOIN i.invoiceDetails d JOIN d.product p WHERE i.id = :id")
    List<InvoiceDetailDTO> getInvoiceDetailsByInvoiceId(@Param("id") int invoice_id);
}