package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.entity.Numero;

public interface NumeroRepository extends JpaRepository<Numero, Long>{

	}

