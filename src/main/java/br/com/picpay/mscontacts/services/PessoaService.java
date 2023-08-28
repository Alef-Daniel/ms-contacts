package br.com.picpay.mscontacts.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.picpay.mscontacts.DTO.PessoaDTO;
import br.com.picpay.mscontacts.entities.Contato;
import br.com.picpay.mscontacts.entities.Pessoa;
import br.com.picpay.mscontacts.repositories.ContatoRepository;
import br.com.picpay.mscontacts.repositories.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {
	
	
	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	
	public Pessoa addPessoa(Pessoa pessoa){
			for(Contato contato : pessoa.getContatos()) {
				contato.setPessoa(pessoa);
			}
			return repository.save(pessoa);
	}
	
	public List<PessoaDTO> findaAllPessoas(){
		List<Pessoa> pessoas = repository.findAll();
		 return pessoas.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
		
	}
	
	

	public PessoaDTO updatePessoa(Long id, PessoaDTO pessoaDTO) {
		Pessoa pessoaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + id));
		
		pessoaExistente.setNome(pessoaDTO.getNome());
		pessoaExistente.setCargo(pessoaDTO.getCargo());
		pessoaExistente.setTime(pessoaDTO.getTime());
		pessoaExistente.setTipo_contato(pessoaDTO.getTipo_contato());
		
		pessoaExistente.getContatos().clear();
        for (Contato contato : pessoaDTO.getContatos()) {
            Contato contato1 = new Contato();
            contato1.setDdd(contato.getDdd());
            contato1.setTelefone(contato.getTelefone());
            contato1.setPessoa(pessoaExistente);
            pessoaExistente.getContatos().add(contato1);
        }
		
		Pessoa updatedPessoa = repository.save(pessoaExistente);
		return convertToDTO(updatedPessoa);
		
		
	}
	
	@Transactional
	public void deletePessoa(Long id) {
		Pessoa pessoa = repository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + id));
		
		contatoRepository.deleteAllByPessoa(pessoa);
		
		repository.delete(pessoa);
		
	}
	
	
	
	
	
	
	/*Converte dadois persistido em DTO para serializaÇao*/
	
 private PessoaDTO convertToDTO(Pessoa pessoa) {
		 
		 PessoaDTO pessoaDTO = new PessoaDTO();
		 
		        pessoaDTO.setId(pessoa.getId());
		        pessoaDTO.setNome(pessoa.getNome());
		        pessoaDTO.setCargo(pessoa.getCargo());
		        pessoaDTO.setTime(pessoa.getTime());
		        pessoaDTO.setTipo_contato(pessoa.getTipo_contato());
		        pessoaDTO.setContatos(pessoa.getContatos());
				
			
			
			return pessoaDTO;
		}

}
	

