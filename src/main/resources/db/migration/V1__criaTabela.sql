CREATE TABLE pagamento(
    id serial,
    valor decimal(19,2) NOT NULL ,
    nome varchar(100) DEFAULT NULL,
    numero varchar(20) DEFAULT NULL,
    expiracao varchar(10) DEFAULT NULL,
    forma_pagamento varchar(10) NOT NULL,
    codigo varchar(3) DEFAULT NULL,
    id_pedido bigint NOT NULL,
    status varchar(255) DEFAULT NULL,
    primary key (id)
);