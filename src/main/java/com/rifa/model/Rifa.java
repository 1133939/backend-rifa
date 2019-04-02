package com.rifa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rifa.model.enums.EstadoRifa;
@Entity
public class Rifa implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String nome;
private Integer estado;
private Integer quantidade;
@ManyToMany
@JoinTable(name="RIFA_USUARIO",
joinColumns=@JoinColumn(name="rifa_id"),
inverseJoinColumns=@JoinColumn(name="usuario_id"))
private List<Usuario> usuarios = new ArrayList<>();
@OneToOne(mappedBy="rifa")
@JsonIgnore
private Sorteio sorteio;


public Rifa() {
	
}


public Rifa(Integer id, String nome, EstadoRifa estado, Integer qtd) {
	super();
	this.id=id;
	this.nome = nome;
	this.estado = estado.getCod();
	this.quantidade = qtd;
}


public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public EstadoRifa getEstado() {
	return EstadoRifa.toEnum(estado);
}

public void setEstado(EstadoRifa estado) {
	this.estado = estado.getCod();
}

public List<Usuario> getUsuarios() {
	return usuarios;
}

public void setUsuarios(List<Usuario> usuarios) {
	this.usuarios = usuarios;
}


public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}

public Sorteio getSorteio() {
	return sorteio;
}


public void setSorteio(Sorteio sorteio) {
	this.sorteio = sorteio;
}

public Integer getQuantidade() {
	return quantidade;
}


public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Rifa other = (Rifa) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}







}
