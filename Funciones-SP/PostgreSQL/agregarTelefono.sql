CREATE OR REPLACE FUNCTION public.agregartelefono(
	alias character varying,
	contra character varying,
	numero integer,
	tipo character varying)
    RETURNS integer
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
	usuarioID int;
	tipoID int;
BEGIN
	
	usuarioID := (SELECT "ID" FROM usuario WHERE "Alias" = "alias" and contrasenna = contra);
	tipoID := (SELECT "ID" FROM tipotelefono WHERE nombre = tipo);
	
	INSERT INTO telefono(telefono, tipotelefonoid, "UsuarioId")
	VALUES(numero, tipoID, usuarioID);
	
	IF (SELECT "ID" FROM telefono WHERE telefono = numero) IS NOT NULL
	THEN
		RETURN 1;
	ELSE
		RETURN 0;
	END IF;

END;
$BODY$;