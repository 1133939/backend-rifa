package com.rifa.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rifa.model.Rifa;
import com.rifa.model.Usuario;
import com.rifa.model.enums.EstadoRifa;
import com.rifa.repositories.RifaRepository;

@SpringBootTest
public class RifaTeste {
	@Autowired
	private RifaService service = new RifaService();
	@Spy
	private RifaService serviceSpy = new RifaService();
	
	
	@Before
	public void setup() {
		RifaRepository repository = Mockito.mock(RifaRepository.class);
		service.setRepository(repository);
	}
	
	//TESTE DE INTEGRAÇÃO NO METODO FINDBYNAME
	@Test
	@Ignore
	public void buscaPorNome() {
		Rifa rifaBuscada = new Rifa(null,"Rifa1",EstadoRifa.PENDENTE,15);
		service.insert(rifaBuscada);
		List<Rifa> listaRifa = service.findByName(rifaBuscada.getNome());
		List<Rifa> listaRifa2 = service.findByName("rif");
		listaRifa.forEach(rifa->{
				assertTrue(rifa.getNome().contains(rifaBuscada.getNome()));
		});
		listaRifa2.forEach(rifa->{
			assertTrue(rifa.getNome().contains(rifaBuscada.getNome()));
		});
	}
	@Test
	public void todasRifasVendidas() {
		
		Rifa rifa = new Rifa(1,"Rifa1",EstadoRifa.PENDENTE,3);
		Usuario usuario1 = new Usuario(1,"Usuario1","1User","usuario");
		Usuario usuario2 = new Usuario(2,"Usuario2","2User","usuario");
		Usuario usuario3 = new Usuario(3,"Usuario3","3User","usuario");
		rifa.getUsuarios().addAll(Arrays.asList(usuario1,usuario2,usuario3));		
		Mockito.when(service.update(rifa)).thenReturn(rifa);
		Mockito.when(service.update(rifa)).thenReturn(rifa);
		
		
	}
}
