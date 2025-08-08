package br.com.estoque.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "logs_auditoria")
@Getter
@Setter
public class LogAuditoria {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    //TODO: DESENVOLVER A LOGICA
    @Column(nullable = false, length = 50)
    private String acao; // ação realizada, como "CREATE", "UPDATE", "DELETE"

    @Column(nullable = false, length = 200)
    private String descricao; // descrição resumida da ação

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

}