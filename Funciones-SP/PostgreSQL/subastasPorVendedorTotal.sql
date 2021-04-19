CREATE OR REPLACE FUNCTION public.mostrarSubastasVendedorNot(ali varchar, contra varchar)
    RETURNS TABLE(nombreItem varchar, descripcionItem varchar, fotoI varchar, detallesEntregaS varchar)
    LANGUAGE 'plpgsql'

AS $BODY$	
DECLARE 
	vendid int;
BEGIN
	
	vendid := (SELECT vendedor."ID" 
			   FROM vendedor
			   INNER JOIN participante ON vendedor."participanteId" =participante."ID"
			   INNER JOIN usuario ON participante."usuarioId" = usuario."ID" 
			   AND usuario."Alias" = ali AND usuario.contrasenna = contra);

	RETURN QUERY
	SELECT item.nombre, item.descripcion, item.foto, subasta.detallesentrega
	FROM subasta 
	INNER JOIN item ON item."ID" = subasta.itemid
	WHERE subasta.vendedorid = vendid AND subasta.activa = FALSE;
		
END;
$BODY$;
