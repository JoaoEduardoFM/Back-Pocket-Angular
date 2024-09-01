package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public List<Usuario> findByNomeContainingIgnoreCase(@Param("nome") String nome);
	
	public List<Usuario> findByLoginContainingIgnoreCase(@Param("login") String login);
	
	public List<Usuario> findByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);
	
	public List<Usuario> findByCpfContainingIgnoreCase(@Param("cpf") String cpf);
	
	@Query("SELECT MAX(u.id) FROM Usuario u")
    Long findMaxId();

}

