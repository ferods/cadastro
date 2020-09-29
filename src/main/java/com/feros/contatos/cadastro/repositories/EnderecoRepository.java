package com.feros.contatos.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feros.contatos.cadastro.model.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	List<Endereco> findByCodigoSecundario(Long codigoSecundario);

	List<Endereco> findByCep(Integer cep);

}
