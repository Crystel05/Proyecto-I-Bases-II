CREATE OR REPLACE FUNCTION public.mostrartipostel(
	)
    RETURNS TABLE(nombre character varying) 
    LANGUAGE 'plpgsql'

AS $BODY$
BEGIN
	
	RETURN QUERY
	SELECT tipotelefono.nombre FROM tipotelefono;

END;
$BODY$;