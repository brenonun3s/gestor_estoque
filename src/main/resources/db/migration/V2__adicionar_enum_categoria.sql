CREATE TYPE categoria_produto AS ENUM ('ELETRONICOS', 'ALIMENTICIO', 'ROUPAS', 'CASA_E_JARDIM', 'LIVROS', 'ESPORTES');

ALTER TABLE tb_produto
    ALTER COLUMN categoria TYPE categoria_produto USING categoria::categoria_produto;

ALTER TABLE tb_produto
    ALTER COLUMN categoria SET NOT NULL;