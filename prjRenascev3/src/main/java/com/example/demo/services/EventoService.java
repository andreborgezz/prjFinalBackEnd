package com.example.demo.services;

import java.util.List; // Importa a classe List para manipulação de listas
import java.util.Optional; // Importa a classe Optional para tratamento de valores que podem ser nulos

import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação para injeção de dependência
import org.springframework.stereotype.Service; // Importa a anotação Service

import com.example.demo.entities.Evento;
import com.example.demo.repositories.EventoRepository;

@Service // Indica que esta classe é um serviço gerenciado pelo Spring
public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository; // Repositório para operações com a entidade Evento

	// Método para encontrar todos os eventos
	public List<Evento> findAll() {
		return eventoRepository.findAll(); // Retorna todos os eventos do repositório
	}

	// Método para encontrar um evento pelo ID
	public Optional<Evento> findById(Long id) {
		return eventoRepository.findById(id); // Retorna um evento se encontrado, caso contrário, retorna um Optional vazio
	}

	// Método para salvar um novo evento
	public Evento save(Evento evento) {
		return eventoRepository.save(evento); // Salva o evento no repositório
	}

	// Método para deletar um evento pelo ID
	public void deleteById(Long id) {
		eventoRepository.deleteById(id); // Deleta o evento do repositório
	}

	// Método para atualizar um evento
	public Evento update(Long id, Evento eventoAtualizado) {
		Optional<Evento> eventoExistente = eventoRepository.findById(id); // Busca o evento existente pelo ID
		if (eventoExistente.isPresent()) { // Verifica se o evento existe
			Evento evento = eventoExistente.get(); // Obtém o evento existente

			// Atualiza os dados do evento
			evento.setNome(eventoAtualizado.getNome());
			evento.setTipo(eventoAtualizado.getTipo());
			evento.setData(eventoAtualizado.getData());
			evento.setDescricao(eventoAtualizado.getDescricao());
			evento.setQuantidadeIngressos(eventoAtualizado.getQuantidadeIngressos());

			return eventoRepository.save(evento); // Salva o evento atualizado no repositório
		}

		return null; // Ou lançar exceção caso o evento não exista
	}
}
