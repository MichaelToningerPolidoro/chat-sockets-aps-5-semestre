CREATE DATABASE IF NOT EXISTS `aps-5-semestre`;
USE `aps-5-semestre`;

CREATE TABLE `usuarios` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nome_completo` varchar(50),
  `ativo` bool,
  `codigo` varchar(7),
  `criado_em` timestamp default current_timestamp
);

CREATE TABLE `senhas` (
  `id_usuario` int,
  `senha` varchar(12)
);

CREATE TABLE `mensagens` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `mensagem` varchar(1000),
  `enviado_em` datetime default current_timestamp,
  `id_usuario` int
);

CREATE TABLE `palavrasRestritas` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `palavra` varchar(15),
  `criado_em` timestamp default current_timestamp
);

ALTER TABLE `senhas` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

ALTER TABLE `mensagens` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

/*  Inserção de registros na tabela de palavras indesejadas  */
INSERT INTO palavrasrestritas (palavra) VALUES 
    ("teste01"),
    ("select"),
    ("insert"),
    ("delete"),
    ("datebayo");
    
INSERT INTO usuarios(nome_completo, ativo, codigo) VALUES ("Michael TP", false, "abc1234");
INSERT INTO usuarios(nome_completo, ativo, codigo) VALUES ("Lucas", false, "abcdefg");
INSERT INTO usuarios(nome_completo, ativo, codigo) VALUES ("Raquel", false, "1234567");
INSERT INTO usuarios(nome_completo, ativo, codigo) VALUES ("Marcelo", false, "1234abc");
    
SELECT * FROM palavrasrestritas;
SELECT * FROM usuarios;
SELECT * FROM mensagens;

DELETE FROM mensagens where id > 0;

DROP TABLE IF EXISTS mensagens;
DROP TABLE IF EXISTS palavrasrestritas;
DROP TABLE IF EXISTS senhas;
DROP TABLE IF EXISTS usuarios;