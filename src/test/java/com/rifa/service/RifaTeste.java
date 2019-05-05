package com.rifa.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rifa.model.Rifa;
import com.rifa.model.enums.EstadoRifa;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RifaTeste {
	@Autowired
	private RifaService service = new RifaService();
	@Autowired
	private SorteioService serviceSort = new SorteioService();
	@Spy
	private RifaService serviceSpy = new RifaService();
	
	
	@Before
	public void setup() {

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
	public void teste_buscaRifaPorUsuario() {
	List<Rifa> rifas = service.findByUsuario("Joaquim");
	for(Rifa rifa : rifas) {
		System.out.printf(rifa.getNome());
		System.out.println(rifa.getEstado());
		System.out.println(rifa.getUsuarios().size());
	}
	}
}
