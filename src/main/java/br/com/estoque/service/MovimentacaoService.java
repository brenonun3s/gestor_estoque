package br.com.estoque.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.dto.response.MovimentacoesResponseDTO;
import br.com.estoque.repository.EntradaRepository;
import br.com.estoque.repository.SaidaRepository;

@Service
public class MovimentacaoService {

    @Autowired
    EntradaRepository entradaRepository;

    @Autowired
    SaidaRepository saidaRepository;

    public List<MovimentacoesResponseDTO> listarMovimentacoes() {
        List<MovimentacoesResponseDTO> entradas = entradaRepository.findAllMovimentacoes();

        List<MovimentacoesResponseDTO> saidas = saidaRepository.findAllMovimentacoes();
        List<MovimentacoesResponseDTO> todas = new ArrayList<>();
        todas.addAll(entradas);
        todas.addAll(saidas);

        todas.sort(Comparator.comparing(MovimentacoesResponseDTO::data));
        return todas;
    }
}
