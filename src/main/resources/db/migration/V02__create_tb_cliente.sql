CREATE TABLE tb_cliente (
	id int GENERATED ALWAYS AS IDENTITY,
	nome varchar(255) NOT NULL,
	documento varchar(14) NOT NULL UNIQUE,
	CONSTRAINT tb_cliente_pkey PRIMARY KEY (id)
);

insert into tb_cliente (nome, documento)
values
('Cliente A','00000000000'),
('Cliente B','11111111111'),
('Cliente C','222222222222'),
('Cliente D','333333333333');