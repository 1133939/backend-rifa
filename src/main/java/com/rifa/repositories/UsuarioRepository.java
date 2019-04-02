package com.rifa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifa.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findBySenhaAndUsuario(String senha, String usuario);
}
