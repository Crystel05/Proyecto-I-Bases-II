create or replace NONEDITIONABLE PROCEDURE mostrarUsuarios(cursor OUT SYS_REFCURSOR)

IS

BEGIN

	OPEN CURSOR FOR
	SELECT usuario.docidentidad, usuario.nombreapellidos
	FROM participante 
	INNER JOIN usuario ON participante.usuarioId = usuario.ID;

END;