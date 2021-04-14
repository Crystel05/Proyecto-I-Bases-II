CREATE OR REPLACE FUNCTION public.comprasXcomprador(ident varchar)
    RETURNS TABLE(itemNombre varchar, 
				  precioBase numeric, 
				  precioFinal numeric, 
				  fecha timestamp)
				  --vendedor varchar, 
				  --comentario varchar)
    LANGUAGE 'plpgsql'

AS $BODY$
BEGIN
	
		RETURN QUERY
		SELECT item.nombre, subasta.precioinicial, puja.oferta, puja."fechaHora"
		FROM historialcomprador
		INNER JOIN subasta ON historialcomprador.subastaid = subasta."ID"
		INNER JOIN item ON subasta.itemid = item."ID"
		INNER JOIN puja ON subasta."mejorMonto" = puja.oferta
		INNER JOIN participante ON historialcomprador."compradorId" = participante."ID"
		INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
		AND usuario.docident = ident;
		
END;
$BODY$;

--SELECT * FROM comprasXcomprador('123456') 
--DROP FUNCTION comprasXcomprador(varchar)
