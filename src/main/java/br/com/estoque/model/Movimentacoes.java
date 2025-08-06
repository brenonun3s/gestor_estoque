package br.com.estoque.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_movimentacoes")
public class Movimentacoes {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;
 
}