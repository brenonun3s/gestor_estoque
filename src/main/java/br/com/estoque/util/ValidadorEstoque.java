package br.com.estoque.util;

import java.util.UUID;

public interface ValidadorEstoque {
 void validarQuantidadeEstoque(UUID produtoID, Integer quantidadeSolicitada);
}
