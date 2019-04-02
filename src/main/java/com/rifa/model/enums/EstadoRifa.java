package com.rifa.model.enums;

public enum EstadoRifa {
CONCLUIDA(1, "Concluída"),
PENDENTE(2, "Pendente");

private Integer cod;
private String descricao;

private EstadoRifa(Integer cod, String descricao) {
	this.cod=cod;
	this.descricao=descricao;
}

public Integer getCod() {
	return cod;
}

public String getDescricao() {
	return descricao;
}

public static EstadoRifa toEnum(Integer cod) {
	if(cod == null) {
		return null;
	}
	for(EstadoRifa estadoRifa : EstadoRifa.values()) {
		if(cod.equals(estadoRifa.getCod())) {
			return estadoRifa;
		}
	}
	throw new IllegalArgumentException("Id inválido	"+ cod);
}
}
