CREATE OR REPLACE FUNCTION public.enviarComentario(coment varchar, puntu int, esVend boolean, compra boolean, 
												   itemNom varchar, ali varchar, contra varchar)
    RETURNS INTEGER
    LANGUAGE 'plpgsql'

AS $BODY$	
DECLARE 
	compid int;
	subasId int;
	vendid int;
	partId int;
	montoMax int;
BEGIN
		
	partId := (SELECT participante."ID" FROM participante 
			   INNER JOIN usuario ON participante."usuarioId" = usuario."ID" 
			   AND usuario."Alias" = ali AND usuario.contrasenna = contra);
			   
	subasId := (SELECT subasta."ID" 
				FROM subasta
				INNER JOIN item ON item."ID" = subasta.itemid AND item.nombre = itemNom);
				
	montoMax := (SELECT max(oferta) FROM puja WHERE "subastaId" = subasId);
	
	IF esVend
	THEN
	
		vendId := (SELECT vendedor."ID" 
				   FROM vendedor 
				   INNER JOIN participante ON vendedor."participanteId" = participante."ID" AND participante."ID" = partId);
		RAISE NOTICE '%', vendId;
		compId := (SELECT comprador."ID"
				   FROM comprador
				   INNER JOIN puja ON comprador."ID" = puja."compradorId"
				   INNER JOIN subasta ON puja."subastaId" = subasta."ID"
				   AND subasta."ID" = subasId AND puja.oferta = montoMax);	  
		
	ELSE
	
		vendId := (SELECT vendedorid 
				   FROM subasta 
				   WHERE "ID" = subasId);
		compId := (SELECT comprador."ID" 
				   FROM comprador
				   INNER JOIN participante ON comprador."participanteId" = participante."ID" AND participante."ID" = partId);
	
	END IF;
	
	IF  (SELECT "ID" FROM comentarios WHERE subastaid = subasId AND "esVendedor" = esVend) IS NOT NULL
	THEN
		
		UPDATE comentarios
		SET comentario = coment, puntuacion = puntu
		WHERE "ID" = (SELECT "ID" FROM comentarios WHERE subastaid = subasId AND "esVendedor" = esVend);
	
	ELSE
		INSERT INTO comentarios(comentario, puntuacion, compradorid, vendedorid, subastaid, "esVendedor")
		VALUES (coment, puntu, compid, vendid, subasId, esVend);

		IF esVend = FALSE
		THEN 

			INSERT INTO historialcomprador(subastaid, "compradorId", comprado)
			VALUES (subasId, compid, compra);
		
		END IF;
	END IF;
	
	RETURN 1;
		
END;
$BODY$;

