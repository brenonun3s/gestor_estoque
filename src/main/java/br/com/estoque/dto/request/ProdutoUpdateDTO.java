package br.com.estoque.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estoque.model.enums.CategoriaProdutos;
import jakarta.validation.constraints.*;

public record ProdutoUpdateDTO(

        String nome,

        String marca,

        @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero") Double preco,

        Boolean status,

        String sku,

        @Min(value = 0, message = "Quantidade mínima não pode ser negativa") Integer quantidadeMinima,

        @Min(value = 0, message = "Quantidade mínima não pode ser negativa") @Max(value = 10000, message = "Quantidade máxima não pode ser superior a 10000") Integer quantidadeMaxima,

        @FutureOrPresent(message = "Data de validade deve ser hoje ou no futuro") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate dataValidade,

        CategoriaProdutos categoria

) {
}
