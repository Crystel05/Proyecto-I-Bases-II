CREATE OR REPLACE FUNCTION public.mostDetallesSubastaActiva(subastaId int)
    RETURNS TABLE (decrp varchar, fotoPath varchar, compradorNombre varchar)
    LANGUAGE 'plpgsql'
	
AS $BODY$

DECLARE 
	dato RECORD;

BEGIN

	
	IF(SELECT "mejorMonto" FROM subasta WHERE "ID" = subastaId) = 0
	THEN
		FOR dato IN
			SELECT descripcion, foto
			FROM subasta
			INNER JOIN item ON subasta.itemid = item."ID"
			WHERE subasta."ID" = subastaId LOOP

			decrp := dato.descripcion;
			fotoPath := dato.foto;
			compradorNombre := 'Sin pujas';
			RETURN NEXT;
		END LOOP;
		
	ELSE
	
		FOR dato IN
			SELECT descripcion, foto, nombreapellidos
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
			RETURN NEXT;
		END LOOP;
		
	END IF;
		

END;
$BODY$;

