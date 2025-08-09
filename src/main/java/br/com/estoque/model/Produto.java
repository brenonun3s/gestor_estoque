package br.com.estoque.model;

import java.time.LocalDate;
import java.util.UUID;

import br.com.estoque.enums.CategoriaProdutos;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String nome;

    private String marca;

    @Column(name = "quantidade_minima")
    private Integer quantidadeMinima;

    private Double preco;

    private Boolean status;

    private LocalDate dataValidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estoque> estoques;

    private CategoriaProdutos categoria;

    @Column(name = "quantidade_estoque")
    private Integer estoque;
}
