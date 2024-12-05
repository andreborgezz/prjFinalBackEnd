package com.example.demo.services;

import java.util.List;
import java.util.Optional; // Importa a classe Optional para tratar valores nulos

import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação para injeção de dependência
import org.springframework.stereotype.Service; // Marca esta classe como um serviço gerenciado pelo Spring

import com.example.demo.entities.ItensCompra;
import com.example.demo.repositories.ItensCompraRepository;

@Service // Indica que esta classe é um serviço gerenciado pelo Spring
public class ItensCompraService {
	
	@Autowired
	private ItensCompraRepository itensCompraRepository; // Repositório para manipulação de ItensCompra

	// Método para buscar todos os itens de compra
	public List<ItensCompra> findAll() {
		return itensCompraRepository.findAll(); // Retorna todos os itens de compra do repositório
	}

	// Método para buscar um item de compra por ID
	public ItensCompra findById(Long id) {
		return itensCompraRepository.findById(id).orElse(null);
	}

	// Método para salvar um novo item de compra
	public ItensCompra save(ItensCompra itensCompra) {
		return itensCompraRepository.save(itensCompra); // Salva o item de compra no repositório
	}

	// Método para deletar um item de compra por ID
	public void deleteById(Long id) {
		if (!itensCompraRepository.existsById(id));
	}
        
	
	
	// Método para atualizar um item de compra
    public ItensCompra update(Long id, ItensCompra compraAtualizada) {
        Optional<ItensCompra> compraExistente = itensCompraRepository.findById(id); // Busca o item de compra pelo ID
        if (compraExistente.isPresent()) { // Verifica se o item de compra existe
            ItensCompra itensCompra = compraExistente.get(); // Obtém o item de compra existente

            // Atualiza os dados do item de compra
            itensCompra.setCompra(compraAtualizada.getCompra());
            itensCompra.setPrecoUnitario(compraAtualizada.getPrecoUnitario());
            itensCompra.setProduto(compraAtualizada.getProduto());
            itensCompra.setQuantidadeCompra(compraAtualizada.getQuantidadeCompra());

            // Salva o item de compra atualizado no repositório
            return itensCompraRepository.save(itensCompra);
        }
        
        return null; // Ou lança exceção caso o item de compra não exista
    }
    
}
