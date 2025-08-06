package br.com.estoque.mapper;

import br.com.estoque.dto.ProdutoRequestDTO;
import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.dto.ProdutoUpdateDTO;
import br.com.estoque.model.Produto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoRequestDTO dto);

    ProdutoResponseDTO toResponseDTO(Produto produto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProdutoFromDto(ProdutoUpdateDTO dto, @MappingTarget Produto produto);

    default Produto updateEntityFromDTO(ProdutoUpdateDTO dto, Produto produto) {
        if (dto == null) {
            return produto;
        }

        if (dto.getNome() != null) {
            produto.setNome(dto.getNome());
        }
        if (dto.getPreco() != null) {
            produto.setPreco(dto.getPreco());
        }
        return produto;
    }
}