package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Nome do usuário
	private String nome;

	// Email do usuário
	private String email;

	// Senha do usuário
	private String senha;

	// Enum para definir o tipo do usuário (VIP ou NORMAL)
	@Enumerated(EnumType.STRING) // Salva o valor do enum como string no banco
	private TipoUsuario tipo;

	// Getter e Setter para o ID do usuário
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getter e Setter para o nome do usuário
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// Getter e Setter para o email do usuário
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Getter e Setter para a senha do usuário
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Getter e Setter para o tipo de usuário
	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	// Enum que define os tipos possíveis de usuário
	public enum TipoUsuario {
		VIP, NORMAL;
	}
}
