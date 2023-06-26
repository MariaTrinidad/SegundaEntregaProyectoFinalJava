package com.CODERHOUSE.SegundaPreEntregaGuerra.service;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Client;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Product;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client postClient(Client client) throws Exception {
        return clientRepository.save(client);
    }

    public Client getClient(int id) throws Exception {
        Optional<Client> cliente = clientRepository.findById(id);
        if(cliente.isEmpty()){
            throw new Exception("Client with id: " + id + ", not found");
        } else {
            return cliente.get();
        }
    }
    public void deleteClient(Integer clientId){

        Optional<Client> cliente = clientRepository.findById(clientId);
        System.out.println("Cliente a dar de baja "+ cliente);
        cliente.get().setIs_active(false);
        clientRepository.saveAndFlush(cliente.get());
        System.out.println(cliente.get());


    }
    public boolean clientExist (int id) throws Exception {
        Optional<Client> cliente = clientRepository.findById(id);
        return cliente.isPresent();
    }
}