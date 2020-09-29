package com.feros.contatos.cadastro.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.feros.contatos.cadastro.model.Endereco;
import com.feros.contatos.cadastro.model.Uf;

public class DetalhesEnderecoDTO {

	private Long id;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private Integer cep;
	private String cidade;
	private Uf uf;
	private Long codigoSecundario;


	public DetalhesEnderecoDTO(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();
		this.cidade = endereco.getCidade();
		this.uf = endereco.getUf();
		this.codigoSecundario = endereco.getCodigoSecundario();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}
	
	public Long getCodigoSecundario() {
		return codigoSecundario;
	}
	
	public void setCodigoSecundario(Long codigoSecundario) {
		this.codigoSecundario = codigoSecundario;
	}

	public static List<DetalhesEnderecoDTO> converter(List<Endereco> enderecos) {
		return enderecos.stream().map(DetalhesEnderecoDTO::new).collect(Collectors.toList());
	}

}
