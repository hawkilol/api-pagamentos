-- CREATE TABLE METODO_PAGAMENTO (
--     id BIGINT PRIMARY KEY AUTO_INCREMENT,
--     metpag_nome VARCHAR(255)
-- );

-- CREATE TABLE TAB_PAGAMENTO (
--     id BIGINT PRIMARY KEY AUTO_INCREMENT,
--     pag_cod_debito VARCHAR(255),
--     pag_cpf VARCHAR(255),
--     pag_metpag BIGINT,
--     pag_valor DOUBLE,
--     FOREIGN KEY (pag_metpag) REFERENCES METODO_PAGAMENTO(id)
-- );

-- //Usando Filter no model pra flag ativo ao inves de excluir pela view aqui
-- CREATE VIEW PAGAMENTO_ATIVOS AS SELECT * FROM PAGAMENTO WHERE pag_ativo = true;

INSERT INTO METODO_PAGAMENTO (metpag_nome) VALUES ('boleto');
INSERT INTO METODO_PAGAMENTO (metpag_nome) VALUES ('pix');
INSERT INTO METODO_PAGAMENTO (metpag_nome) VALUES ('cartao_credito');
INSERT INTO METODO_PAGAMENTO (metpag_nome) VALUES ('cartao_debito');

-- INSERT INTO PAGAMENTO (pag_cod_debito, pag_cpf_cnpj, pag_metpag, pag_valor) 
-- VALUES ('123456', '006066773369', 1, 1337.10);