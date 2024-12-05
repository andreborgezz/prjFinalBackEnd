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

import com.example.demo.entities.Evento;
import com.example.demo.services.EventoService;

@RestController
@RequestMapping("/eventos") // Define o mapeamento de URL base para "eventos"
public class EventoController {

	@Autowired
	private EventoService eventoService; // Injeta o serviço EventoService

	// Listar todos os eventos
	@GetMapping
	public ResponseEntity<List<Evento>> getAllEventos() {
		List<Evento> eventos = eventoService.findAll(); // Busca todos os eventos
		return ResponseEntity.ok(eventos); // Retorna a lista de eventos com status 200 OK
	}

	// Buscar evento por ID
	@GetMapping("/{id}")
	public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
		Optional<Evento> evento = eventoService.findById(id); // Busca o evento pelo ID
		return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna o evento ou status 404 se não encontrado
	}

	// Criar novo evento
	@PostMapping
	public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
		Evento novoEvento = eventoService.save(evento); // Salva o novo evento
		return ResponseEntity.ok(novoEvento); // Retorna o evento criado com status 200 OK
	}

	// Atualizar evento existente
	@PutMapping("/{id}")
	public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento eventoAtualizado) {
		Evento eventoAtualizadoResp = eventoService.update(id, eventoAtualizado); // Atualiza o evento pelo ID
		if (eventoAtualizadoResp != null) {
			return ResponseEntity.ok(eventoAtualizadoResp); // Retorna o evento atualizado com status 200 OK
		} else {
			return ResponseEntity.notFound().build(); // Retorna status 404 se o evento não for encontrado
		}
	}

	// Deletar evento
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
		eventoService.deleteById(id); // Deleta o evento pelo ID
		return ResponseEntity.noContent().build(); // Retorna status 204 No Content após a deleção
	}
}
