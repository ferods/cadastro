package com.feros.contatos.cadastro.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.feros.contatos.cadastro.dtos.DetalhesEnderecoDTO;
import com.feros.contatos.cadastro.dtos.DetalhesPessoaEnderecosDTO;
import com.feros.contatos.cadastro.dtos.EnderecoDTO;
import com.feros.contatos.cadastro.dtos.PessoaDTO;
import com.feros.contatos.cadastro.dtos.PessoaEnderecosDTO;
import com.feros.contatos.cadastro.formularios.AtualizacaoPessoaForm;
import com.feros.contatos.cadastro.formularios.EnderecoForm;
import com.feros.contatos.cadastro.formularios.PessoaForm;
import com.feros.contatos.cadastro.formularios.VinculoForm;
import com.feros.contatos.cadastro.services.EnderecoService;
import com.feros.contatos.cadastro.services.PessoaService;







@RestController @RequestMapping("/cadastros")
public class CadastroController {
	
	
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private EnderecoService enderecoService;

	
	
	
	@PostMapping
	public ResponseEntity<PessoaEnderecosDTO> salvar(@RequestBody @Valid PessoaForm pessoaForm, UriComponentsBuilder uriBuilder) {
		return pessoaService.salvar(pessoaForm, uriBuilder);
	}
	
	@PostMapping("/endereco")
	public ResponseEntity<DetalhesEnderecoDTO> salvarEndereco(@RequestBody @Valid EnderecoForm enderecoForm, UriComponentsBuilder uriBuilder){
		return enderecoService.salvarEndereco(enderecoForm, uriBuilder);
	}
	
	
	
	@GetMapping("/pessoas")
	public List<PessoaDTO> listarPessoas(@RequestParam(required = false) String nome){
		return pessoaService.listarPessoas(nome);
	}
	
	@GetMapping("/enderecos")
	public List<EnderecoDTO> listarEnderecos(@RequestParam(required = false) Integer cep ){
		return enderecoService.listarEnderecos(cep);
	}
	
	
	@GetMapping
	public List<PessoaEnderecosDTO> listarPessoaEnderecos(@RequestParam(required = false) String nome){
		return pessoaService.listarPessoaEnderecos(nome);
	}
	
	
	@GetMapping("/{id}")
	public DetalhesPessoaEnderecosDTO detalharPessoaEnderecos(@PathVariable Long id) {
		return pessoaService.detalharPessoaEnderecos(id);
	}
	
	
	@PutMapping("/vincularendereco/{id}")
	public ResponseEntity<PessoaEnderecosDTO> vincularEndereco(@PathVariable Long id, @RequestBody @Valid VinculoForm form){
		return enderecoService.vincularEnderecos(id, form);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<PessoaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoPessoaForm form){
		return pessoaService.atualizarPessoa(id, form);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		return pessoaService.deletar(id);
	}
	
	@DeleteMapping("/endereco/{id}")
	public ResponseEntity<?> deletarEndereco(@PathVariable Long id){
		return enderecoService.deletar(id);
	}
	
	
	
	

}


