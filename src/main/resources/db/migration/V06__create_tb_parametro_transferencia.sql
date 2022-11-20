CREATE TABLE tb_parametro_transferencia (
	id int GENERATED ALWAYS AS IDENTITY,
	id_tipo_transferencia int NOT NULL,
	id_banco int NOT NULL,
	comissao decimal(10,2) default 0.0,
	limite_diario decimal(10,2) default 0.0,
	CONSTRAINT tb_parametro_transferencia_pkey PRIMARY KEY (id),
	constraint fk_tb_parametro_transferencia_tipo_transferencia FOREIGN KEY (id_tipo_transferencia) REFERENCES tb_tipo_transferencia(id),
	constraint fk_tb_parametro_transferencia_banco FOREIGN KEY (id_banco) REFERENCES tb_banco(id)
);

insert into tb_parametro_transferencia (id_tipo_transferencia, id_banco, comissao,limite_diario)
	values (
	    (select id from tb_tipo_transferencia where tipo = 'Interno'),
	    (select id from tb_banco where nome = 'Banco A'),
	     0,0
	),
	(
    	(select id from tb_tipo_transferencia where tipo = 'Externo'),
        (select id from tb_banco where nome = 'Banco A'),
    	  5, 5000
    ),
    (
    	    (select id from tb_tipo_transferencia where tipo = 'Interno'),
    	    (select id from tb_banco where nome = 'Banco B'),
    	     0, 0
    	),
    	(
        	(select id from tb_tipo_transferencia where tipo = 'Externo'),
            (select id from tb_banco where nome = 'Banco B'),
        	  5, 5000
        );