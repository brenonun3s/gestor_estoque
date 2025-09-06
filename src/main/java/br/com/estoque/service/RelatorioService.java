package br.com.estoque.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {

    @Autowired
    private DataSource dataSource;

    public void gerarPdf() throws SQLException, JRException {
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                getClass().getResource("/reports/meuRelatorio.jasper").getPath(),
                new HashMap<>(),
                conn
            );

            JasperExportManager.exportReportToPdfFile(jasperPrint, "meuRelatorio.pdf");
            System.out.println("Relatório gerado!");
        }
    }
}

