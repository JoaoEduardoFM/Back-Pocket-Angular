package br.com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.model.entity.Numero;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.repository.NumeroRepository;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Service
@AllArgsConstructor
public class NumeroService {

	@Autowired
	NumeroRepository repository;

	public ResponseEntity<?> salvaRegistro(@Valid Numero numero, @ApiIgnore ResponseRest response) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(numero));
	}

	public ResponseEntity<?> atualizaRegistro(@Valid Numero numero, @ApiIgnore ResponseRest response) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(numero));
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

	public ResponseEntity<Numero> create(Numero numero) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(numero));
	}

	public Numero updatePorId(Long id, Numero numero) {
		return repository.save(numero);
	}

	public List<Numero> buscarRegistros() {
		List<Numero> findAll = repository.findAll();
		return findAll;
	}
	
	public List<Numero> buscarNumerosPorUsuarioPk(Long id) {
		List<Numero> findAll = repository.findByUsuarioPk(id);
		return findAll;
	}

	public Boolean validaSeExisteId(Long id) {
		Optional<Numero> buscaPorID = repository.findById(id);
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