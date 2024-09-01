package br.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.entity.Produtos;
import br.com.model.response.ResponseRest;
import br.com.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("Produto")
@Api(tags = { "Produto" }, description = " Serviços relacionado a gerenciamento de produtos.")
public class ProdutoController {

	ProdutoService service;

	@PostMapping("cadastraProduto")
	@ApiOperation(
			value = "Cadastra e atualiza produtos.", 
			notes = "Cadastra e atualiza produtos.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> salvaRegistro(@RequestBody @Valid Produtos numero, @ApiIgnore ResponseRest response) {
		return service.salvaRegistro(numero, response);
	}

	@DeleteMapping("deletaProduto/{id}")
	@ApiOperation(
			value = "Deleta número.", 
			notes = "Deleta número.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleta(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return service.deleta(id, response);
	}
	
	@GetMapping("buscaNomeProdutos/{nome}")
	@ApiOperation(
			value = "Busca por Nome.", 
			notes = "Busca registro por nome.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> buscaPorNome(@PathVariable String nome, @ApiIgnore ResponseRest response) {
		return service.buscaPorNome(nome, response);
	}
	
	@GetMapping("buscaProdutos")
	@ApiOperation(
			value = "Busca por número do usuário.", 
			notes = "Busca por número do usuário.")
	@ResponseStatus(HttpStatus.OK)
	public List<Produtos> buscaPorProdutos(@ApiIgnore ResponseRest response) {
		return service.buscaProdutos();
	}

	@GetMapping("buscaPorID/{id}")
	@ApiOperation(
			value = "Busca por ID.", 
			notes = "Busca número por id.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> buscaPorID(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return service.buscaPorID(id, response);
	}
	
	@GetMapping(value = "buscaProdutos/page/{pagina}",produces = "application/json")
	@ApiOperation(
			value = "Busca por Produtos.", 
			notes = "Lista Produtos paginados.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<Produtos>> listaTodosPaginado(@PathVariable ("pagina") int pagina) {
		return service.buscaProdutoPage(pagina);

	}
}

