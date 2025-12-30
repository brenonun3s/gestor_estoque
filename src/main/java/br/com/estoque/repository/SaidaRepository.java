package br.com.estoque.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estoque.dto.response.MovimentacoesResponseDTO;
import br.com.estoque.model.entity.Saida;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, UUID> {

    @Query("""
                SELECT new br.com.estoque.dto.response.MovimentacoesResponseDTO(
                    e.quantidade,
                    e.motivo,
                    e.responsavel,
                    p.sku,
                    p.nome,
                    e.data,
                    'SAIDA'
                )
                FROM Saida e
                JOIN e.produto p
            """)
    List<MovimentacoesResponseDTO> findAllMovimentacoes();

    @Query("SELECT FUNCTION('DATE', s.data), SUM(s.quantidade) FROM Saida s GROUP BY FUNCTION('DATE', s.data)")
    List<Object[]> totalSaidasPorDia();

}
