
CREATE TABLE IF NOT EXISTS tb_estoque (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    produto_id UUID NOT NULL,
    quantidade_atual INT NOT NULL DEFAULT 0,
    CONSTRAINT uk_tb_estoque_produto UNIQUE (produto_id),
    CONSTRAINT fk_tb_estoque_produto FOREIGN KEY (produto_id) REFERENCES tb_produto (id)
);

CREATE OR REPLACE FUNCTION atualizar_estoque_entrada()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO tb_estoque (id, produto_id, quantidade_atual)
    VALUES (gen_random_uuid(), NEW.id_produto, NEW.quantidade)
    ON CONFLICT (produto_id)
    DO UPDATE
    SET quantidade_atual = tb_estoque.quantidade_atual + EXCLUDED.quantidade_atual;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION atualizar_estoque_saida()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT quantidade_atual FROM tb_estoque WHERE produto_id = NEW.id_produto) < NEW.quantidade THEN
        RAISE EXCEPTION 'Estoque insuficiente para o produto %', NEW.id_produto;
    END IF;

    UPDATE tb_estoque
    SET quantidade_atual = quantidade_atual - NEW.quantidade
    WHERE produto_id = NEW.id_produto;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


DROP TRIGGER IF EXISTS tg_entrada ON tb_entradas;
CREATE TRIGGER tg_entrada
AFTER INSERT ON tb_entradas
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_entrada();

DROP TRIGGER IF EXISTS tg_saida ON tb_saidas;
CREATE TRIGGER tg_saida
AFTER INSERT ON tb_saidas
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_saida();
