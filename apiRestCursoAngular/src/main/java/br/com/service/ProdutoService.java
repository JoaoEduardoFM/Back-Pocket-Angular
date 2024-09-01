package br.com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.model.entity.Produtos;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Service
@AllArgsConstructor
public class ProdutoService {
	@Autowired
	ProdutoRepository repository;

	public ResponseEntity<?> salvaRegistro(@Valid Produtos usuario, @ApiIgnore ResponseRest response) {
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

	public List<Produtos> buscaProdutos() {
		List<Produtos> list = repository.findAll();
		return list;
	}


	public Boolean validaSeExisteId(Long id) {
		Optional<Produtos> buscaPorID = repository.findById(id);
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