-- Atualizar função de ENTRADA
CREATE OR REPLACE FUNCTION atualizar_estoque_entrada()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE tb_estoque
    SET quantidade_atual = quantidade_atual + NEW.quantidade
    WHERE produto_id = NEW.id_produto;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Atualizar função de SAÍDA
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
