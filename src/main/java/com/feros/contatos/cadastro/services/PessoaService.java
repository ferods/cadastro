package com.feros.contatos.cadastro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feros.contatos.cadastro.dtos.PessoaDTO;
import com.feros.contatos.cadastro.dtos.PessoaDetalhesDTO;
import com.feros.contatos.cadastro.models.Pessoa;
import com.feros.contatos.cadastro.repositories.PessoaRepository;
import com.feros.contatos.cadastro.requisicoes.RequisicaoAtualizacaoPessoa;
import com.feros.contatos.cadastro.requisicoes.RequisicaoPessoa;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;	
	

	@Transactional
	public ResponseEntity<PessoaDetalhesDTO> salvar(RequisicaoPessoa req) {
		Pessoa pessoa = req.toPessoa();
		pessoaRepository.save(pessoa);
		return ResponseEntity.ok(new PessoaDetalhesDTO(pessoa));
	}
	
	

	public ResponseEntity<List<PessoaDTO>> lista(String nome) {
		if (nome == null) {
			List<Pessoa> pessoas = pessoaRepository.findAll();
			return ResponseEntity.ok(pessoas.stream().map(PessoaDTO::new).collect(Collectors.toList()));
		}
		List<Pessoa> pessoas = pessoaRepository.findByNome(nome);
		return ResponseEntity.ok(pessoas.stream().map(PessoaDTO::new).collect(Collectors.toList()));
	}
	

	public ResponseEntity<PessoaDetalhesDTO> detalhar(Long cpf) {
		Optional<Pessoa> pessoaOpt = pessoaRepository.findById(cpf);
		if (!pessoaOpt.isPresent())
			return ResponseEntity.notFound().build();
		Pessoa pessoa = pessoaOpt.get();
		return ResponseEntity.ok(new PessoaDetalhesDTO(pessoa));
	}
	
	

	@Transactional
	public ResponseEntity<PessoaDTO> atualizar(Long cpf, RequisicaoAtualizacaoPessoa req) {
		return ResponseEntity.ok(new PessoaDTO(req.atualizar(pessoaRepository, cpf).getBody()));
	}



	@Transactional
	public ResponseEntity<?> remover(Long cpf) {
		Optional<Pessoa> pessoaOpt = pessoaRepository.findById(cpf);
		if(!pessoaOpt.isPresent()) return ResponseEntity.notFound().build();
		pessoaRepository.delete(pessoaOpt.get());
		return ResponseEntity.ok().build();
	}

}
