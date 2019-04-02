package com.rifa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifa.model.Rifa;

public interface RifaRepository extends JpaRepository<Rifa, Integer>{
	List<Rifa>findByNome(String nome);
}
