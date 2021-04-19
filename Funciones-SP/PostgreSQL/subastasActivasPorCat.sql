CREATE OR REPLACE FUNCTION public.mostSubPorCat(categ varchar)
    RETURNS TABLE (subId int, nombreIt varchar, fechaFin timestamp, monto numeric(19,4))
    LANGUAGE 'plpgsql'
	
AS $BODY$

DECLARE 
	dato RECORD;

BEGIN
	
	FOR dato IN
		SELECT subasta."ID" as "subastaI", item.nombre as itemNombre, fechafinal, "mejorMonto", precioinicial
		FROM subasta
		INNER JOIN item ON subasta.itemid = item."ID"
		INNER JOIN subcategoria ON item."subcategoriaId" = subcategoria."ID"
		INNER JOIN categoria ON subcategoria."categoriaId" = categoria."ID" AND categoria."nombre" = categ
		WHERE subasta.activa = TRUE LOOP
		
		subId := dato."subastaI";
		nombreIt := dato.itemNombre;
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

