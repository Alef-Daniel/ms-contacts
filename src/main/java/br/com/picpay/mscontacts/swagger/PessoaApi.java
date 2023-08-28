package br.com.picpay.mscontacts.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.picpay.mscontacts.DTO.PessoaDTO;
import br.com.picpay.mscontacts.entities.Pessoa;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


public interface PessoaApi {
	
	@Tag(name = "Cadastro de Pessoas", description = "Cadastrar informações de pessoas/empresas e os telefones para contato.")
	@Operation(summary = "Cadastro de pessoas", description = "Endpoint onde se cadastra informações da pessoa/empresa"
			+ " junto aos numeros de telefone para contato.")
	
	@ApiResponses({
        @ApiResponse(code = 201, message = "Cadastro realizado com sucesso. "),
        @ApiResponse(code = 500, message = "Erro ao cadastrar contato")
    })
    
	ResponseEntity<String> addPessoaAndContato(@RequestBody Pessoa obj);
	
	@Tag(name = "Consulta de contatos das Pessoas e empresas", description = "Consultar informações de contato das pessoas e empresas.")
	@Operation(summary = "Consulta de contatos", description = "Endpoint para realizar consulta de contatos dos parceiros e funcionarios da empresa Picpay e  Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contatos encontrados",response = PessoaDTO.class),
        @ApiResponse(code = 404, message = "Nenhum Contato encontrado.")
    })
	public ResponseEntity<List<PessoaDTO>> findAllPessoas();
	
	@Tag(name = "Atualizar informações de contato", description = "Atualizar informações de contato de pessoas e parceiros")
	@Operation(summary = "Atualização de contato.", description = "Endpoint para realizar atualização de contatos dos parceiros e funcionarios da empresa Picpay e  Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato atualizado com sucesso",response = PessoaDTO.class),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado."),
        @ApiResponse(code = 500, message = "Erro ao atualizar pessoa.")
    })
	public ResponseEntity<PessoaDTO> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO);
	
	@Tag(name = "Deletar contato de pessoas e empresas", description = "Deletar informações de contato de pessoas e parceiros")
	@Operation(summary = "Remoção de contato.", description = "Endpoint para realizar atualização de contatos dos parceiros e funcionarios da empresa Picpay e  Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato deletado com sucesso"),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado."),
        @ApiResponse(code = 500, message = "Erro ao deletar contato.")
    })
	public  ResponseEntity<String> deletePessoa(@PathVariable Long id);
}
