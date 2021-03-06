package com.feros.contatos.cadastro.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.feros.contatos.cadastro.models.Pessoa;


public class PessoaDTO {

	private Long cpf;
	private String nome;
	private String email;
	private TelefoneDTO telefone;

	public PessoaDTO(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.email = pessoa.getEmail();
		this.telefone = new TelefoneDTO(pessoa.getTelefone());
	}


	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public TelefoneDTO getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneDTO telefone) {
		this.telefone = telefone;
	}
	
	
	

	public static List<PessoaDTO> converter(List<Pessoa> pessoas) {
		return pessoas.stream().map(PessoaDTO::new).collect(Collectors.toList());
	}
	
	
}
