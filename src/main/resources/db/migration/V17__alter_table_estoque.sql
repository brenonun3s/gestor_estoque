ALTER TABLE tb_estoque
ADD CONSTRAINT uk_tb_estoque_produto UNIQUE (produto_id);
