package com.feros.contatos.cadastro.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.feros.contatos.cadastro.models.Pessoa;



public class PessoaDetalhesDTO {
	
	private String nome;
	private Long cpf;
	private String email;
	private TelefoneDTO telefone;
	private List<EnderecoDTO> enderecos = new ArrayList<EnderecoDTO>();
	
	
	
	public PessoaDetalhesDTO(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.email = pessoa.getEmail();
		this.telefone = new TelefoneDTO(pessoa.getTelefone());
		enderecos.addAll(pessoa.getEnderecos().stream().map(EnderecoDTO :: new).collect(Collectors.toList()));
	}

	public PessoaDetalhesDTO() {}

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

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}
	
	
	
	
	

}
