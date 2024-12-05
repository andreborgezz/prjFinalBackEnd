package com.example.demo.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// A classe representa a entidade Evento no banco de dados
@Entity
public class Evento {

    // Identifica o campo id como a chave primária, gerada automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Nome do evento
    private String nome;
    
    // Descrição do evento
    private String descricao;
    
    // Data do evento
    private LocalDate data;
    
    // Enum para definir o tipo do evento (SHOW, ESPORTE, CULTURAL)
    @Enumerated(EnumType.STRING)  // Salva o valor do enum como string no banco
    private TipoEvento tipo;
    
    // Quantidade de ingressos disponíveis para o evento
    private Integer quantidadeIngressos;

    // Getter e Setter para o ID do evento
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter para o nome do evento
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para a descrição do evento
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter e Setter para a data do evento
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    // Getter e Setter para o tipo de evento
    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    // Getter e Setter para a quantidade de ingressos disponíveis
    public Integer getQuantidadeIngressos() {
        return quantidadeIngressos;
    }

    public void setQuantidadeIngressos(Integer quantidadeIngressos) {
        this.quantidadeIngressos = quantidadeIngressos;
    }
    
    // Enum que define os tipos possíveis de evento
    public enum TipoEvento {
        SHOW, ESPORTE, CULTURAL;
    }
}
