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

INSERT INTO usuarios(nome_completo, ativo, codigo) VALUES 
	("Michael TP", false, "abc1234"),
	("Lucas", false, "abcdefg"),
	("Raquel", false, "1234567"),
	("Marcelo", false, "1234abc");
    
INSERT INTO senhas(id_usuario, senha) VALUES 
	(1, "123"),
	(2, "123"),
    (3, "123"),
    (4, "123");
    
    
SELECT * FROM palavrasrestritas;
SELECT * FROM usuarios;
SELECT * FROM senhas;
SELECT * FROM mensagens;

SELECT u.id, u.nome_completo FROM usuarios u, senhas s WHERE u.codigo = "abc1234" AND s.id_usuario = u.id;

SELECT u.id, u.nome_completo FROM usuarios u WHERE codigo = "1234abc" AND (SELECT senha FROM senhas WHERE id_usuario = u.id) = "123";

SELECT m.mensagem, m.enviado_em, u.nome_completo FROM mensagens m, usuarios u
WHERE m.id_usuario = u.id AND m.enviado_em BETWEEN DATE_SUB(NOW(), INTERVAL 15 minute) AND NOW()
ORDER BY m.enviado_em DESC LIMIT 20;

DELETE FROM mensagens where id > 0;

DROP TABLE IF EXISTS mensagens;
DROP TABLE IF EXISTS palavrasrestritas;
DROP TABLE IF EXISTS senhas;
DROP TABLE IF EXISTS usuarios;