package br.com.picpay.mscontacts.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.picpay.mscontacts.DTO.PessoaDTO;
import br.com.picpay.mscontacts.entities.Pessoa;
import br.com.picpay.mscontacts.services.PessoaService;
import br.com.picpay.mscontacts.swagger.PessoaApi;
import jakarta.persistence.EntityNotFoundException;




@RestController
@RequestMapping(value = "/api/pessoa", produces = "application/json")
public class PessoaResource implements PessoaApi {
	
	
	@Autowired
	private PessoaService service;
	
	
	@GetMapping
	public ResponseEntity<List<PessoaDTO>> findAllPessoas(){
		 
		try {
			List<PessoaDTO> pessoas = service.findaAllPessoas();
			return ResponseEntity.ok().body(pessoas);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Contato encontrado.");
        }	
		
	}
	

	@PostMapping(path = "/cadastro", produces = "application/json")
	public ResponseEntity<String> addPessoaAndContato( @RequestBody Pessoa obj){
		
		 try {
			 obj = service.addPessoa(obj);
				return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso.");
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar contato");
	        }
		
		
		
		
	}
	
	
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<PessoaDTO> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO){
		
		try {
			PessoaDTO updatedPessoa = service.updatePessoa(id, pessoaDTO);
			return ResponseEntity.ok(updatedPessoa);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com o ID: " + id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar pessoa", e);
        }
		
		
		
		
		
		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<String> deletePessoa(@PathVariable Long id){
		
		 try {
			 service.deletePessoa(id);
				return ResponseEntity.ok("Contato deletado com sucesso");
	        } catch (EntityNotFoundException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com o ID: " + id);
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar pessoa", e);
	        }
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
	
	
	
