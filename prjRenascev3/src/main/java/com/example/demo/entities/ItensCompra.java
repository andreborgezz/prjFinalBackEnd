package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// A classe representa a entidade ItensCompra no banco de dados
@Entity
public class ItensCompra {

    // Identifica o campo id como a chave primária, gerada automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quantidade de itens comprados
    private Integer quantidadeCompra;

    // Preço unitário do produto no momento da compra
    private double precoUnitario;

    // Relacionamento muitos-para-um com a entidade Produto
    @ManyToOne
    @JoinColumn(name = "produto_id")  // Coluna que faz a junção com a tabela de produtos
    private Produto produto;

    // Relacionamento muitos-para-um com a entidade Compra
    @ManyToOne
    @JoinColumn(name = "compra_id")  // Coluna que faz a junção com a tabela de compras
    private Compra compra;

    // Getter e Setter para o ID do item de compra
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter para a quantidade comprada
    public Integer getQuantidadeCompra() {
        return quantidadeCompra;
    }

    public void setQuantidadeCompra(Integer quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }

    // Getter e Setter para o preço unitário
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    // Getter e Setter para o produto associado
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    // Getter e Setter para a compra associada
    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

}
