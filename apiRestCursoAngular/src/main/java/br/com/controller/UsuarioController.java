package br.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.entity.Usuario;
import br.com.model.response.ResponseRest;
import br.com.repository.UsuarioRepository;
import br.com.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("usuario")
@Api(tags = { "Usuario" })
public class UsuarioController {

	UsuarioRepository usuarioRpository;

	UsuarioService service;

	@PostMapping
	@ApiOperation(
			value = "Cadastra usuário.", 
			notes = "Cadastra um usuário.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> salvaRegistroVeiculo(@RequestBody @Valid Usuario usuario, @ApiIgnore ResponseRest response) {
		return service.salvaRegistro(usuario, response);
	}

	@PutMapping("atualizaUsuarioPorId/{id}")
	@ApiOperation(
			value = "Atualiza usuário.", 
			notes = "Atualiza de um usuário.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> atualizaRegistroVeiculo(@RequestBody @Valid Usuario usuario, @ApiIgnore ResponseRest response) {
		return service.atualizaRegistro(usuario, response);
	}

	@DeleteMapping("deletaUsuario/{id}")
	@ApiOperation(
			value = "Deleta usuário.", 
			notes = "Deleta usuário.")
	@CrossOrigin(maxAge = 3600)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleta(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return service.deleta(id, response);
	}

	@GetMapping("buscaPorID/{id}")
	@ApiOperation(
			value = "Busca por ID.", 
			notes = "Busca registro de um usuário.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> buscaPorID(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return service.buscaPorID(id, response);
	}
	
	@GetMapping("buscaPorNome/{nome}")
	@ApiOperation(
			value = "Busca por Nome.", 
			notes = "Busca registro por nome.")
	@CrossOrigin(maxAge = 3600)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> buscaPorNome(@PathVariable String nome, @ApiIgnore ResponseRest response) {
		return service.buscaPorNome(nome, response);
	}
	
	@GetMapping("buscaPorLogin/{login}")
	@ApiOperation(
			value = "Busca por login.", 
			notes = "Busca registro por login.")
	@CrossOrigin(maxAge = 3600)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> buscaPorLogin(@PathVariable String login, @ApiIgnore ResponseRest response) {
		return service.buscaPorLogin(login, response);
	}
	
	@GetMapping("buscaPorCpf/{cpf}")
	@ApiOperation(
			value = "Busca por cpf.", 
			notes = "Busca registro por cpf.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> buscaPorCpf(@PathVariable String cpf, @ApiIgnore ResponseRest response) {
		return service.buscaPorCpf(cpf);
	}

	@GetMapping("buscaUsuarios")
	@ApiOperation(
			value = "Busca por usuários.", 
			notes = "Lista usuário cadastrados.")
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> listaTodos() {
		return service.buscaUsuarios();

	}
}
