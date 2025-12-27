package br.com.estoque.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaProdutos {
    ELETRONICOS("Eletrônicos"),
    ROUPAS("Roupas"),
    CASA_E_JARDIM("Casa e Jardim"),
    LIVROS("Livros"),
    ESPORTES("Esportes"),
    ALIMENTICIO("Alimentício"),
    BELEZA("Beleza"),
    BRINQUEDOS("Brinquedos"),
    OUTROS("Outros");

    private final String descricao;

    CategoriaProdutos(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }
}
