package com.rifa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rifa.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	@Transactional(readOnly=true)
	Usuario findBySenhaAndUsuario(String senha, String usuario);
	@Transactional(readOnly=true)
	Usuario findByUsuario(String usuario);
}
