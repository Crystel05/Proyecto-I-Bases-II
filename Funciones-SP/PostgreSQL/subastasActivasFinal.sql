CREATE OR REPLACE FUNCTION public.mostSubPorSubcat(categori varchar, subcategori varchar)
    RETURNS TABLE (subastaID int, nombreIt varchar, fechaFin timestamp, monto numeric(19,4))
    LANGUAGE 'plpgsql'
	
AS $BODY$

DECLARE 
	subcatId int;
	dato RECORD;
	
BEGIN

	subcatId := (SELECT subcategoria."ID" FROM public.subcategoria 
	INNER JOIN categoria ON subcategoria."categoriaId" = categoria."ID"
	WHERE subcategoria.nombre = subcategori AND categoria.nombre = categori);

	FOR dato IN 
		SELECT subasta."ID" as subast, item.nombre as itemNom, fechafinal, "mejorMonto", precioinicial
		FROM subasta
		INNER JOIN item on subasta.itemid = item."ID"
		WHERE item."subcategoriaId" = subcatId AND activa = TRUE LOOP
		
		subastaID := dato.subast;
		nombreIt := dato.itemNom;
		fechaFin := dato.fechafinal;
		
		IF dato."mejorMonto" = 0
		THEN 
		
			monto := dato.precioinicial;
			
		ELSE
		
			monto := dato."mejorMonto";
		
		END IF;
		
		RETURN NEXT;
	END LOOP;

END;
$BODY$;

