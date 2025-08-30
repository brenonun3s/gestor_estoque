DROP TRIGGER IF EXISTS tg_atualiza_estoque_entrada ON tb_entradas;
DROP FUNCTION IF EXISTS atualizar_estoque_entrada();

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
