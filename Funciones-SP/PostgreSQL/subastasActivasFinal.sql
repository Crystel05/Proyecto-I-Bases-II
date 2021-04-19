CREATE OR REPLACE FUNCTION public.mostSubPorSubcat(subcategori varchar)
    RETURNS TABLE (nombreIt varchar, fechaFin timestamp, monto numeric(19,4))
    LANGUAGE 'plpgsql'
	
AS $BODY$

DECLARE 
	subcatId int;
	
BEGIN

	subcatId := (SELECT "ID" FROM public.subcategoria WHERE nombre = subcategori);

	RETURN QUERY
	SELECT  item.nombre, fechafinal, "mejorMonto"
    FROM subasta
    INNER JOIN item on subasta.itemid = item."ID"
    WHERE item."subcategoriaId" = subcatId AND activa = TRUE; 

END;
$BODY$;
