package br.com.estoque.model.entity;

import java.util.ArrayList;
import java.util.UUID;

import br.com.estoque.model.enums.UsuarioEnum;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_usuarios")
public class Usuario {
 
 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 private String username;

 private String senha;

 private ArrayList<UsuarioEnum> roles;
}
