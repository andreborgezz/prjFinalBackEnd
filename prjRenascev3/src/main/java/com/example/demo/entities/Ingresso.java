package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingresso {

	// Identifica o campo id como a chave primária, gerada automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Relacionamento muitos-para-um com a entidade Evento
	@ManyToOne
	@JoinColumn(name = "evento_id") // Coluna que faz a junção com a tabela de eventos
	private Evento evento;

	// Relacionamento muitos-para-um com a entidade Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id") // Coluna que faz a junção com a tabela de usuários
	private Usuario usuario;

	// Quantidade de ingressos comprados
	private Integer quantidade;

	// Getter e Setter para o ID do ingresso
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getter e Setter para o evento associado ao ingresso
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	// Getter e Setter para o usuário associado ao ingresso
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getter e Setter para a quantidade de ingressos comprados
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
