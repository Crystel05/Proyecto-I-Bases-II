CREATE OR REPLACE FUNCTION public.mostrarsubastasactivas(
	)
    RETURNS TABLE(subastaId int, nombreitem character varying, fechafinalD timestamp without time zone, monto numeric) 
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE 
dato RECORD;
BEGIN

	FOR dato IN 
	SELECT subasta."ID" as IdSubs, nombre, fechafinal, "mejorMonto", precioinicial
	FROM subasta
	INNER JOIN item on subasta.itemid = item."ID"
	WHERE subasta.activa = TRUE LOOP
		nombreitem := dato.nombre;
		fechafinalD := dato.fechafinal;
		subastaId := dato.IdSubs;
	
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