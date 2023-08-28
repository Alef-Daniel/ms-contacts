package br.com.picpay.mscontacts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.picpay.mscontacts.entities.Contato;
import br.com.picpay.mscontacts.entities.Pessoa;
import br.com.picpay.mscontacts.repositories.ContatoRepository;

@Service
public class ContatoService {
	
	
	@Autowired
	private ContatoRepository repository;
	
	
	
	public Contato addContato(Contato contato){
			return repository.save(contato);
	}
	
}
