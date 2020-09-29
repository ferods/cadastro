package com.feros.contatos.cadastro.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.feros.contatos.cadastro.model.Pessoa;

public class PessoaEnderecosDTO {
	
	private String nome;
	private Long cpf;
	private List<EnderecoDTO> enderecos = new ArrayList<EnderecoDTO>();
	private String email;
	private TelefoneDTO telefone;

	public PessoaEnderecosDTO(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
	    enderecos.addAll(pessoa.getEnderecos().stream().map(EnderecoDTO::new).collect(Collectors.toList()));
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

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
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
	

	public static List<PessoaEnderecosDTO> converter(List<Pessoa> pessoas) {
	    return pessoas.stream().map(PessoaEnderecosDTO::new).collect(Collectors.toList());
	}

}
