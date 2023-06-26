package com.CODERHOUSE.SegundaPreEntregaGuerra.service;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Client;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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


    public void updateClient(Client client){
        Class<?> claseAux = client.getClass();
        Field[] fields = claseAux.getDeclaredFields();

        clientRepository.saveAndFlush(client);


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
}