package com.rifa.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rifa.model.Usuario;
import com.rifa.service.UsuarioService;
@CrossOrigin
@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResources {
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id)	{
		Usuario usuario = service.find(id);
	return ResponseEntity.ok().body(usuario);
	}
	@RequestMapping(method=RequestMethod.GET, value="/")
	public ResponseEntity<?> findByName(@RequestParam(value="usuario") String usuario)	{
		Usuario user = service.findByName(usuario);
	return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll()	{
		List<Usuario> usuario = service.findAll();
	return ResponseEntity.ok().body(usuario);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Usuario usuario){
	usuario = service.insert(usuario);
	return ResponseEntity.created(null).build();
	}
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Usuario usuario){
	usuario.setId(id);
	usuario = service.update(usuario);
	return ResponseEntity.noContent().build();
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
