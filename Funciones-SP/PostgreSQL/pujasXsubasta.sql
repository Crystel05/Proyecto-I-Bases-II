CREATE OR REPLACE FUNCTION public.pujasXsubasta(nombreItem varchar)
    RETURNS TABLE(cantidadPuja numeric(19,4), 
				 fecha timestamp) 
    LANGUAGE 'plpgsql'

AS $BODY$
BEGIN
	
	RETURN QUERY
	SELECT puja.oferta, puja."fechaHora" 
	FROM puja
	INNER JOIN subasta ON puja."subastaId" = subasta."ID"
	INNER JOIN item ON subasta.itemid = Item."ID" WHERE item."nombre" = nombreItem;
END;
$BODY$;