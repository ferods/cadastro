package com.feros.contatos.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feros.contatos.cadastro.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	List<Endereco> findByCep(String cep);

}
