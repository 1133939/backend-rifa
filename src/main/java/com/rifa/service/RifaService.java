package com.rifa.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifa.model.Rifa;
import com.rifa.repositories.RifaRepository;
@Service
public class RifaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired
private RifaRepository repository;

public List<Rifa> findAll() {
	return repository.findAll();
}
}
