CREATE TABLE tb_conta (
	id int GENERATED ALWAYS AS IDENTITY,
	id_banco int NOT NULL,
	id_cliente int NOT NULL,
	saldo decimal(10,2) default 0.0,
	UNIQUE (id_banco, id_cliente),
	CONSTRAINT tb_conta_pkey PRIMARY KEY (id),
	constraint fk_tb_conta_banco FOREIGN KEY (id_banco) REFERENCES tb_banco(id),
	constraint fk_tb_conta_cliente FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id)
);

insert into tb_conta (id_banco, id_cliente, saldo)
	values (
	    (select id from tb_banco where nome = 'Banco A'),
	    (select id from tb_cliente where nome = 'Cliente A'),
	    10000
	),
	(
    	(select id from tb_banco where nome = 'Banco A'),
    	(select id from tb_cliente where nome = 'Cliente B'),
    	10000
    ),
    (
        (select id from tb_banco where nome = 'Banco B'),
        (select id from tb_cliente where nome = 'Cliente C'),
        10000
    );