package com.rifa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Sorteio implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
@ManyToOne
@JoinColumn(name="usuario_id")
private Usuario usuarioVencedor;
@OneToOne
@JoinColumn(name="rifa_id")
@JsonIgnore
private Rifa rifa;
@JsonFormat(pattern="dd/MM/yyyy HH:mm")
private Date date;

public Sorteio() {
	
}

public Sorteio(Integer id, Usuario usuarioVencedor, Rifa rifa, Date date) {
	super();
	this.id = id;
	this.usuarioVencedor = usuarioVencedor;
	this.rifa = rifa;
	this.date = date;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Usuario getUsuarioVencedor() {
	return usuarioVencedor;
}

public void setUsuarioVencedor(Usuario usuarioVencedor) {
	this.usuarioVencedor = usuarioVencedor;
}

public Rifa getRifa() {
	return rifa;
}

public void setRifa(Rifa rifa) {
	this.rifa = rifa;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
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
	Sorteio other = (Sorteio) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}


}
