package br.com.estoque.service;

/*@Service
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


*/