package br.com.estoque.model.entity;

import java.time.LocalDate;
import java.util.UUID;

import br.com.estoque.model.enums.CategoriaProdutos;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "quantidade_maxima")
    private Integer quantidadeMaxima;

    private Double preco;

    private Boolean status;

    private LocalDate dataValidade;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Estoque estoque;
    

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriaProdutos categoria;


}
