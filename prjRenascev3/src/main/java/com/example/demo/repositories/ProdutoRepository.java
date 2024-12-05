package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

import com.example.demo.entities.Produto;

// A interface ProdutoRepository estende JpaRepository para fornecer operações CRUD para a entidade Produto
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Não são necessários métodos adicionais, pois JpaRepository já fornece métodos como 
    // save(), findById(), findAll(), deleteById(), etc.
}
