package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

import com.example.demo.entities.Usuario;

// A interface UsuarioRepository estende JpaRepository para fornecer operações CRUD para a entidade Usuario
// Anotação opcional que indica que a interface é um repositório
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	// Não são necessários métodos adicionais, pois JpaRepository já fornece métodos
	// como
	// save(), findById(),
}