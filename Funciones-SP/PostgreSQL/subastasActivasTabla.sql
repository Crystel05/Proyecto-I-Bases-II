CREATE OR REPLACE FUNCTION public.subastasActivasTablaSinEscoger()
RETURNS TABLE (nombreItem varchar, fechaFinalS timestamp, monto numeric(19,4))
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
BEGIN
	
	RETURN QUERY
	SELECT  nombre, fechafinal, "mejorMonto"
    FROM subasta
    INNER JOIN item on subasta.itemid = item."ID"
    WHERE activa = TRUE;

END;
$BODY$;