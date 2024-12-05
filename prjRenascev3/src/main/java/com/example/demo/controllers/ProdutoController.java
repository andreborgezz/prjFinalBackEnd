package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Produto;
import com.example.demo.services.ProdutoService;


@RestController
@RequestMapping("/produtos") // Define o mapeamento base para a URL "/produtos"
public class ProdutoController {
	 
	@Autowired
	private ProdutoService produtoService; // Injeta o serviço ProdutoService

	// Listar todos os Produtos
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos() {
		List<Produto> produtos = produtoService.findAll(); // Busca todos os produtos
		return ResponseEntity.ok(produtos); // Retorna a lista com status 200 OK
	}

	// Buscar Produto por ID
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id); // Busca produto pelo ID
		return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna o produto ou status 404 se não encontrado
	}

	// Criar um novo Produto
	@PostMapping
	public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
		Produto novoProduto = produtoService.save(produto); // Salva novo produto
		return ResponseEntity.ok(novoProduto); // Retorna o produto criado com status 200 OK
	}

	// Atualizar Produto existente
	@PutMapping("/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
		Produto produtoAtualizadoResp = produtoService.update(id, produtoAtualizado); // Atualiza o produto pelo ID
		if (produtoAtualizadoResp != null) {
			return ResponseEntity.ok(produtoAtualizadoResp); // Retorna o produto atualizado com status 200 OK
		} else {
			return ResponseEntity.notFound().build(); // Retorna status 404 se o produto não for encontrado
		}
	}

	// Deletar Produto
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
		produtoService.deleteById(id); // Deleta o produto pelo ID
		return ResponseEntity.noContent().build(); // Retorna status 204 No Content após a deleção
	}
}
