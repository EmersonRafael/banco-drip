package com.drip.banco.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "tb_parametro_transferencia")
@Data
public class ParametroTransferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_transferencia")
    TipoTransaferencia tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_banco")
    Banco banco;

    @Column(name = "comissao")
    double comissao;

    @Column(name = "limite_diario")
    double limiteDiario;
}
