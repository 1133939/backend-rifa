package com.rifa.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifa.exceptions.UsuarioSemTicketException;
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
private UsuarioService usuarioService;
	@Autowired
private SorteioRepository repositorySorteio;
	public RifaService() {
	}

public List<Rifa> findAll() {
	return repository.findAll();
}
public Rifa find(Integer id) {
	Optional<Rifa> rifas = repository.findById(id);
	return rifas.orElse(null);
}
public List<Rifa> findByName(String nomeRifa){
	// 2 = pendente    1 = concluida
	List<Rifa> rifas =  repository.findByNomeContainingIgnoreCaseAndEstado(nomeRifa, 2);
	System.out.println("TESETEEEEEEEEEE  "+ rifas);
	return rifas;
}


public Rifa insert(Rifa rifa) {
rifa.setId(null);
rifa.setEstado(EstadoRifa.PENDENTE);
return repository.save(rifa);
}


public Rifa update(Rifa rifa) throws UsuarioSemTicketException{
Rifa newRifa = updateRifa(rifa);
if(this.rifaFull(rifa.getId())) {
newRifa.setEstado(EstadoRifa.CONCLUIDA);
}	
	
	return repository.save(newRifa);

}
private Rifa updateRifa(Rifa rifa) throws UsuarioSemTicketException {
	Rifa newRifa = find(rifa.getId());
	if(newRifa.getEstado()==EstadoRifa.PENDENTE && !rifa.getUsuarios().isEmpty() || rifa.getUsuarios()!=null) {
	List<Usuario> list = newRifa.getUsuarios();
	for(Usuario usuario : rifa.getUsuarios()) {
		Usuario aux = usuarioService.find(usuario.getId());
		if(aux.getTickets()>0) {
		Usuario auxUser = new Usuario();
		auxUser.setId(aux.getId());
		auxUser.setTickets(aux.getTickets()-1);
		usuarioService.update(auxUser);
		}else {
			throw new UsuarioSemTicketException("Usuário sem tickets para comprar rifa");
		}
	}
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
public void setRepository(RifaRepository repository) {
	this.repository=repository;
}
}
