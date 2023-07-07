package com.CODERHOUSE.SegundaPreEntregaGuerra.service;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Client;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.InvoiceDTO;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.ClientRepository;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    public Client postClient(Client client) throws Exception {
        String clientIngresado = validarClient(client);

        if (!clientIngresado.isEmpty()) {

            throw new Exception("El valor del atributo '" + clientIngresado + "' es nulo");
        }
        return clientRepository.save(client);
    }

    public Client getClient(int id) throws Exception {

        Optional<Client> cliente = clientRepository.findById(id);
        List<InvoiceDTO> facturasCliente = invoiceRepository.getInvoicesByClientById(id);
        System.out.println(facturasCliente);
        if(cliente.isEmpty()){
            throw new Exception("Client with id: " + id + ", not found");
        } else {
            System.out.println("cliente" + cliente.get() + cliente.get().getInvoice());
            System.out.println("FacturasDelCliente: " + cliente.get().getInvoice());
            return cliente.get();
        }

    }


    public void updateClient(int id, Client client) throws Exception {
        Optional<Client> clienteExistente = clientRepository.findById(id);
        if(clienteExistente.isEmpty()){
            throw new Exception("Client not exist");
        } else {

            clientRepository.saveAndFlush(client);
        }

    }
    public void deleteClient(Integer clientId){

        Optional<Client> client = clientRepository.findById(clientId);

        System.out.println("Cliente a dar de baja "+ client);
        client.get().setIs_active(false);
        clientRepository.saveAndFlush(client.get());
        System.out.println(client.get());
        System.out.println(clientRepository.findById(clientId));

    }


    public boolean clientExist (int id) throws Exception {
        Optional<Client> cliente = clientRepository.findById(id);
        return cliente.isPresent();
    }

    public String validarClient(Client client) {
        Class<?> claseAux = client.getClass();
        Field[] fields = claseAux.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field);
            //Esto permite la accesibilidad al campo/atrributo para leerlo
            field.setAccessible(true);

            try {
                Object value = field.get(client);
                System.out.println(field);
                if (value == null && field.getName()!= "invoice") {
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