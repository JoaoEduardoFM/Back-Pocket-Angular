package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.model.entity.Numero;

@Repository
public interface NumeroRepository extends JpaRepository<Numero, Long>{

	public List<Numero> findByUsuarioPk(@Param("usuarioPk") Long id);

	}

