package com.rifa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rifa.model.Usuario;
import com.rifa.repositories.UsuarioRepository;
import com.rifa.security.UserSS;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UsuarioRepository repo;
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Usuario user = repo.findByUsuario(usuario);
		if(user == null) {
			throw new UsernameNotFoundException(usuario);
		}
		return new UserSS(user.getId(),user.getUsuario(),user.getSenha(),new ArrayList<>());
	}

}