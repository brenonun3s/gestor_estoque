create table tb_saidas (
    id UUID primary key,
    id_produto UUID NOT NULL,
    quantidade INTEGER not null,
    motivo VARCHAR(255) not null,
    data_saida DATE NOT NULL,
    responsavel VARCHAR(255),
    destino VARCHAR(255),
    FOREIGN KEY (id_produto) REFERENCES tb_produto(id)

);

create table tb_entradas (
    id UUID primary key,
    id_produto UUID NOT NULL,
    quantidade INTEGER not null,
    motivo VARCHAR(255) not null,
    origem VARCHAR(255) NOT NULL,
    data_entrada DATE NOT NULL,
    responsavel VARCHAR(255).
    FOREIGN KEY (id_produto) REFERENCES tb_produto(id)
);

CREATE TABLE tb_estoque (
    id UUID primary key,
    id_produto UUID not null UNIQUE,
    quantidade INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (id_produto) REFERENCES tb_produto(id)
);

ALTER TABLE tb_produto DROP COLUMN IF EXISTS quantidade_minima;

CREATE OR REPLACE FUNCTION atualizar_estoque_entrada()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE tb_estoque
    SET quantidade = quantidade + NEW.quantidade
    WHERE id_produto = NEW.id_produto;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tg_atualiza_estoque_entrada
AFTER INSERT ON tb_entrada
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_entrada();

CREATE OR REPLACE FUNCTION atualizar_estoque_saida()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT quantidade FROM tb_estoque WHERE id_produto = NEW.id_produto) < NEW.quantidade THEN
        RAISE EXCEPTION 'Estoque insuficiente para o produto %', NEW.id_produto;
    END IF;

    UPDATE tb_estoque
    SET quantidade = quantidade - NEW.quantidade
    WHERE id_produto = NEW.id_produto;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tg_atualiza_estoque_saida
AFTER INSERT ON tb_saida
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_saida();

