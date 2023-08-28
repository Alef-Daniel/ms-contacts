package br.com.picpay.mscontacts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.picpay.mscontacts.entities.Contato;
import br.com.picpay.mscontacts.entities.Pessoa;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
	
	void deleteAllByPessoa(Pessoa pessoa);
}
