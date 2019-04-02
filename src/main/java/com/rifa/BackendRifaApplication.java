package com.rifa;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rifa.model.Rifa;
import com.rifa.model.enums.EstadoRifa;
import com.rifa.repositories.RifaRepository;

@SpringBootApplication
public class BackendRifaApplication implements CommandLineRunner {
@Autowired	
private RifaRepository repositoryRifa;

	public static void main(String[] args) {
		SpringApplication.run(BackendRifaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Rifa rifa1 = new Rifa(null, "AK-LINHAS-VERMELHAS", EstadoRifa.PENDENTE, null);
		Rifa rifa2 = new Rifa(null, "AWP-COLORIDA", EstadoRifa.PENDENTE, null);
		Rifa rifa3 = new Rifa(null, "GLOCK-VENON", EstadoRifa.PENDENTE, null);
		
		repositoryRifa.saveAll(Arrays.asList(rifa1,rifa2,rifa3));
		
		
	}

}
