package com.rifa.resources;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rifa.exceptions.UsuarioSemTicketException;
import com.rifa.model.Rifa;
import com.rifa.model.RifaDTO;
import com.rifa.model.Usuario;
import com.rifa.service.RifaService;
@CrossOrigin
@RestController
@RequestMapping(value="/rifas")
public class RifaResources implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Autowired	
private RifaService service;
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll(){
		List<Rifa> lista = service.findAll();
		List <RifaDTO> listDTO = lista.stream().map(rifas -> new RifaDTO(rifas)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	@RequestMapping(method=RequestMethod.GET, value="/buscaNome/{nome}")
	public ResponseEntity<?> findByName(@PathVariable String nome){
		List<Rifa> lista = service.findByName(nome);
		return ResponseEntity.ok().body(lista);
	}
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id){
		Rifa rifa = service.find(id);
		return ResponseEntity.ok().body(rifa);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Rifa rifa){
		rifa = service.insert(rifa);
		return ResponseEntity.created(null).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> update(@RequestBody Rifa rifa, @PathVariable Integer id){
		rifa.setId(id);
		try {
			rifa = service.update(rifa);
			
		} catch (UsuarioSemTicketException e) {
			e.printStackTrace();
		}
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
