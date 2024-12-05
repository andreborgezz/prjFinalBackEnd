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

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;


@RestController
@RequestMapping("/usuarios") // Define o mapeamento base para a URL "/usuarios"
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService; // Injeta o serviço UsuarioService

	// Listar todos os Usuarios
	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll(); // Busca todos os usuários
		return ResponseEntity.ok(usuarios); // Retorna a lista com status 200 OK
	}

	// Buscar Usuario por ID
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioService.findById(id); // Busca usuário pelo ID
		return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna o usuário ou status 404 se não encontrado
	}

	// Criar um novo Usuario
	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
		Usuario novoUsuario = usuarioService.save(usuario); // Salva novo usuário
		return ResponseEntity.ok(novoUsuario); // Retorna o usuário criado com status 200 OK
	}

	// Atualizar Usuario existente
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable long id, @RequestBody Usuario usuarioAtualizado) {
		Usuario usuarioAtualizadoResp = usuarioService.update(id, usuarioAtualizado); // Atualiza o usuário pelo ID
		if (usuarioAtualizadoResp != null) {
			return ResponseEntity.ok(usuarioAtualizadoResp); // Retorna o usuário atualizado com status 200 OK
		} else {
			return ResponseEntity.notFound().build(); // Retorna status 404 se o usuário não for encontrado
		}
	}

	// Deletar Usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable long id) {
		usuarioService.deleteById(id); // Deleta o usuário pelo ID
		return ResponseEntity.noContent().build(); // Retorna status 204 No Content após a deleção
	}
}
