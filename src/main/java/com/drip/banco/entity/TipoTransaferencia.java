package com.drip.banco.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "tb_tipo_transferencia")
@Data
public class TipoTransaferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "tipo")
    String tipo;

}
