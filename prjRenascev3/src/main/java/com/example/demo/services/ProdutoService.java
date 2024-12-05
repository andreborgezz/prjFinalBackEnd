package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Produto;
import com.example.demo.repositories.ProdutoRepository;

@Service
// Classe que fornece serviços relacionados a produtos
public class ProdutoService {

    // Injeta o repositório de Produto para interagir com o banco de dados
    @Autowired
    private ProdutoRepository produtoRepository;

    // Método para buscar todos os produtos
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    // Método para buscar um produto pelo seu ID
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    // Método para salvar um novo produto no banco de dados
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Método para deletar um produto pelo seu ID
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    // Método para atualizar um produto existente
    public Produto update(Long id, Produto produtoAtualizado) {
        // Busca o produto pelo ID
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        
        // Verifica se o produto existe
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            
            // Atualiza as propriedades do produto com as informações recebidas
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            
            // Salva o produto atualizado no banco de dados
            return produtoRepository.save(produto);
        }
        
        // Retorna null se o produto não foi encontrado ou pode lançar uma exceção
        return null;
    }
}
