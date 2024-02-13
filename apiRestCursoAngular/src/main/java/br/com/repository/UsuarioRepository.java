package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
