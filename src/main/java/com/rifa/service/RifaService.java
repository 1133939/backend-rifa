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
public List<Rifa> findByName(Rifa rifa){
	return repository.findByNome(rifa.getNome());
}
public Rifa insert(Rifa rifa) {
rifa.setId(null);
return repository.save(rifa);
}
public Rifa update(Rifa rifa) throws ParseException {
if(this.rifaFull(rifa.getId())) {
rifa.setEstado(EstadoRifa.CONCLUIDA);
}	  
	return repository.save(rifa);

}
public void sortRifa(Rifa rifa) throws ParseException {
	Random sorteio = new Random();
	Integer sorteado = sorteio.nextInt(rifa.getQuantidade());
	Usuario usuarioSorteado = rifa.getUsuarios().get(sorteado);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	Sorteio sorteioRifa = new Sorteio(null, usuarioSorteado, rifa, sdf.parse("02/04/2019 02:10"));
	repositorySorteio.save(sorteioRifa);	
}
public boolean rifaFull(Integer id) throws ParseException {
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
