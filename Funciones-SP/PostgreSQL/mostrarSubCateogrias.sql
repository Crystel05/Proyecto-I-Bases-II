CREATE OR REPLACE FUNCTION public.mostrarSubCategorias(categoriaNom varchar)
RETURNS TABLE (nombre varchar)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
BEGIN
	
	RETURN QUERY
	SELECT subcategoria.nombre
    FROM subcategoria INNER JOIN categoria
    ON categoria."ID" = subcategoria."categoriaId"
    WHERE categoria.nombre = categoriaNom;

END;
$BODY$;
