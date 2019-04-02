package com.rifa;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rifa.model.Rifa;
import com.rifa.model.Sorteio;
import com.rifa.model.Usuario;
import com.rifa.model.enums.EstadoRifa;
import com.rifa.repositories.RifaRepository;
import com.rifa.repositories.SorteioRepository;
import com.rifa.repositories.UsuarioRepository;

@SpringBootApplication
public class BackendRifaApplication implements CommandLineRunner {
@Autowired	
private RifaRepository repositoryRifa;
@Autowired	
private SorteioRepository repositorySorteio;
@Autowired	
private UsuarioRepository repositoryUsuario;

	public static void main(String[] args) {
		SpringApplication.run(BackendRifaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Rifa rifa1 = new Rifa(null, "AK-LINHAS-VERMELHAS", EstadoRifa.PENDENTE, null);
		Rifa rifa2 = new Rifa(null, "AWP-COLORIDA", EstadoRifa.PENDENTE, null);
		Rifa rifa3 = new Rifa(null, "GLOCK-VENON", EstadoRifa.PENDENTE, null);
		
		Usuario usuario1 = new Usuario(null, "Matheus Campelo", "Matheus","Campelo");
		Usuario usuario2 = new Usuario(null, "Joaquim Parrolho", "Joaquim","Parrolho");
		Usuario usuario3 = new Usuario(null, "Kilombo Karakata", "Kilombo","Karakata");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Sorteio sorteio1 = new Sorteio(null, usuario1, rifa1, sdf.parse("02/04/2019 02:10"));
		usuario1.getRifas().addAll(Arrays.asList(rifa1, rifa3));
		usuario2.getRifas().addAll(Arrays.asList(rifa1));
		usuario3.getRifas().addAll(Arrays.asList(rifa1));
		rifa1.getUsuarios().addAll(Arrays.asList(usuario1,usuario2,usuario3));
		repositoryUsuario.saveAll(Arrays.asList(usuario1,usuario2,usuario3));
		repositoryRifa.saveAll(Arrays.asList(rifa1,rifa2,rifa3));
		repositorySorteio.saveAll(Arrays.asList(sorteio1));
		
		
		
	}

}
