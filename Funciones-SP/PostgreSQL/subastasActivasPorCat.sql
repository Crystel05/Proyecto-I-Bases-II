CREATE OR REPLACE FUNCTION public.mostSubPorCat(categ varchar)
    RETURNS TABLE (nombreIt varchar, fechaFin timestamp, monto numeric(19,4))
    LANGUAGE 'plpgsql'
	
AS $BODY$

BEGIN
	
	RETURN QUERY
	SELECT  item.nombre, fechafinal, "mejorMonto"
    FROM subasta
    INNER JOIN item ON subasta.itemid = item."ID"
	INNER JOIN subcategoria ON item."subcategoriaId" = subcategoria."ID"
	INNER JOIN categoria ON subcategoria."categoriaId" = categoria."ID" 
	WHERE categoria."nombre" = categ AND activa = TRUE;

END;
$BODY$;
