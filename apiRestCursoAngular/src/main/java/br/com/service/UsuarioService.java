package br.com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.model.entity.Usuario;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Service
@AllArgsConstructor
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;

	public ResponseEntity<?> salvaRegistro(@Valid Usuario usuario, @ApiIgnore ResponseRest response) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}

	public ResponseEntity<ResponseRest> deleta(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		if (!validaSeExisteId(id)) {
			response.setMessage("Id não existente.");
			response.setType(messageType.ATENCAO);
			return new ResponseEntity<ResponseRest>(response, HttpStatus.BAD_REQUEST);

		}
		repository.deleteById(id);
		response.setMessage("Registro excluído com sucesso.");
		response.setType(messageType.SUCESSO);
		return new ResponseEntity<ResponseRest>(response, HttpStatus.OK);

	}

	public ResponseEntity<?> buscaPorID(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		if (!validaSeExisteId(id)) {
			response.setMessage("Id não existente.");
			response.setType(messageType.ATENCAO);
			return new ResponseEntity<ResponseRest>(response, HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
	}
	
	public ResponseEntity<?> buscaPorNome(@PathVariable String nome, @ApiIgnore ResponseRest response) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findByNomeContainingIgnoreCase(nome));
	}
	
	public ResponseEntity<?> buscaPorCpf(@PathVariable String cpf) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findByCpfContainingIgnoreCase(cpf));
	}
	
	public ResponseEntity<?> buscaPorLogin(@PathVariable String login, @ApiIgnore ResponseRest response) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findByLoginContainingIgnoreCase(login));
	}
	
	public ResponseEntity<?> buscaPorLoginSenha(@PathVariable String login, @PathVariable String senha) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findByLoginAndSenha(login, senha));
	}

	public ResponseEntity<Usuario> create(Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}

	public Usuario updatePorId(Long id, Usuario usuario) {
		return repository.save(usuario);
	}

	public List<Usuario> buscaUsuarios() {
		List<Usuario> list = repository.findAll();
		return list;
	}
	
	public ResponseEntity<Page<Usuario>> buscaUsuariosPage(int pagina) {
		PageRequest page = PageRequest.of(pagina, 5, Sort.by("nome"));
		Page<Usuario> list = repository.findAll(page);
		return new ResponseEntity<Page<Usuario>>(list,HttpStatus.OK);
	}
	
	
	public ResponseEntity<Page<Usuario>> buscaNomePage(String nome ,int pagina) {
		 PageRequest pageRequest = PageRequest.of(pagina, 5, Sort.by("nome"));
	        Page<Usuario> list = repository.findAll(pageRequest);
	        list.forEach(usuario -> usuario.setNome(nome));
	        return new ResponseEntity<>(list, HttpStatus.OK);
	    }

	public Boolean validaSeExisteId(Long id) {
		Optional<Usuario> buscaPorID = repository.findById(id);
		try {
			if (buscaPorID.get().getId() != null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}