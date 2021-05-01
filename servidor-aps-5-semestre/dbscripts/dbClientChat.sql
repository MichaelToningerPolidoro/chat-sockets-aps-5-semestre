CREATE DATABASE IF NOT EXISTS `aps-5-semestre`;
USE `aps-5-semestre`;

CREATE TABLE `usuarios` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nome_completo` varchar(50),
  `ativo` bool,
  `codigo` varchar(7),
  `criado_em` timestamp
);

CREATE TABLE `senhas` (
  `id_usuario` int,
  `senha` varchar(12)
);

CREATE TABLE `mensagens` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `mensagem` varchar(1000),
  `enviado_em` datetime,
  `id_usuario` int
);

CREATE TABLE `palavrasRestritas` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `palavra` varchar(15),
  `criado_em` timestamp
);

ALTER TABLE `senhas` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

ALTER TABLE `mensagens` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

/*  Inserção de registros na tabela de palavras indesejadas  */
INSERT INTO palavrasrestritas (palavra) VALUES 
    ("teste01"),
    ("select"),
    ("insert"),
    ("delete");
    
SELECT * FROM palavrasrestritas;
