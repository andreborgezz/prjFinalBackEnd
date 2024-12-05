package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

import com.example.demo.entities.Compra;

// A interface CompraRepository estende JpaRepository para fornecer operações CRUD para a entidade Compra
public interface CompraRepository extends JpaRepository<Compra, Long> {
    // Não são necessários métodos adicionais, pois JpaRepository já fornece métodos como 
    // save(), findById(), findAll(), deleteById(), etc.
}
