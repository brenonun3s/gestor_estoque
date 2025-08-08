package br.com.estoque.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "local_estoque")
    private String localEstoque;

    @Column(name = "quantidade_atual")
    private Integer quantidadeAtual;

    @Column(name = "ultimo_lancamento")
    private LocalDateTime ultimoLancamento;

    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimentacaoEstoque> movimentacoes;

}
