package com.generation.crudfarmacia.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.server.ResponseStatusException;

import com.generation.crudfarmacia.Model.Produto;
import com.generation.crudfarmacia.Repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos") // Endpoint base
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	// | BUSCA TODOS OS PRODUTOS NO REPOSITORIO
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return ResponseEntity.ok(produtos); // RETORNA A LISTA COMO RESPOSTA
	}

	@GetMapping("/{id}") 
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(produto -> ResponseEntity.ok(produto))

				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto, @PathVariable long id) {
		return produtoRepository.findById(id).map(resposta -> {
			produto.setId(id);
			return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
		}

		produtoRepository.deleteById(id);
	}

}
