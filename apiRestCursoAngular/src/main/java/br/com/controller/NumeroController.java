package br.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.entity.Numero;
import br.com.model.response.ResponseRest;
import br.com.repository.NumeroRepository;
import br.com.service.NumeroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("Numero")
@Api(tags = { "Numero" }, description = " Serviços relacionado a números para contato.")
public class NumeroController {

	NumeroRepository usuarioRpository;

	NumeroService service;

	@PostMapping("cadastraNumero")
	@ApiOperation(
			value = "Cadastra número.", 
			notes = "Cadastra número.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> salvaRegistro(@RequestBody @Valid Numero numero, @ApiIgnore ResponseRest response) {
		return service.salvaRegistro(numero, response);
	}

	@PutMapping("atualizaNumero/{id}")
	@ApiOperation(
			value = "Atualiza número.", 
			notes = "Atualiza número.")
	@ResponseStatus(HttpStatus.OK)	
	public ResponseEntity<?> atualizaRegistro(@RequestBody @Valid Numero numero, @ApiIgnore ResponseRest response) {
		return service.atualizaRegistro(numero, response);
	}

	@DeleteMapping("deletaNumero/{id}")
	@ApiOperation(
			value = "Deleta número.", 
			notes = "Deleta número.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleta(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return service.deleta(id, response);
	}

	@GetMapping("buscaPorID/{id}")
	@ApiOperation(
			value = "Busca por ID.", 
			notes = "Busca número por id.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> buscaPorID(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return service.buscaPorID(id, response);
	}
	
	@GetMapping("buscaPorUsuarioPk/{id}")
	@ApiOperation(
			value = "Busca por número do usuário.", 
			notes = "Busca por número do usuário.")
	@ResponseStatus(HttpStatus.OK)
	public List<Numero> buscaPorUsuarioPk(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return service.buscarNumerosPorUsuarioPk(id);
	}
}
