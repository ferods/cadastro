package com.feros.contatos.cadastro.requisicoes;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.feros.contatos.cadastro.models.Pessoa;
import com.feros.contatos.cadastro.repositories.PessoaRepository;

public class RequisicaoAtualizacaoPessoa {

	private String nomeAtualizar;
	private String emailAtualizar;
	private RequisicaoAtualizarTelefone telefoneAtualizar;

	public String getNomeAtualizar() {
		return nomeAtualizar;
	}

	public void setNomeAtualizar(String nomeAtualizar) {
		this.nomeAtualizar = nomeAtualizar;
	}

	public String getEmailAtualizar() {
		return emailAtualizar;
	}

	public void setEmailAtualizar(String emailAtualizar) {
		this.emailAtualizar = emailAtualizar;
	}

	public RequisicaoAtualizarTelefone getTelefoneAtualizar() {
		return telefoneAtualizar;
	}

	public void setTelefoneAtualizar(RequisicaoAtualizarTelefone telefoneAtualizar) {
		this.telefoneAtualizar = telefoneAtualizar;
	}

	public ResponseEntity<Pessoa> atualizar(PessoaRepository pessoaRepository, Long cpf) {
		
		Optional<Pessoa> pessoaOpt = pessoaRepository.findById(cpf);
		if(!pessoaOpt.isPresent()) return ResponseEntity.notFound().build();
		
		Pessoa pessoa = pessoaOpt.get();
		pessoa.setNome(this.nomeAtualizar);
		pessoa.setEmail(this.emailAtualizar);
		pessoa.getTelefone().setResidencial(this.telefoneAtualizar.getResidencialAtualizar());
		pessoa.getTelefone().setCelular(this.telefoneAtualizar.getCelularAtualizar());
		
		pessoaRepository.save(pessoa);
		
		return ResponseEntity.ok(pessoa);
		
		
	}

}
