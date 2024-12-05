package com.example.demo.controllers;

import java.util.List;

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

import com.example.demo.entities.ItensCompra;
import com.example.demo.services.ItensCompraService;



@RestController
@RequestMapping("/itens-compra") // Define o mapeamento base para a URL "/itens-compra"
public class ItensCompraController {

	@Autowired
	private ItensCompraService itensCompraService; // Injeta o serviço ItensCompraService

	// Listar todos os itens de compra
	@GetMapping
	public ResponseEntity<List<ItensCompra>> getAllItensCompra() {
		List<ItensCompra> itens = itensCompraService.findAll(); // Busca todos os itens de compra
		return ResponseEntity.ok(itens); // Retorna a lista com status 200 OK
	}

	// Buscar item de compra por ID
	@GetMapping("/{id}")
	public ResponseEntity<ItensCompra> getItensCompraById(@PathVariable Long id) {
		ItensCompra item = itensCompraService.findById(id);
		if(item != null) {
			return ResponseEntity.ok(item);
		}else{
			return ResponseEntity.notFound().build();
		}
	}	


	// Criar novo item de compra
	@PostMapping
	public ResponseEntity<ItensCompra> createItensCompra(@RequestBody ItensCompra itensCompra) {
		ItensCompra novoItensCompra = itensCompraService.save(itensCompra); // Salva novo item de compra
		return ResponseEntity.ok(novoItensCompra); // Retorna o item criado com status 200 OK
	}

	// Atualizar item de compra existente
	@PutMapping("/{id}")
	public ResponseEntity<ItensCompra> updateItensCompra(@PathVariable Long id, @RequestBody ItensCompra itensCompraAtualizado) {
		ItensCompra itemAtualizadoResp = itensCompraService.update(id, itensCompraAtualizado); // Atualiza o item de compra pelo ID
		if (itemAtualizadoResp != null) {
			return ResponseEntity.ok(itemAtualizadoResp); // Retorna o item atualizado com status 200 OK
		} else {
			return ResponseEntity.notFound().build(); // Retorna status 404 se o item não for encontrado
		}
	}

	// Deletar item de compra
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItensCompra(@PathVariable Long id) {
		itensCompraService.deleteById(id); // Deleta o item de compra pelo ID
		return ResponseEntity.noContent().build(); // Retorna status 204 No Content após a deleção
	}
	
}
