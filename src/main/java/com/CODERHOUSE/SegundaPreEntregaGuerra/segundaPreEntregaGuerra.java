package com.CODERHOUSE.SegundaPreEntregaGuerra;

import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Client;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Product;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.ClientRepository;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.InvoiceRepository;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.ProductRepository;
import com.CODERHOUSE.SegundaPreEntregaGuerra.repository.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class segundaPreEntregaGuerra implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(segundaPreEntregaGuerra.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

			System.out.println("Server listening. Access H2 on: http://localhost:8888/h2-console");
	}

}
