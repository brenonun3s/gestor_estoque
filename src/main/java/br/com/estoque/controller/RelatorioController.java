package br.com.estoque.controller;

import java.io.InputStream;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/produtos")
    public ResponseEntity<byte[]> gerarRelatorio() throws Exception {
        // Abre o .jasper via classpath
        InputStream jasperStream = getClass().getResourceAsStream("/reports/relatorios_produtos.jasper");
        if (jasperStream == null) {
            throw new RuntimeException("Arquivo relatorios_produtos.jasper não encontrado no classpath!");
        }

        // Preenche o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperStream,
                new HashMap<>(),
                dataSource.getConnection()
        );

        // Exporta para PDF
        byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=relatorios_produtos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}


