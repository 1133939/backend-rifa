package com.rifa.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	if(usuario.getSenha()==null || usuario.getUsuario()==null) {
		throw new IllegalAccessError("Usuário não cadastrado devidamente, nome de usuário ou senha vazios");
	}
	usuario.setId(null);
	String criptoPass = get_SHA_512_SecurePassword(usuario.getSenha(),usuario.getUsuario());	
	usuario.setSenha(criptoPass);
	return repository.save(usuario);
}

public Usuario autentica(Usuario usuario) {
	if(usuario.getSenha()==null || usuario.getUsuario()==null) {
		throw new IllegalAccessError("Usuário não cadastrado devidamente, nome de usuário ou senha vazios");
	}
	String criptoPass = get_SHA_512_SecurePassword(usuario.getSenha(),usuario.getUsuario());	
	usuario.setSenha(criptoPass);
	Usuario user = repository.findBySenhaAndUsuario(usuario.getSenha(), usuario.getUsuario());
	if(user != null) {
		System.out.println("Logando Usuário...");
		return user;
	}
		throw new IllegalAccessError("Usuário não existe ou senha incorreta");
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



public String get_SHA_512_SecurePassword(String passwordToHash, String   salt){
	
String generatedPassword = null;
    try {
         MessageDigest md = MessageDigest.getInstance("SHA-512");
         md.update(salt.getBytes(StandardCharsets.UTF_8));
         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
         StringBuilder sb = new StringBuilder();
         for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
         }
         generatedPassword = sb.toString();
        } 
       catch (NoSuchAlgorithmException e){
        e.printStackTrace();
       }
    return generatedPassword;
}


}
