CREATE TABLE tb_banco (
	id int GENERATED ALWAYS AS IDENTITY,
	nome varchar(255) NOT NULL,
	CONSTRAINT tb_banco_pkey PRIMARY KEY (id)
);

insert into tb_banco
   (nome)
values
   ('Banco A'),
   ('Banco B');