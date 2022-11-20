package com.drip.banco.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tb_conta")
@Data
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_banco")
    Banco banco;

    @OneToMany(mappedBy="contaOrigem", fetch = FetchType.LAZY)
    List<Transferencia> transferencias;

    @Column(name = "saldo")
    Double saldo;

}
