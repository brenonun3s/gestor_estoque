package br.com.estoque.model.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
@Table(name = "tb_usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 private String name;

 @Column(unique = true, nullable = false)
 private String email;

 @Column(nullable = false)
 @JsonIgnore
 private String password;

}