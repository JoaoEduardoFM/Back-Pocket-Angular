package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.entity.Produtos;

public interface ProdutoRepository extends JpaRepository<Produtos, Long>{

}
