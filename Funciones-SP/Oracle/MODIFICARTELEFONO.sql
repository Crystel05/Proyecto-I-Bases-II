create or replace NONEDITIONABLE PROCEDURE modificartelefono(aliass IN VARCHAR2, numeroAntiguo IN INTEGER, numro IN integer, tipoTel IN VARCHAR2, retorno OUT INTEGER)

IS

	usuID INTEGER;
	tipoID integer;


BEGIN

	SELECT ID INTO usuID FROM usuario WHERE alias = aliass;
	SELECT ID INTO tipoID FROM tipotelefono WHERE tipo = tipotel;

	UPDATE telefono
    SET numero = numro,
    tipotelefonoid = tipoID
    WHERE UsuarioId = usuID
    AND numero = numeroAntiguo;

END;