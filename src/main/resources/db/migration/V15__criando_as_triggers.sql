CREATE TRIGGER tg_entrada
AFTER INSERT ON tb_entradas
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_entrada();

CREATE TRIGGER tg_saida
AFTER INSERT ON tb_saidas
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_saida();
