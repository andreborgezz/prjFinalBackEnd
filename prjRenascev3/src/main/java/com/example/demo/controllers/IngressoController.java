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

import com.example.demo.entities.Ingresso;
import com.example.demo.services.IngressoService;



@RestController
@RequestMapping("/ingressos") // Define o mapeamento de URL base para "ingressos"
public class IngressoController {

	@Autowired
	private IngressoService ingressoService; // Injeta o serviço IngressoService

	// Listar todos os ingressos
	@GetMapping
	public ResponseEntity<List<Ingresso>> getAllIngressos() {
		List<Ingresso> ingressos = ingressoService.findAll(); // Busca todos os ingressos
		return ResponseEntity.ok(ingressos); // Retorna a lista de ingressos com status 200 OK
	}

	// Buscar ingresso por ID
	@GetMapping("/{id}")
	public ResponseEntity<Ingresso> getIngressoById(@PathVariable Long id) {
		Optional<Ingresso> ingresso = ingressoService.findById(id); // Busca o ingresso pelo ID
		return ingresso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna o ingresso ou status 404 se não encontrado
	}

	// Criar novo ingresso
	@PostMapping
	public ResponseEntity<Ingresso> createIngresso(@RequestBody Ingresso ingresso) {
		Ingresso novoIngresso = ingressoService.save(ingresso); // Salva o novo ingresso
		return ResponseEntity.ok(novoIngresso); // Retorna o ingresso criado com status 200 OK
	}

	// Atualizar ingresso existente
	@PutMapping("/{id}")
	public ResponseEntity<Ingresso> updateIngresso(@PathVariable Long id, @RequestBody Ingresso ingressoAtualizado) {
		Ingresso ingressoAtualizadoResp = ingressoService.update(id, ingressoAtualizado); // Atualiza o ingresso pelo ID
		if (ingressoAtualizadoResp != null) {
			return ResponseEntity.ok(ingressoAtualizadoResp); // Retorna o ingresso atualizado com status 200 OK
		} else {
			return ResponseEntity.notFound().build(); // Retorna status 404 se o ingresso não for encontrado
		}
	}

	// Deletar ingresso
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteIngresso(@PathVariable Long id) {
		ingressoService.deleteById(id); // Deleta o ingresso pelo ID
		return ResponseEntity.noContent().build(); // Retorna status 204 No Content após a deleção
	}
}
