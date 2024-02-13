package br.com.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.model.entity.Usuario;
import br.com.repository.UsuarioRepository;

public class VerificaCampos {

	@Autowired
	UsuarioRepository repository;

	public Boolean verificaId(Long id) {
		try {
			Optional<Usuario> veiculoCadastrado = repository.findById(id);
			if (veiculoCadastrado.get().getId() != null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
