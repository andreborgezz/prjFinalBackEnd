package com.example.demo.services;

import java.util.List; // Importa a classe List para manipulação de listas
import java.util.Optional; // Importa a classe Optional para tratar valores nulos

import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação para injeção de dependência
import org.springframework.stereotype.Service; // Marca esta classe como um serviço gerenciado pelo Spring

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;

@Service // Indica que esta classe é um serviço gerenciado pelo Spring
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository; // Repositório para manipulação de usuários

	// Método para buscar todos os usuários
	public List<Usuario> findAll() {
		return usuarioRepository.findAll(); // Retorna todos os usuários do repositório
	}

	// Método para buscar usuário por ID
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id); // Retorna um usuário específico ou Optional vazio se não encontrado
	}

	// Método para salvar um novo usuário
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario); // Salva o usuário no repositório
	}

	// Método para deletar usuário por ID
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id); // Deleta o usuário com o ID informado
	}

	// Método para atualizar um usuário
	public Usuario update(Long id, Usuario usuarioAtualizado) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(id); // Busca o usuário pelo ID
		if (usuarioExistente.isPresent()) { // Verifica se o usuário existe
			Usuario usuario = usuarioExistente.get(); // Obtém o usuário existente

			// Atualiza os dados do usuário
			usuario.setNome(usuarioAtualizado.getNome());
			usuario.setEmail(usuarioAtualizado.getEmail());
			usuario.setTipo(usuarioAtualizado.getTipo());
			usuario.setSenha(usuarioAtualizado.getSenha());

			// Salva o usuário atualizado no repositório
			return usuarioRepository.save(usuario);
		}
		return null; // Ou lança exceção caso o usuário não exista
	}
}
