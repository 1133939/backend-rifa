package com.rifa.model;

public class UsuarioDTO {
private Integer id;
private String nome;

public UsuarioDTO(Usuario usuario) {
id = usuario.getId();
nome = usuario.getNome();
}
public UsuarioDTO() {
	
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

}
