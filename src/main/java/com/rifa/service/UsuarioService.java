package com.rifa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifa.model.Usuario;
import com.rifa.repositories.UsuarioRepository;

@Service
public class UsuarioService {
@Autowired
private UsuarioRepository repository;

public Usuario insert(Usuario usuario) {
	usuario.setId(null);
	return repository.save(usuario);
}
public Usuario update(Usuario usuario) {
	return repository.save(usuario);
}
public List<Usuario> findAll(){
	return repository.findAll();
}
public Usuario find(Integer id){
	Optional<Usuario> usuario = repository.findById(id);
	return usuario.orElse(null);
}


}
