package br.com.estoque.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.service.RelatorioEstoqueService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/relatorios")
public class RelatorioEstoqueController {

    private final RelatorioEstoqueService service;

    public RelatorioEstoqueController(RelatorioEstoqueService service) {
        this.service = service;
    }

    @GetMapping("/estoque/{idCliente}")
    public ResponseEntity<byte[]> gerarRelatorio(@PathVariable Long idCliente) throws JRException {
        byte[] pdf = service.gerarRelatorio().readAllBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-estoque.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}

