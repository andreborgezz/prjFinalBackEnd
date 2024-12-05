package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Ingresso;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    // Não são necessários métodos adicionais, pois JpaRepository já fornece métodos como 
    // save(), findById(), findAll(), deleteById(), etc.
}
