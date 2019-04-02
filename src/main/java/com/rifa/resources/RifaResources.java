package com.rifa.resources;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rifa.model.Rifa;
import com.rifa.service.RifaService;

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
		return ResponseEntity.ok().body(lista);
	}
}
