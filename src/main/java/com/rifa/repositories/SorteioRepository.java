package com.rifa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rifa.model.Sorteio;
@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Integer> {
@Query("SELECT s FROM Sorteio s WHERE s.usuarioVencedor.usuario=?1")
List<Sorteio> findGanhadorSorteio(String usuario);
}
