ALTER TABLE tb_entradas
ADD COLUMN id_produto UUID REFERENCES tb_produto(id);

ALTER TABLE tb_entradas
    DROP COLUMN IF EXISTS nome_produto;

ALTER TABLE tb_saidas
    DROP COLUMN IF EXISTS nome_produto;

ALTER TABLE tb_saidas
ADD COLUMN id_produto UUID REFERENCES tb_produto(id);