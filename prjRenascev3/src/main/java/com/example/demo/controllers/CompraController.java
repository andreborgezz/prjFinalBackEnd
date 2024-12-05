package com.example.demo.controllers;

import java.util.List;
import java.util.Optional; // Importa a classe Optional para tratamento de valores nulos

import org.springframework.beans.factory.annotation.Autowired; // Injeção de dependência gerenciada pelo Spring
import org.springframework.http.ResponseEntity; // Resposta HTTP com status
import org.springframework.web.bind.annotation.DeleteMapping; // Define um endpoint para deleção
import org.springframework.web.bind.annotation.GetMapping; // Define um endpoint para GET
import org.springframework.web.bind.annotation.PathVariable; // Define parâmetros no endpoint
import org.springframework.web.bind.annotation.PostMapping; // Define um endpoint para POST
import org.springframework.web.bind.annotation.PutMapping; // Define um endpoint para PUT
import org.springframework.web.bind.annotation.RequestBody; // Captura o corpo da requisição
import org.springframework.web.bind.annotation.RequestMapping; // Mapeamento de URL
import org.springframework.web.bind.annotation.RestController; // Marca a classe como um controlador REST

import com.example.demo.entities.Compra;
import com.example.demo.services.CompraService;


@RestController // Define a classe como um controlador REST
@RequestMapping("/compra") // Mapeia requisições para o caminho /compra
public class CompraController {

	@Autowired
	private CompraService compraService; // Injeção do serviço CompraService

	// Listar todas as compras
	@GetMapping
	public ResponseEntity<List<Compra>> getAllCompras() {
		List<Compra> compra = compraService.findAll(); // Busca todas as compras
		return ResponseEntity.ok(compra); // Retorna a lista de compras com status 200 OK
	}

	// Buscar compra por ID
	@GetMapping("/{id}")
	public ResponseEntity<Compra> getCompraById(@PathVariable Long id) {
		Optional<Compra> compra = compraService.findById(id); // Busca a compra pelo ID
		return compra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna a compra ou
																									// status 404 se não
																									// encontrado
	}

	// Criar uma nova compra
	@PostMapping
	public ResponseEntity<Compra> createCompra(@RequestBody Compra compra) {
		Compra novoCompra = compraService.save(compra); // Salva a nova compra
		return ResponseEntity.ok(novoCompra); // Retorna a compra criada com status 200 OK
	}

	// Atualizar uma compra existente
	@PutMapping("/{id}")
	public ResponseEntity<Compra> updateCompra(@PathVariable Long id, @RequestBody Compra compraAtualizado) {
		Compra compraAtualizadoResp = compraService.update(id, compraAtualizado); // Atualiza a compra pelo ID
		if (compraAtualizadoResp != null) {
			return ResponseEntity.ok(compraAtualizadoResp); // Retorna a compra atualizada com status 200 OK
		} else {
			return ResponseEntity.notFound().build(); // Retorna status 404 se a compra não for encontrada
		}
	}

	// Deletar uma compra
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompra(@PathVariable Long id) {
		compraService.deleteById(id); // Deleta a compra pelo ID
		return ResponseEntity.noContent().build(); // Retorna status 204 No Content após a deleção
	}
}
