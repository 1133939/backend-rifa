package com.rifa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifa.model.Sorteio;
@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Integer> {

}
