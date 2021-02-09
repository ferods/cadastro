package com.feros.contatos.cadastro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.feros.contatos.cadastro.dtos.EnderecoDTO;
import com.feros.contatos.cadastro.dtos.EnderecoDetalhesDTO;
import com.feros.contatos.cadastro.dtos.PessoaDTO;
import com.feros.contatos.cadastro.dtos.PessoaDetalhesDTO;
import com.feros.contatos.cadastro.requisicoes.RequisicaoAtualizacaoEndereco;
import com.feros.contatos.cadastro.requisicoes.RequisicaoAtualizacaoPessoa;
import com.feros.contatos.cadastro.requisicoes.RequisicaoEndereco;
import com.feros.contatos.cadastro.requisicoes.RequisicaoPessoa;
import com.feros.contatos.cadastro.services.EnderecoService;
import com.feros.contatos.cadastro.services.PessoaService;

@RestController
@RequestMapping("/api/cadastros")
public class CadastrosController {
		
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private EnderecoService enderecoService;

	//pessoa CRUD
	
	@PostMapping
	public ResponseEntity<PessoaDetalhesDTO> salvar(@RequestBody RequisicaoPessoa req){
		return pessoaService.salvar(req);
	}
	
	@GetMapping
	public ResponseEntity<List<PessoaDTO>> getLista(@RequestParam(required = false) String nome){
		return pessoaService.lista(nome);		
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<PessoaDetalhesDTO> getPessoaDetalhes(@PathVariable Long cpf){
		return pessoaService.detalhar(cpf);		
	}
	
	@PutMapping("/atualizar/{cpf}")
	public ResponseEntity<PessoaDTO> atualizar(@PathVariable Long cpf, @RequestBody RequisicaoAtualizacaoPessoa req){
		return pessoaService.atualizar(cpf, req);
	}
	
	@DeleteMapping("/remover/{cpf}")
	public ResponseEntity<?> remover(@PathVariable Long cpf){
		return pessoaService.remover(cpf);
	}
	
		
	//endereco CRUD
	
	
	@PostMapping("/enderecos")
	public ResponseEntity<EnderecoDTO> salvar(@RequestBody RequisicaoEndereco req, UriComponentsBuilder builder){
		return enderecoService.salvar(req, builder);		
	}
	
	@GetMapping("/enderecos")
	public ResponseEntity<List<EnderecoDTO>> getListaEnderecos(@RequestParam(required = false) String cep){
		return enderecoService.listar(cep);		
	}
	
	@GetMapping("/enderecos/{id}")
	public ResponseEntity<EnderecoDetalhesDTO> getEnderecoDetalhes(@PathVariable Long id){
		return enderecoService.detalhar(id);		
	}
	
	@PutMapping("/enderecos/atualizar/{id}")
	public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long id, @RequestBody RequisicaoAtualizacaoEndereco req){
		return enderecoService.atualizar(id, req);
	}
	
	@DeleteMapping("/enderecos/remover/{id}")
	public ResponseEntity<?> removerEndereco(@PathVariable Long id){
		return enderecoService.remover(id);
		}
	
	

}
