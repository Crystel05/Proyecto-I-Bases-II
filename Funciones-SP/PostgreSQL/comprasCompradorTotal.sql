CREATE OR REPLACE FUNCTION public.mostrarSubastasCompradorNot(ali varchar, contra varchar)
    RETURNS TABLE(nombreItem varchar, descripcionItem varchar, fotoI varchar, detallesEntregaS varchar)
    LANGUAGE 'plpgsql'

AS $BODY$	
DECLARE 
	compId int;
BEGIN
	
	compId := (SELECT comprador."ID" 
			   FROM comprador
			   INNER JOIN participante ON comprador."participanteId" = participante."ID"
			   INNER JOIN usuario ON participante."usuarioId" = usuario."ID" 
			   AND usuario."Alias" = ali AND usuario.contrasenna = contra);

	RETURN QUERY
	SELECT item.nombre, item.descripcion, item.foto, subasta.detallesentrega
	FROM subasta 
	INNER JOIN item ON item."ID" = subasta.itemid
	INNER JOIN puja ON puja.oferta = subasta."mejorMonto" AND puja."subastaId" = subasta."ID"
	INNER JOIN comprador ON puja."compradorId" = comprador."ID" AND comprador."ID" = compId
	WHERE subasta.activa = FALSE;
		
END;
$BODY$;
