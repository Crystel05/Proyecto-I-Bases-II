CREATE OR REPLACE FUNCTION public.mostrarTelUs(cedula varchar)
RETURNS TABLE (numer int)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
usuId int;

BEGIN
	
	usuId := (SELECT "ID" 
	FROM usuario WHERE
    docident = cedula);

   	RETURN QUERY
    SELECT telefono
    FROM telefono
    WHERE "UsuarioId" = usuId;

END;
$BODY$;
