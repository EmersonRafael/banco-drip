CREATE TABLE tb_tipo_transferencia (
	id int GENERATED ALWAYS AS IDENTITY,
	tipo varchar(255) NOT NULL,
	CONSTRAINT tb_tipo_transferencia_pkey PRIMARY KEY (id)
);

insert into tb_tipo_transferencia (tipo) values ('Interno'), ('Externo');