package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

import com.example.demo.entities.Evento;

// A interface EventoRepository estende JpaRepository para fornecer operações CRUD para a entidade Evento
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Não são necessários métodos adicionais, pois JpaRepository já fornece métodos como 
    // save(), findById(), findAll(), deleteById(), etc.
}
