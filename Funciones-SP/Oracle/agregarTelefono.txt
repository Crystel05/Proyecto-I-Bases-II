create or replace NONEDITIONABLE PROCEDURE agregartelefono(aliass IN VARCHAR2, numro IN integer, tipoTel IN VARCHAR2, retorno OUT INTEGER)

IS

	usuarioID INTEGER;
	tipoID integer;

BEGIN

	SELECT ID INTO usuarioID FROM usuario WHERE alias = aliass;
	SELECT ID INTO tipoID FROM tipotelefono WHERE tipo = tipotel;

	INSERT INTO telefono(numero, tipotelefonoid, UsuarioId)
	VALUES(numro, tipoID, usuarioID);

END;