CREATE TABLE tb_transferencias (
	id int GENERATED ALWAYS AS IDENTITY,
	id_conta_origem int NOT NULL,
	id_conta_destino int NOT NULL,
	id_tipo_transferencia int NOT NULL,
	comissao_aplicada decimal(10,2) default 0.0,
	valor decimal(10,2) default 0.0 NOT NULL,
	data_criacao TIMESTAMPTZ NOT NULL DEFAULT NOW(),
	CONSTRAINT tb_trasferencias_pkey PRIMARY KEY (id),
	constraint fk_tb_trasferencias_conta_origem FOREIGN KEY (id_conta_origem) REFERENCES tb_conta(id),
	constraint fk_tb_trasferencias_conta_destino FOREIGN KEY (id_conta_destino) REFERENCES tb_conta(id),
	constraint fk_tb_trasferencias_tipo_transferencia FOREIGN KEY (id_tipo_transferencia) REFERENCES tb_tipo_transferencia(id)
);