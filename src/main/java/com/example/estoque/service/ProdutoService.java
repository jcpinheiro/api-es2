package com.example.estoque.service;

import com.example.estoque.model.Produto;
import com.example.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }


    public List<Produto> lista() {
        return repository.findAll();
    }

    public Optional<Produto> buscaPorId(Integer id) {
        return repository.findById(id );
    }

    public Produto salva(Produto produto) {
        return repository.save(produto );

    }

    public Optional<Produto> buscaPor(String nome, String descricao) {
        return repository.findByNomeAndDescricao(nome, descricao);
    }
}
