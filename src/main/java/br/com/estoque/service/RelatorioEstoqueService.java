package br.com.estoque.service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.estoque.dto.RelatorioProdutosDTO;
import br.com.estoque.repository.EstoqueRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioEstoqueService {

    private final EstoqueRepository estoqueRepository;

    public RelatorioEstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public ByteArrayInputStream gerarRelatorio() throws JRException {
        // 1. Buscar dados
        List<RelatorioProdutosDTO> dados = estoqueRepository.gerarRelatorioProdutos();

        // 2. Compilar template
        JasperReport report = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/reports/estoque.jrxml")
        );

        // 3. Preencher dados
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
        JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);

        // 4. Exportar para PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(print, baos);

        return new ByteArrayInputStream(baos.toByteArray());
    }
}

