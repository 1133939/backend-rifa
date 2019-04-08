package com.rifa.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifa.model.Rifa;
import com.rifa.model.RifaDTO;
import com.rifa.model.Sorteio;
import com.rifa.model.Usuario;
import com.rifa.model.UsuarioDTO;
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
rifa.setEstado(EstadoRifa.PENDENTE);
return repository.save(rifa);
}


public Rifa update(Rifa rifa){
Rifa newRifa = updateRifa(rifa);
if(this.rifaFull(rifa.getId())) {
newRifa.setEstado(EstadoRifa.CONCLUIDA);
}
	return repository.save(newRifa);

}
private Rifa updateRifa(Rifa rifa) {
	Rifa newRifa = find(rifa.getId());
	if(newRifa.getEstado()==EstadoRifa.PENDENTE) {
	List<Usuario> list = newRifa.getUsuarios();
	list.addAll(rifa.getUsuarios());
	newRifa.setUsuarios(list);
	}
	if(rifa.getNome()!=null) {
	newRifa.setNome(rifa.getNome());	
	}
	if(rifa.getEstado()!=null) {
		newRifa.setEstado(rifa.getEstado());		
	}
	if(rifa.getQuantidade()!=null) {
		newRifa.setQuantidade(rifa.getQuantidade());		
	}
	if(rifa.getSorteio()!=null) {
		newRifa.setSorteio(rifa.getSorteio());		
	}
	return newRifa;
	
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
	if(rifa.getQuantidade().equals(usuarios.size()) && rifa.getEstado()==EstadoRifa.PENDENTE) {
		this.sortRifa(rifa);
		return true;
	}	else {
		return false;
	}
}
public List<RifaDTO> fromDTO(List<Rifa> rifas){
	List<RifaDTO> listaDTO = new ArrayList<>();
	for(Rifa item : rifas) {
		RifaDTO aux = new RifaDTO();
		aux.setId(item.getId());
		aux.setNome(item.getNome());
		for(Usuario usuarios : item.getUsuarios()) {
		UsuarioDTO auxUser = new UsuarioDTO();
		auxUser.setId(usuarios.getId());
		auxUser.setNome(usuarios.getNome());
		aux.setUsuarios(Arrays.asList(auxUser));
		}
		listaDTO.add(aux);
	}
	return listaDTO;
}
}
