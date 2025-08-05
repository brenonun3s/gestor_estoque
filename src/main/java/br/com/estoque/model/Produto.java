package br.com.estoque.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produto {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)

 private UUID id;

 private String sku;

 private String nome;

 private Integer estoque;

 private String marca;

 private Integer quantidadeMinima;

 private Double preco;

 private Boolean status;

 private LocalDate dataValidade;

}
