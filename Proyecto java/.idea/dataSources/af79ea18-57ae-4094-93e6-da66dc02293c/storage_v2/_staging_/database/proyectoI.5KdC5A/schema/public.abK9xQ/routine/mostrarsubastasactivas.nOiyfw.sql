create function mostrarsubastasactivas()
    returns TABLE(subastaid integer, nombreitem character varying, fechafinald timestamp without time zone, monto numeric)
    language plpgsql
as
$$
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
$$;


SELECT * FROM mostrarsubastasactivas();
alter function mostrarsubastasactivas() owner to postgres;

