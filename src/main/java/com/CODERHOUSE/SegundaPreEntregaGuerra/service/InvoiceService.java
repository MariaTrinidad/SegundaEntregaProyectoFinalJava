package com.CODERHOUSE.SegundaPreEntregaGuerra.service;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.*;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.ClientRepository;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.InvoiceRepository;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @Autowired
    private ClientService clientService;
    public InvoiceDTO postInvoice (RequestInvoice requestInvoice) throws Exception {

        //Busca al cliente a través de su id
        Client clientExist = clientService.getClient(requestInvoice.getClient_id());
        if (!clientExist.getIs_active()){
            throw new Exception("El cliente con Id: " + clientExist.getId() + " no esta activo ");
        }
        //Buscamolos productos
        List<Product> productList = productService.getProductsById(requestInvoice.getProduct_list());

        //Calculo del total y validacion de Stock
        double total = 0;
        int i = 0;
           for (RequestProductDetail productosRequesting :
               requestInvoice.getProduct_list()) {
                if(!productList.get(i).getIs_active()){
                    throw new Exception("El producto con Id: " + productosRequesting.getProductId() + " no esta activo ");
                }
               if (productList.get(i).getStock() < requestInvoice.getProduct_list().get(i).getQuantity()) {
                   throw new Exception("No hay stock suficiente del producto con Id: " + productosRequesting.getProductId());
               } else {
                   System.out.println("indice product invoice" + productList.get(i).getId() +" " + i);
                   total += productList.get(i).getPrice() * requestInvoice.getProduct_list().get(i).getQuantity();
                   i++;

               }
            }
        //Si no hubo error pasa a descontar los stocks de los productos
        productService.decreaseStockProducts(requestInvoice.getProduct_list());

        //Instanciamos un objeto invoice
        Invoice invoiceCreated = new Invoice();

        //SET  la fecha del invoice
        invoiceCreated.setCreated_at(new Date().toString());

        //Setteamos al cliente dentro del invoice
        invoiceCreated.setClient(clientExist);

        invoiceCreated.setTotal(total);
        //Guardamos el invoice antes de guardar el detalle
        invoiceCreated = invoiceRepository.save(invoiceCreated);

        //Settamos los invoice_details y los guardamos
        i = 0;
        for (Product productForDetail:
                productList) {
            System.out.println("indice productForDetail invoice" + i);
            InvoiceDetail newInvoice = new InvoiceDetail();
            newInvoice.setPrice(productForDetail.getPrice());
            newInvoice.setInvoice(invoiceCreated);
            newInvoice.setProduct(productForDetail);
            newInvoice.setQuantity(requestInvoice.getProduct_list().get(i).getQuantity());
            invoiceDetailService.saveInvoiceDetail(newInvoice);
            i++;
        }

        //Por último retornamos el DTO
        return new InvoiceDTO(
                invoiceCreated.getId(),
                invoiceCreated.getClient().getId(),
                invoiceCreated.getCreated_at(),
                invoiceCreated.getTotal()
        );
    }

    public List<InvoiceDTO> getInvoicesByClientId (int clientId) throws Exception {
        System.out.println(clientId);
        return invoiceRepository.getInvoicesByClientById(clientId);
    }

    public InvoiceWithDetailsDTO getInvoiceById (int invoice_id) throws Exception {
        Optional<Invoice> invoiceFound = invoiceRepository.findById(invoice_id);
        System.out.println("invoiceFound" + invoiceFound);
        if (invoiceFound.isEmpty()) {
            throw new Exception("Invoice not found");
        }

        List<InvoiceDetailDTO> invoice_details = invoiceDetailService.getInvoiceDetailsByInvoiceId(invoice_id);
        for (InvoiceDetailDTO detalleFactura :
                invoice_details) {
            System.out.println("invoicedetails"+ detalleFactura.toString());
        }
        return new InvoiceWithDetailsDTO(
                invoiceFound.get().getId(),
                invoiceFound.get().getClient().getId(),
                invoiceFound.get().getCreated_at(),
                invoiceFound.get().getTotal(),
                invoice_details
        );

    }
}