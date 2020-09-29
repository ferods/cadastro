package com.feros.contatos.cadastro.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.feros.contatos.cadastro.model.Pessoa;

public class PessoaDTO {

	private String nome;
	private Long cpf;
	private String email;
	private TelefoneDTO telefone;

	public PessoaDTO(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.email = pessoa.getEmail();
		this.telefone = TelefoneDTO.converter(pessoa);
	}


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
