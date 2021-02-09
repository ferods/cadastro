package com.feros.contatos.cadastro.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.feros.contatos.cadastro.dtos.EnderecoDTO;
import com.feros.contatos.cadastro.dtos.EnderecoDetalhesDTO;
import com.feros.contatos.cadastro.dtos.PessoaDTO;
import com.feros.contatos.cadastro.models.Endereco;
import com.feros.contatos.cadastro.models.Pessoa;
import com.feros.contatos.cadastro.repositories.EnderecoRepository;
import com.feros.contatos.cadastro.repositories.PessoaRepository;
import com.feros.contatos.cadastro.requisicoes.RequisicaoAtualizacaoEndereco;
import com.feros.contatos.cadastro.requisicoes.RequisicaoEndereco;

@Service
public class EnderecoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	public ResponseEntity<EnderecoDTO> salvar(RequisicaoEndereco req, UriComponentsBuilder builder) {
		
		Optional<Pessoa> pessoaOpt = pessoaRepository.findById(req.getCpfPessoa());
		if(!pessoaOpt.isPresent()) return ResponseEntity.notFound().build();
		
		Pessoa pessoa = pessoaOpt.get();
		Endereco enderecoConvertido = req.toEndereco();
		
		pessoa.addEndereco(enderecoConvertido);
		enderecoConvertido.setPessoa(pessoa);
		
		pessoaRepository.save(pessoa);		
		
		URI uri = builder.path("/api/cadastros/enderecos").buildAndExpand(enderecoConvertido.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoDTO(enderecoConvertido));
	}
	
	

	public ResponseEntity<List<EnderecoDTO>> listar(String cep) {
		
		if(cep == null) {
			List<Endereco> enderecos = enderecoRepository.findAll();
			return ResponseEntity.ok(enderecos.stream().map(EnderecoDTO :: new).collect(Collectors.toList()));
		}		
		List<Endereco> enderecos = enderecoRepository.findByCep(cep);
		return ResponseEntity.ok(enderecos.stream().map(EnderecoDTO :: new).collect(Collectors.toList()));
	}



	public ResponseEntity<EnderecoDetalhesDTO> detalhar(Long id) {
		
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
		if (!enderecoOpt.isPresent()) return ResponseEntity.notFound().build();
		
		Endereco endereco = enderecoOpt.get();
		
		return ResponseEntity.ok(new EnderecoDetalhesDTO(endereco));
	}



	@Transactional
	public ResponseEntity<EnderecoDTO> atualizar(Long id, RequisicaoAtualizacaoEndereco req) {
		return ResponseEntity.ok(new EnderecoDTO(req.atualizar(id, enderecoRepository).getBody()));
	}
	
	@Transactional
	public ResponseEntity<?> remover(Long id){
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
		if(!enderecoOpt.isPresent()) return ResponseEntity.notFound().build();
		enderecoRepository.delete(enderecoOpt.get());;
		return ResponseEntity.ok().build();
	}
	

}
