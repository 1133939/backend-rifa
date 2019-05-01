package com.rifa.model;

import java.util.List;
import java.util.stream.Collectors;

public class RifaDTO {
private Integer id;
private String nome;
private List<UsuarioDTO> usuarios;
private Integer quantidade;

public RifaDTO(Rifa rifa) {
id = rifa.getId();
nome = rifa.getNome();
setUsuarios(convert(rifa.getUsuarios()));
setQuantidade(rifa.getQuantidade());
}
public RifaDTO() {
}
public Integer getId() {
	return id;
}
public String getNome() {
	return nome;
}

public void setId(Integer id) {
	this.id = id;
}
public void setNome(String nome) {
	this.nome = nome;
}
public List<UsuarioDTO> getUsuarios() {
	return usuarios;
}
public void setUsuarios(List<UsuarioDTO> usuarios) {
	this.usuarios = usuarios;
}
//UTIL
public List<UsuarioDTO> convert(List<Usuario> usuarios) {
	return usuarios.stream().map(user -> new UsuarioDTO(user)).collect(Collectors.toList());
}
public Integer getQuantidade() {
	return quantidade;
}
public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
}

}
