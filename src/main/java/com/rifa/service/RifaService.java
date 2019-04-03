package com.rifa.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifa.model.Rifa;
import com.rifa.model.Sorteio;
import com.rifa.model.Usuario;
import com.rifa.model.enums.EstadoRifa;
import com.rifa.repositories.RifaRepository;
import com.rifa.repositories.SorteioRepository;
@Service
public class RifaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired
private RifaRepository repository;
	@Autowired
private SorteioRepository repositorySorteio;

public List<Rifa> findAll() {
	return repository.findAll();
}
public Rifa find(Integer id) {
	Optional<Rifa> rifas = repository.findById(id);
	return rifas.orElse(null);
}
public List<Rifa> findByName(String nomeRifa){
	return repository.findByNome(nomeRifa);
}


public Rifa insert(Rifa rifa) {
rifa.setId(null);
return repository.save(rifa);
}


public Rifa update(Rifa rifa){
if(this.rifaFull(rifa.getId())) {
rifa.setEstado(EstadoRifa.CONCLUIDA);
}	  
	System.out.println("ASDASD"+rifa.getId());
	return repository.save(rifa);

}

public void delete(Integer id) {
	repository.deleteById(id);
}

private void sortRifa(Rifa rifa) {
	Random sorteio = new Random();
	Integer sorteado = sorteio.nextInt(rifa.getQuantidade());
	Usuario usuarioSorteado = rifa.getUsuarios().get(sorteado);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	try {
	Sorteio sorteioRifa = new Sorteio(null, usuarioSorteado, rifa, sdf.parse("02/04/2019 02:10"));
	repositorySorteio.save(sorteioRifa);	
	}catch(ParseException e) {
		e.getMessage();
	}
}
private boolean rifaFull(Integer id) {
	if(id == null) {
		throw new IllegalAccessError("id Nulo");
	}
	Rifa rifa = this.find(id);
	List<Usuario> usuarios = rifa.getUsuarios();	
	if(rifa.getQuantidade().equals(usuarios.size()+1) && rifa.getEstado()==EstadoRifa.PENDENTE) {
		this.sortRifa(rifa);
		return true;
	}	else {
		return false;
	}
}
}
