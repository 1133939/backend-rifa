package com.rifa.model;


public class RifaDTO {
private Integer id;
private String nome;

public RifaDTO(Rifa rifa) {
id = rifa.getId();
nome = rifa.getNome();
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


}
