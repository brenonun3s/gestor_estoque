package br.com.estoque.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.estoque.dto.MovimentacoesDTO;
import br.com.estoque.repository.EntradaRepository;
import br.com.estoque.repository.SaidaRepository;

@Service
public class MovimentacaoService {

    private final EntradaRepository entradaRepository;
    private final SaidaRepository saidaRepository;

    public MovimentacaoService(EntradaRepository entradaRepository, SaidaRepository saidaRepository) {
        this.entradaRepository = entradaRepository;
        this.saidaRepository = saidaRepository;
    }

    public List<MovimentacoesDTO> listarMovimentacoes() {
        List<MovimentacoesDTO> entradas = entradaRepository.findAll()
            .stream()
            .map(e -> new MovimentacoesDTO(
                e.getQuantidade(),
                e.getMotivo(),
                e.getResponsavel(),
                e.getProduto().getSku(),
                e.getProduto().getNome(),
                e.getData(),
                "ENTRADA"
            )).toList();

        List<MovimentacoesDTO> saidas = saidaRepository.findAll()
            .stream()
            .map(s -> new MovimentacoesDTO(
                s.getQuantidade(),
                s.getMotivo(),
                s.getResponsavel(),
                s.getProduto().getSku(),
                s.getProduto().getNome(),
                s.getData(),
                "SAIDA"
            )).toList();

        // junta tudo e ordena por data
        List<MovimentacoesDTO> todas = new ArrayList<>();
        todas.addAll(entradas);
        todas.addAll(saidas);

        todas.sort(Comparator.comparing(MovimentacoesDTO::data));
        return todas;
    }
}

