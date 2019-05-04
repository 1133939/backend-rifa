package com.rifa.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rifa.model.Usuario;
import com.rifa.repositories.UsuarioRepository;

@Service
public class UsuarioService {
@Autowired
private UsuarioRepository repository;
@Autowired
private BCryptPasswordEncoder pe;


public Usuario insert(Usuario usuario) {
	if(usuario.getSenha()==null || usuario.getUsuario()==null) {
		throw new IllegalAccessError("Usuário não cadastrado devidamente, nome de usuário ou senha vazios");
	}
	usuario.setSenha(pe.encode(usuario.getSenha()));
	usuario.setId(null);
	return repository.save(usuario);
}



public Usuario update(Usuario usuario) {
	usuario = updateUsuario(usuario);
	return repository.save(usuario);
}
private Usuario updateUsuario(Usuario usuario) {
	Usuario newUsuario = find(usuario.getId());
	if(usuario.getNome()!=null && usuario.getNome()!="") {
		newUsuario.setNome(usuario.getNome());	
	}if(usuario.getSenha()!=null  && usuario.getSenha()!="") {
		newUsuario.setSenha(pe.encode(usuario.getSenha()));	
	}
	if(usuario.getTickets()!=null) {
		newUsuario.setTickets(usuario.getTickets());
		}
	if(usuario.getRifas()!=null) {
		newUsuario.setRifas(usuario.getRifas());	
	}if(usuario.getUsuario()!=null  && usuario.getUsuario()!="") {
		newUsuario.setUsuario(usuario.getUsuario());	
	}if(usuario.getSorteio()!=null) {
		newUsuario.setSorteio(usuario.getSorteio());	
	}
	return newUsuario;
}
public void delete(Integer id) {
	repository.deleteById(id);
}


public List<Usuario> findAll(){
	return repository.findAll();
}

public Usuario find(Integer id){
	Optional<Usuario> usuario = repository.findById(id);
	return usuario.orElse(null);
}


public Usuario findByName(String usuario) {
	return repository.findByUsuario(usuario);
}


}
