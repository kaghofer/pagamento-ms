CREATE TABLE parcela_pagamento(
    id serial PRIMARY KEY,
    valor decimal(19,2) NOT NULL ,
    data_criacao timestamp NOT NULL,
    data_vencimento timestamp DEFAULT NULL,
    data_pagamento timestamp DEFAULT NULL,
    status_parcela varchar(3) DEFAULT NULL,
    pagamento_id bigint NOT NULL,
    constraint fk_pagamento foreign key (pagamento_id) references pagamento(id)
);