package com.rifa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifa.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findBySenhaAndUsuario(String senha, String usuario);
}
