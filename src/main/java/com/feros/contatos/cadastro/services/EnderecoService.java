package com.feros.contatos.cadastro.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.feros.contatos.cadastro.dtos.DetalhesEnderecoDTO;
import com.feros.contatos.cadastro.dtos.EnderecoDTO;
import com.feros.contatos.cadastro.dtos.PessoaEnderecosDTO;
import com.feros.contatos.cadastro.formularios.EnderecoForm;
import com.feros.contatos.cadastro.formularios.VinculoForm;
import com.feros.contatos.cadastro.model.Endereco;
import com.feros.contatos.cadastro.model.Pessoa;
import com.feros.contatos.cadastro.repositories.EnderecoRepository;
import com.feros.contatos.cadastro.repositories.PessoaRepository;

@Service
@Transactional
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;	

	
	
	public ResponseEntity<DetalhesEnderecoDTO> salvarEndereco(EnderecoForm enderecoForm, UriComponentsBuilder uriBuilder) {
		
		
		Endereco endereco = enderecoForm.converter();		
		enderecoRepository.save(endereco);
		if(enderecoForm.getIdPessoa() != null){
		enderecoForm.vincularEnderecoComPessoa(pessoaRepository, endereco, enderecoRepository);
		}
		
		URI uri = uriBuilder.path("endereco/{id}").buildAndExpand(endereco.getId()).toUri() ;
		return ResponseEntity.created(uri).body(new DetalhesEnderecoDTO(endereco));		
		
	}







	public ResponseEntity<?> deletar(Long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		if(endereco.isPresent()) {
			enderecoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}







	public ResponseEntity<PessoaEnderecosDTO> vincularEnderecos(Long id, VinculoForm form) {				
		Pessoa pessoa = form.vincular(id, enderecoRepository, pessoaRepository);
		return ResponseEntity.ok(new PessoaEnderecosDTO(pessoa));
	}







	public List<EnderecoDTO> listarEnderecos(Integer cep) {
		if(cep == null) {
			List<Endereco> enderecos = enderecoRepository.findAll();
			return EnderecoDTO.converter(enderecos);			
			
		} else {
			List<Endereco> enderecos = enderecoRepository.findByCep(cep);
			return EnderecoDTO.converter(enderecos);
		
		}
	}
	
	
}
