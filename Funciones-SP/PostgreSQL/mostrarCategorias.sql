CREATE OR REPLACE FUNCTION public.mostCategorias()
RETURNS TABLE (nombre varchar)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
BEGIN
	
	RETURN QUERY
	SELECT categoria.nombre FROM categoria;

END;
$BODY$;