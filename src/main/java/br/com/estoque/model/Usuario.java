package br.com.estoque.model;

import java.util.ArrayList;
import java.util.UUID;

import br.com.estoque.enums.UsuarioEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
 
 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 private String username;

 private String senha;

 private ArrayList<UsuarioEnum> roles;
}
