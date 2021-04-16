CREATE OR REPLACE FUNCTION public.mostrarInfoUsuario(ced varchar)
    RETURNS TABLE(contra varchar, nombre varchar, cedula varchar, dir varchar, alis varchar) 
    LANGUAGE 'plpgsql'

AS $BODY$
BEGIN
	
	RETURN QUERY
	SELECT contrasenna, nombreapellidos, docIdent, direccion, "Alias"
	FROM usuario
	WHERE docIdent = ced;

END;
$BODY$;



