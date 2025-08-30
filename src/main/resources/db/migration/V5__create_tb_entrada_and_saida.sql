create table tb_saidas (
    id UUID primary key,
    nome_produto VARCHAR(255) not null,
    quantidade INTEGER not null,
    motivo VARCHAR(255) not null,
    data DATE NOT NULL,
    responsavel VARCHAR(255)
);

create table tb_entradas (
    id UUID primary key,
    nome_produto VARCHAR(255) not null,
    quantidade INTEGER not null,
    motivo VARCHAR(255) not null,
    data DATE NOT NULL,
    responsavel VARCHAR(255)
);