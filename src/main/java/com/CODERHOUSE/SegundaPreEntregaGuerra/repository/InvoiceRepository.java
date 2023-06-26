package com.CODERHOUSE.SegundaPreEntregaGuerra.repository;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Invoice;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.InvoiceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    //Para un inner join, generamos una nueva entidad DTO, que es muy similar a la entidad
    //solamente que no instancia nuevos registros, sino que simplemente genera un objeto de transferencia de datos
    //DTO
    @Query("SELECT new com.CODERHOUSE.SegundaPreEntregaGuerra.model.InvoiceDTO(" +
            "i.id invoice_id, " +
            "i.id client_id, " +
            "i.created_at, " +
            "i.total" +
            ") FROM Invoice i INNER JOIN i.client c WHERE c.id = :id")
    List<InvoiceDTO> getInvoicesByClientById(@Param("id") int id);

}