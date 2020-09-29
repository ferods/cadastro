package com.feros.contatos.cadastro.formularios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.feros.contatos.cadastro.model.Endereco;
import com.feros.contatos.cadastro.model.Pessoa;
import com.feros.contatos.cadastro.model.Uf;
import com.feros.contatos.cadastro.repositories.EnderecoRepository;
import com.feros.contatos.cadastro.repositories.PessoaRepository;

public class EnderecoForm {

	@NotNull @NotEmpty @Length(max = 50)
	private String logradouro;
	@NotNull @NotEmpty @Length(max = 5)
	private String numero;
	private String complemento;
	@NotNull @NotEmpty @Length(max = 20)
	private String bairro;
	@NotNull @NotEmpty @Length(max = 8)
	private String cep;
	@NotNull @NotEmpty @Length(max = 20)
	private String cidade;
	private Uf uf;
	
	private Long idPessoa;


	
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
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

	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
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
	
	public Long getIdPessoa() {
		return idPessoa;
	}
	
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	
	
	
	public static List<Endereco> converterEnderecosFormParaEnderecosMetodoSalvarPessoasService(List<EnderecoForm> enderecosForm) {		
		return enderecosForm.stream().map(Endereco::new).collect(Collectors.toList());
	}
	
	
	

	//logo abaixo são metodos para concatenação 
	
	public Endereco converter() {		
		Long codigoSecundario = converterStringParaNumero(this.cep, this.numero);
		return new Endereco(this.logradouro, Integer.parseInt(this.numero), this.complemento, this.bairro,Integer.parseInt(this.cep), this.cidade, this.uf, codigoSecundario);		 
	}

	
	public Long converterStringParaNumero(String cep, String numero) {		
	  String resultado = cep + numero;		
	  return  Long.parseLong(resultado); 
	}
	
	
	public Pessoa vincularEnderecoComPessoa(PessoaRepository pessoaRepository, Endereco endereco, EnderecoRepository enderecoRepository) {
		Pessoa pessoa = pessoaRepository.getOne(idPessoa);
		List<Endereco> enderecosRep = enderecoRepository.findByCodigoSecundario(endereco.getCodigoSecundario());
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.addAll(enderecosRep);
		enderecos.addAll(pessoa.getEnderecos());
		pessoa.setEnderecos(enderecos);
		return pessoa;
	}

}
