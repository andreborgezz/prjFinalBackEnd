package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

import com.example.demo.entities.ItensCompra;

// A interface ItensCompraRepository estende JpaRepository para fornecer operações CRUD para a entidade ItensCompra
public interface ItensCompraRepository extends JpaRepository<ItensCompra, Long> {
    // Não são necessários métodos adicionais, pois JpaRepository já fornece métodos como 
    // save(), findById(), findAll(), deleteById(), etc.
}
