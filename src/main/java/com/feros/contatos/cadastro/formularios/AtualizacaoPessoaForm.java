package com.feros.contatos.cadastro.formularios;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.feros.contatos.cadastro.model.Pessoa;
import com.feros.contatos.cadastro.repositories.PessoaRepository;

public class AtualizacaoPessoaForm {

	@NotNull @NotEmpty @Length(max = 50)
	private String nome;
	@NotNull
	private Long cpf;
	@NotNull @NotEmpty @Length(max = 35)
	private String email;
	private TelefoneAtualizacaoForm telefones;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TelefoneAtualizacaoForm getTelefones() {
		return telefones;
	}

	public void setTelefones(TelefoneAtualizacaoForm telefones) {
		this.telefones = telefones;
	}

	public Pessoa atualizar(Long id, PessoaRepository pessoaRepository) {
		Pessoa pessoa = pessoaRepository.getOne(id);
		pessoa.setNome(this.nome);
		pessoa.setCpf(this.cpf);
		pessoa.setEmail(this.email);
		pessoa.getTelefone().setResidencial(this.telefones.getResidencial());
		pessoa.getTelefone().setComercial(this.telefones.getComercial());
		pessoa.getTelefone().setCelular(this.telefones.getCelular());
		return pessoa;
	}

}
