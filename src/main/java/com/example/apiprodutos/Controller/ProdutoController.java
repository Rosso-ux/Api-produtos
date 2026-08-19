package com.example.apiprodutos.Controller;

import com.example.apiprodutos.Model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private static List<Produto> produtos = new ArrayList<>(
            List.of(
                    new Produto(1, "Notebook", 3500.00),
                    new Produto(2, "Mouse", 80.00),
                    new Produto(3, "Teclado", 150.00)
            )
    );

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtos;
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable Integer id) {

        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }

        return null;
    }

    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {

        produtos.add(produto);

        return produto;
    }

    @PatchMapping("/{id}")
    public Produto atualizarProduto(
            @PathVariable Integer id,
            @RequestBody Produto produtoAtualizado) {

        for (Produto produto : produtos) {

            if (produto.getId().equals(id)) {

                produto.setNome(produtoAtualizado.getNome());
                produto.setPreco(produtoAtualizado.getPreco());

                return produto;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Integer id) {

        boolean removido = produtos.removeIf(produto -> produto.getId().equals(id));

        if (removido) {
            return "Produto removido com sucesso!";
        }

        return "Produto não encontrado!";
    }

}
