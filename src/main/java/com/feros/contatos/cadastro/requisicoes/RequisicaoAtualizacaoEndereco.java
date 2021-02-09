package com.feros.contatos.cadastro.requisicoes;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.feros.contatos.cadastro.models.Endereco;
import com.feros.contatos.cadastro.models.Uf;
import com.feros.contatos.cadastro.repositories.EnderecoRepository;

public class RequisicaoAtualizacaoEndereco {
	
	private String cepAtualizar;
	private String logradouroAtualizar;
	private String numeroAtualizar;
	private String complementoAtualizar;
	private String bairroAtualizar;
	private String cidadeAtualizar;
	private String ufAtualizar;
	
	
	
	public String getCepAtualizar() {
		return cepAtualizar;
	}
	public void setCepAtualizar(String cepAtualizar) {
		this.cepAtualizar = cepAtualizar;
	}
	public String getLogradouroAtualizar() {
		return logradouroAtualizar;
	}
	public void setLogradouroAtualizar(String logradouroAtualizar) {
		this.logradouroAtualizar = logradouroAtualizar;
	}
	public String getNumeroAtualizar() {
		return numeroAtualizar;
	}
	public void setNumeroAtualizar(String numeroAtualizar) {
		this.numeroAtualizar = numeroAtualizar;
	}
	public String getComplementoAtualizar() {
		return complementoAtualizar;
	}
	public void setComplementoAtualizar(String complementoAtualizar) {
		this.complementoAtualizar = complementoAtualizar;
	}
	public String getBairroAtualizar() {
		return bairroAtualizar;
	}
	public void setBairroAtualizar(String bairroAtualizar) {
		this.bairroAtualizar = bairroAtualizar;
	}
	public String getCidadeAtualizar() {
		return cidadeAtualizar;
	}
	public void setCidadeAtualizar(String cidadeAtualizar) {
		this.cidadeAtualizar = cidadeAtualizar;
	}
	public String getUfAtualizar() {
		return ufAtualizar;
	}
	public void setUfAtualizar(String ufAtualizar) {
		this.ufAtualizar = ufAtualizar;
	}
	
	
	public ResponseEntity<Endereco> atualizar(Long id, EnderecoRepository enderecoRepository) {
		
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
		
		if(!enderecoOpt.isPresent()) return ResponseEntity.notFound().build();	
		
		Endereco endereco = enderecoOpt.get();
		endereco.setCep(this.cepAtualizar);
		endereco.setLogradouro(this.logradouroAtualizar);
		endereco.setNumero(this.numeroAtualizar);
		endereco.setBairro(this.bairroAtualizar);
		endereco.setCidade(this.cidadeAtualizar);
		endereco.setUf(Uf.valueOf(this.ufAtualizar.toUpperCase()));
		
		enderecoRepository.save(endereco);
		
		return ResponseEntity.ok(endereco );
	}
	
	
	
	
	

}
