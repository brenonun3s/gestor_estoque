package br.com.estoque.model;

import java.time.LocalDateTime;
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
@Table(name = "tb_estoque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estoque {
 
 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 private Integer quantidade;

 private Item item;

 private LocalDateTime ultimo_lancamento;



 
 



}
