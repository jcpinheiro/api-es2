package com.example.estoque.controller;


import com.example.estoque.model.Produto;
import com.example.estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService service) {
        this.produtoService = service;
    }

    @GetMapping
    public List<Produto> lista() {
        return produtoService.lista();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaPor(@PathVariable Integer id) {

        Optional<Produto> produto = produtoService.buscaPorId(id);

        if (produto.isPresent())
            return ResponseEntity.ok(produto.get() );
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salva(@Valid @RequestBody Produto produto) {

        Optional<Produto> produtoExistente = produtoService.buscaPor(produto.getNome(), produto.getDescricao() );
        if (produtoExistente.isPresent() ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um produto cadastrado com esse nome e descrição");
        }
        return produtoService.salva(produto );
    }



}
