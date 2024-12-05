package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Evento;
import com.example.demo.entities.Ingresso;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.EventoRepository;
import com.example.demo.repositories.IngressoRepository;
import com.example.demo.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service // Indica que esta classe é um serviço gerenciado pelo Spring
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository; // Repositório para manipulação de ingressos

    @Autowired
    private EventoRepository eventoRepository; // Repositório para manipulação de eventos

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositório para manipulação de usuários

    // Método para buscar todos os ingressos
    public List<Ingresso> findAll() {
        return ingressoRepository.findAll(); // Retorna todos os ingressos do repositório
    }

    // Método para buscar ingresso por ID
    public Optional<Ingresso> findById(Long id) {
        return ingressoRepository.findById(id); // Retorna um ingresso específico ou Optional vazio se não encontrado
    }

    // Método para salvar um ingresso
    public Ingresso save(Ingresso ingresso) {
        // Busca o usuário associado ao ingresso pelo ID
        Usuario usuario = usuarioRepository.findById(ingresso.getUsuario().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuario não foi encontrado com o ID:" + ingresso.getUsuario().getId())); // Lança exceção se o usuário não for encontrado

        ingresso.setUsuario(usuario); // Associa o usuário ao ingresso

        // Busca o evento associado ao ingresso pelo ID
        Evento evento = eventoRepository.findById(ingresso.getEvento().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Evento não foi encontrado com o ID:" + ingresso.getEvento().getId())); // Lança exceção se o evento não for encontrado

        // Verifica se há ingressos disponíveis
        if (evento.getQuantidadeIngressos() < ingresso.getQuantidade()) {
            throw new IllegalArgumentException("Ingressos insuficientes disponíveis para o evento: " + evento.getNome());
        }

        // Reduz a quantidade de ingressos disponíveis para o evento
        evento.setQuantidadeIngressos(evento.getQuantidadeIngressos() - ingresso.getQuantidade());
        eventoRepository.save(evento); // Salva o evento com a nova quantidade de ingressos

        ingresso.setEvento(evento); // Associa o evento ao ingresso

        // Salva o ingresso no repositório
        return ingressoRepository.save(ingresso);
    }


    // Método para atualizar um ingresso
    public Ingresso update(Long id, Ingresso ingressoAtualizado) {
        Optional<Ingresso> ingressoExistente = ingressoRepository.findById(id); // Busca o ingresso pelo ID
        if (ingressoExistente.isPresent()) { // Verifica se o ingresso existe
            Ingresso ingresso = ingressoExistente.get(); // Obtém o ingresso existente

            // Atualiza os dados do ingresso
            ingresso.setUsuario(ingressoAtualizado.getUsuario());
            ingresso.setEvento(ingressoAtualizado.getEvento());
            ingresso.setQuantidade(ingressoAtualizado.getQuantidade());

            // Salva o ingresso atualizado no repositório
            return ingressoRepository.save(ingresso);
        }
        throw new EntityNotFoundException("Ingresso não encontrado com o ID: " + id);
    }

    // Método para deletar um ingresso
    public void deleteById(Long id) {
        if (ingressoRepository.existsById(id)) {
            ingressoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Ingresso não encontrado com o ID: " + id);
        }
    }
}
