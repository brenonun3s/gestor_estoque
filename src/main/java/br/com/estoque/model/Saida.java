package br.com.estoque.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_saidas")
@Getter
@Setter
public class Saida {
 
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 private Produto produto;

 private Integer quantidade;

 private String motivo;

 private LocalDate data;

 private String responsavel;


}
