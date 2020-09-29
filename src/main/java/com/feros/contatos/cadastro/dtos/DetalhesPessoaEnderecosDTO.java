package com.feros.contatos.cadastro.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.feros.contatos.cadastro.model.Pessoa;

public class DetalhesPessoaEnderecosDTO {
	
	private Long id;
	private String nome;
	private Long cpf;
	private List<DetalhesEnderecoDTO> enderecos = new ArrayList<DetalhesEnderecoDTO>();
	private String email;
	private TelefoneDTO telefone;
	private LocalDateTime dataEntrada;

	public DetalhesPessoaEnderecosDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
	    enderecos.addAll(pessoa.getEnderecos().stream().map(DetalhesEnderecoDTO::new).collect(Collectors.toList()));
		this.email = pessoa.getEmail();
		this.dataEntrada = pessoa.getDataEntrada();
		this.telefone = TelefoneDTO.converter(pessoa);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<DetalhesEnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<DetalhesEnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public TelefoneDTO getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneDTO telefone) {
		this.telefone = telefone;
	}
	

	public static List<DetalhesPessoaEnderecosDTO> converter(List<Pessoa> pessoas) {
	    return pessoas.stream().map(DetalhesPessoaEnderecosDTO::new).collect(Collectors.toList());
	}

	

}
