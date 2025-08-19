package br.com.estoque.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Type;

import br.com.estoque.enums.CategoriaProdutos;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    private Double preco;

    private Boolean status;

    private LocalDate dataValidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estoque> estoques;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriaProdutos categoria;

    @Column(name = "quantidade_estoque")
    private Integer estoque;

}
