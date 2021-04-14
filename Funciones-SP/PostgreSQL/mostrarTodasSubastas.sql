CREATE OR REPLACE FUNCTION public.mostrarTodasSubastas()
    RETURNS TABLE(nombre character varying) 
    LANGUAGE 'plpgsql'

AS $BODY$
BEGIN
	
	RETURN QUERY
	SELECT item.nombre FROM item
	INNER JOIN subasta on itemid = item."ID";

END;
$BODY$;

