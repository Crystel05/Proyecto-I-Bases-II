CREATE OR REPLACE FUNCTION public.mostrarsubastasactivas(
	)
    RETURNS TABLE(nombreitem character varying, fechafinalD timestamp without time zone, monto numeric, 
				  fotoi character varying, descr character varying) 
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE 
dato RECORD;
BEGIN
	FOR dato IN 
	SELECT nombre, fechafinal, "mejorMonto", foto, descripcion, precioinicial
	FROM subasta
	INNER JOIN item on subasta.itemid = item."ID"
	WHERE subasta.activa = TRUE LOOP
		nombreitem := dato.nombre;
		fechafinalD := dato.fechafinal;
		fotoi := dato.foto;
		descr := dato.descripcion;
	
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


