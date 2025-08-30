-- Função ENTRADA
CREATE OR REPLACE FUNCTION atualizar_estoque_entrada()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO tb_estoque (id, produto_id, quantidade_atual)
    VALUES (gen_random_uuid(), NEW.id_produto, NEW.quantidade)
    ON CONFLICT (produto_id)
    DO UPDATE SET quantidade_atual = tb_estoque.quantidade_atual + EXCLUDED.quantidade_atual;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- Função SAÍDA
CREATE OR REPLACE FUNCTION atualizar_estoque_saida()
RETURNS TRIGGER AS $$
BEGIN
    -- Verifica se tem estoque suficiente
    IF (SELECT COALESCE(quantidade_atual, 0) FROM tb_estoque WHERE produto_id = NEW.id_produto) < NEW.quantidade THEN
        RAISE EXCEPTION 'Estoque insuficiente para o produto %', NEW.id_produto;
    END IF;

    -- Atualiza o estoque
    UPDATE tb_estoque
    SET quantidade_atual = quantidade_atual - NEW.quantidade
    WHERE produto_id = NEW.id_produto;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
