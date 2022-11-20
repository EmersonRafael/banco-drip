package com.drip.banco.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "tb_banco")
@Data
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "nome")
    String nome;

}
