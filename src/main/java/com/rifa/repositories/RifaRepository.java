package com.rifa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifa.model.Rifa;
@Repository
public interface RifaRepository extends JpaRepository<Rifa, Integer>{
	List<Rifa>findByNomeContainingIgnoreCaseAndEstado(String nome, Integer estado);
	List<Rifa>findDistinctRifaByUsuarios_Usuario(String nome);
}
