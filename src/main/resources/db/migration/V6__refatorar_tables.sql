
CREATE TABLE IF NOT EXISTS tb_estoque (
    id UUID PRIMARY KEY,
    id_produto UUID NOT NULL UNIQUE,
    quantidade_atual INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (id_produto) REFERENCES tb_produto(id)
);


ALTER TABLE tb_produto
    DROP COLUMN IF EXISTS quantidade_minima;

CREATE TABLE IF NOT EXISTS tb_entradas (
    id UUID PRIMARY KEY,
    id_produto UUID NOT NULL,
    quantidade INTEGER NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    origem VARCHAR(255) NOT NULL,
    data_entrada DATE NOT NULL,
    responsavel VARCHAR(255),
    FOREIGN KEY (id_produto) REFERENCES tb_produto(id)
);

CREATE TABLE IF NOT EXISTS tb_saidas (
    id UUID PRIMARY KEY,
    id_produto UUID NOT NULL,
    quantidade INTEGER NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    data_saida DATE NOT NULL,
    responsavel VARCHAR(255),
    destino VARCHAR(255),
    FOREIGN KEY (id_produto) REFERENCES tb_produto(id)
);

CREATE OR REPLACE FUNCTION atualizar_estoque_entrada()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE tb_estoque
    SET quantidade_atual = quantidade_atual + NEW.quantidade
    WHERE id_produto = NEW.id_produto;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tg_atualiza_estoque_entrada
AFTER INSERT ON tb_entradas
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_entrada();


CREATE OR REPLACE FUNCTION atualizar_estoque_saida()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT quantidade_atual FROM tb_estoque WHERE id_produto = NEW.id_produto) < NEW.quantidade THEN
        RAISE EXCEPTION 'Estoque insuficiente para o produto %', NEW.id_produto;
    END IF;

    UPDATE tb_estoque
    SET quantidade_atual = quantidade_atual - NEW.quantidade
    WHERE id_produto = NEW.id_produto;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tg_atualiza_estoque_saida
AFTER INSERT ON tb_saidas
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_saida();
