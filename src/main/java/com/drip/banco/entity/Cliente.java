package com.drip.banco.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "tb_cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "nome")
    String nome;

    @Column(name = "documento")
    String documento;

}
