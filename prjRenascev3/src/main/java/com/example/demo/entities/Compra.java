package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

// A classe representa a entidade Compra no banco de dados
@Entity
public class Compra {

	// Identifica o campo id como a chave primária, gerada automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Relacionamento muitos-para-um com a entidade Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id") // Coluna que faz a junção com a tabela de usuários
	private Usuario usuario;

	// Relacionamento um-para-muitos com a entidade Produto
	@OneToMany
	@JoinColumn(name = "compra_id") // Coluna que faz a junção com a tabela de produtos
	private List<Produto> produtos;

	// Relacionamento um-para-muitos com a entidade ItensCompra, com remoção em
	// cascata
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItensCompra> itens;

	// Valor total da compra
	private Double valorTotal;

	// Getter e Setter para o ID da compra
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getter e Setter para o usuário associado à compra
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getter e Setter para a lista de produtos da compra
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	// Getter e Setter para o valor total da compra
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	// Getter e Setter para a lista de itens da compra
	public List<ItensCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItensCompra> itens) {
		this.itens = itens;
	}
}
