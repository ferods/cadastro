package com.feros.contatos.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feros.contatos.cadastro.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	List<Pessoa> findByNome(String nomePessoa);

	List<Pessoa> findByCpf(Long pessoaCpf);

}
