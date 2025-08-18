    create table tb_estoque (
        id UUID primary key,
        produto_id UUID not null,
        local_estoque VARCHAR(255),
        quantidade_atual INTEGER,
        ultimo_lancamento TIMESTAMP,
        foreign key (produto_id) references tb_produto(id)
    );

        create table tb_logs_auditoria (
        id uuid primary key,
        usuario_id uuid not null,
        acao varchar(50) not null,
        descricao varchar(200) not null,
        data_hora timestamp not null default now(),
        foreign key (usuario_id) references tb_usuario(id)
    );

    create table tb_movimentacao_estoque (
        id UUID PRIMARY KEY,
        estoque_id UUID NOT NULL,
        tipo_movimentacao VARCHAR(50) NOT NULL,
        quantidade INTEGER NOT NULL,
        motivo VARCHAR(255),
        data_movimentacao TIMESTAMP NOT NULL,
        usuario_alteracao VARCHAR(255) NOT NULL,
        FOREIGN KEY (estoque_id) REFERENCES tb_estoque(id)
    );

    create table tb_produto (
        id UUID primary key,
        sku VARCHAR(255) not null unique,
        nome VARCHAR(255) not null,
        marca VARCHAR(255),
        quantidade_minima INTEGER,
        preco DOUBLE PRECISION,
        status BOOLEAN,
        data_validade DATE,
        categoria VARCHAR(50),
        quantidade_estoque INTEGER
    );

     create table tb_usuarios (
     id UUID primary key,
     username VARCHAR(255) not null unique,
     senha VARCHAR(255) not null,
     roles TEXT[] not null
 );