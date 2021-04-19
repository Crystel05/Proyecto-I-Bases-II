CREATE OR REPLACE FUNCTION public.modificartelefono(numViejo int, numNuevo int, tipoTel varchar)
RETURNS INTEGER
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE

	tipoID integer;
	telID integer;

BEGIN
	
	tipoID := (SELECT "ID" FROM tipotelefono WHERE nombre = tipotel);
	telID := (SELECT "ID" FROM telefono WHERE telefono = numViejo);

	UPDATE telefono
    SET telefono = numNuevo,
    tipotelefonoid = tipoID
    WHERE "ID" = telID;
	
	RETURN 1;

END;
$BODY$;


