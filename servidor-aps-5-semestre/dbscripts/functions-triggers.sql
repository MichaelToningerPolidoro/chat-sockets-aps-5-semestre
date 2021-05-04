USE `aps-5-semestre`;
SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $$

CREATE FUNCTION REPLACE_RESTRICTED_WORDS(p_TEXT VARCHAR(1000))
RETURNS VARCHAR(1000)
BEGIN
DECLARE v_NEWTEXT VARCHAR(1000) DEFAULT '';
DECLARE	v_INDEX BIGINT DEFAULT 0;
DECLARE	v_FINDER CHAR(1);
DECLARE v_CURRENTWORD VARCHAR(1000);
DECLARE v_WORDRESTRICT VARCHAR(40);
WHILE p_TEXT <> '' DO	
SET v_INDEX = REGEXP_INSTR(p_TEXT, '[^a-zA-Z0-9]');

			SET v_FINDER = SUBSTRING(p_TEXT,v_INDEX,1);
            
			IF v_FINDER = ''
			THEN
				SET v_CURRENTWORD = CASE WHEN v_INDEX > 0 THEN SUBSTRING_INDEX(p_TEXT, SPACE(1), 1) ELSE p_TEXT END;
			ELSE
				SET v_CURRENTWORD = CASE WHEN v_INDEX > 0 THEN SUBSTRING_INDEX(p_TEXT, v_FINDER, 1) ELSE p_TEXT END;					
			END IF;
			            
			IF EXISTS (SELECT palavra FROM palavrasrestritas WHERE LOCATE(palavra, v_CURRENTWORD) > 0 ) THEN
				SET v_WORDRESTRICT = (SELECT REPLACE(lower(v_CURRENTWORD), palavra, '***') FROM palavrasrestritas WHERE LOCATE(palavra, v_CURRENTWORD) > 0);
            ELSE
				SET v_WORDRESTRICT = v_CURRENTWORD;
            END IF;

				IF v_FINDER = ''
				THEN
					SET v_NEWTEXT = CONCAT(v_NEWTEXT , v_WORDRESTRICT, SPACE(1));
				ELSE
					SET v_NEWTEXT = CONCAT(v_NEWTEXT , v_WORDRESTRICT, v_FINDER);
				END IF;
                
			IF v_INDEX = 0 THEN
					SET p_TEXT = '';
			ELSE
					SET p_TEXT = SUBSTRING(p_TEXT, v_INDEX+1, CHAR_LENGTH(RTRIM(p_TEXT))+2);
			END IF;	
            
END WHILE;
SET v_NEWTEXT = RTRIM(v_NEWTEXT);

RETURN v_NEWTEXT;
END;$$

DELIMITER ;

select `aps-5-semestre`.REPLACE_RESTRICTED_WORDS('asfasf a.sfasfa .insert ASIHUASFNJ DELETE') as 'restricted';

/*--------------------------------------*/
/*                Trigger               */
/*--------------------------------------*/
USE `aps-5-semestre`;
DROP TRIGGER IF EXISTS `aps-5-semestre`.INSERTED_WITH_RESTRICTED_WORDS;

DELIMITER $

CREATE TRIGGER INSERTED_WITH_RESTRICTED_WORDS BEFORE INSERT
ON mensagens
FOR EACH ROW
BEGIN
SET NEW.mensagem  = (SELECT `aps-5-semestre`.REPLACE_RESTRICTED_WORDS(NEW.mensagem));
END$

/*     Testes     */
insert into mensagens(mensagem, id_usuario) values ("heheh testes e testes DELETE", 1);
select * from mensagens;
insert into usuarios(nome_completo, ativo, codigo) values ("Teste", false, "abc1234");

SELECT mensagem FROM mensagens ORDER BY id DESC LIMIT 1;

DELETE FROM mensagens where id > 0;
