package com.drip.banco.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "tb_transferencias")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_conta_origem")
    Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "id_conta_destino")
    Conta contaDestino;

    @ManyToOne
    @JoinColumn(name = "id_tipo_transferencia")
    TipoTransaferencia tipo;

    @Column(name = "comissao_aplicada")
    double comissaoAplicada;

    @Column(name = "data_criacao")
    LocalDateTime dataCriacao;

    @Column(name = "valor")
    double valor;

}
