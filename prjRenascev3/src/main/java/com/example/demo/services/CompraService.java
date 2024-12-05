package com.example.demo.services;

import java.util.List; // Importa a classe List para manipulação de listas
import java.util.Optional; // Importa a classe Optional para tratamento de valores que podem ser nulos

import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação para injeção de dependência
import org.springframework.stereotype.Service; // Importa a anotação Service

import com.example.demo.entities.Compra;
import com.example.demo.entities.ItensCompra;
import com.example.demo.entities.Produto;
import com.example.demo.repositories.CompraRepository;
import com.example.demo.repositories.ItensCompraRepository;
import com.example.demo.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException; // Importa a exceção para entidades não encontradas

@Service // Indica que esta classe é um serviço gerenciado pelo Spring
public class CompraService {
	
	@Autowired
	private CompraRepository compraRepository; // Repositório para operações com a entidade Compra
	
	@Autowired
	private ItensCompraRepository itensCompraRepository; // Repositório para operações com a entidade ItensCompra
	
	@Autowired
	private ProdutoRepository produtoRepository; // Repositório para operações com a entidade Produto
	
	// Método para encontrar todas as compras
	public List<Compra> findAll() {
		return compraRepository.findAll(); // Retorna todas as compras do repositório
	}
	
	// Método para encontrar uma compra pelo ID
	public Optional<Compra> findById(Long id) {
		return compraRepository.findById(id); // Retorna uma compra se encontrada, caso contrário, retorna um Optional vazio
	}
	
	// Método para salvar uma nova compra
	public Compra save(Compra compra) {
		return compraRepository.save(compra); // Salva a compra no repositório
	}
	
	// Método para deletar uma compra pelo ID
	public void deleteById(Long id) {
		compraRepository.deleteById(id); // Deleta a compra do repositório
	}
	
	// Método para atualizar uma compra e seus itens relacionados
	public Compra update(Long id, Compra compraAtualizada) {
		Optional<Compra> compraExistente = compraRepository.findById(id); // Busca a compra existente pelo ID
		if (compraExistente.isPresent()) { // Verifica se a compra existe
			Compra compra = compraExistente.get(); // Obtém a compra existente

			// Atualiza os dados da compra
			compra.setUsuario(compraAtualizada.getUsuario());
			compra.setProdutos(compraAtualizada.getProdutos());
			compra.setValorTotal(compraAtualizada.getValorTotal());

			// Limpa os itens antigos e salva os novos
			itensCompraRepository.deleteAll(compra.getItens()); // Deleta os itens antigos da compra
			for (ItensCompra itens : compraAtualizada.getItens()) { // Itera sobre os novos itens da compra
				// Busca o produto existente pelo ID
				Produto produto = produtoRepository.findById(itens.getProduto().getId())
						.orElseThrow(() -> new EntityNotFoundException(
								"Produto não encontrado com ID: " + itens.getProduto().getId()));
				// Aqui você pode adicionar lógica para adicionar os itens à compra, se necessário
			}

			return compraRepository.save(compra); // Salva a compra atualizada no repositório
		}
		return null; // Ou lançar exceção caso a compra não exista
	}
}
