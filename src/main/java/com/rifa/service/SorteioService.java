package com.rifa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifa.model.Sorteio;
import com.rifa.repositories.SorteioRepository;

@Service
public class SorteioService {
@Autowired
private SorteioRepository repository;

public List<Sorteio> findAll() {
	return repository.findAll();
}

}
