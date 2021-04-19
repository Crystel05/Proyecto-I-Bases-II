-- FUNCTION: public.mostdetallessubastaactiva(integer)

-- DROP FUNCTION public.mostdetallessubastaactiva(integer);

CREATE OR REPLACE FUNCTION public.mostdetallessubastaactiva(
	subastaid integer)
    RETURNS TABLE(decrp character varying, fotopath character varying, compradornombre character varying, montoP numeric) 
    LANGUAGE 'plpgsql'


AS $BODY$
DECLARE 
	dato RECORD;

BEGIN

	
	IF(SELECT "mejorMonto" FROM subasta WHERE "ID" = subastaId) = 0
	THEN
		FOR dato IN
			SELECT descripcion, foto, precioinicial
			FROM subasta
			INNER JOIN item ON subasta.itemid = item."ID"
			WHERE subasta."ID" = subastaId LOOP

			decrp := dato.descripcion;
			fotoPath := dato.foto;
			compradorNombre := 'Sin pujas';
			montoP := dato.precioinicial;
			RETURN NEXT;
		END LOOP;
		
	ELSE
	
		FOR dato IN
			SELECT descripcion, foto, nombreapellidos, "mejorMonto"
			FROM subasta
			INNER JOIN item ON subasta.itemid = item."ID"
			INNER JOIN puja ON puja.oferta = subasta."mejorMonto"
			INNER JOIN comprador ON puja."compradorId" = comprador."ID"
			INNER JOIN participante ON comprador."participanteId" = participante."ID"
			INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
			WHERE subasta."ID" = subastaId LOOP

			decrp := dato.descripcion;
			fotoPath := dato.foto;
			compradorNombre := dato.nombreapellidos;
			montoP := dato."mejorMonto";
			RETURN NEXT;
		END LOOP;
		
	END IF;
		

END;
$BODY$;

ALTER FUNCTION public.mostdetallessubastaactiva(integer)
    OWNER TO postgres;
