CREATE OR REPLACE FUNCTION public.subastasPorVendedor(docIden varchar)
    RETURNS TABLE(coment varchar, punt int, nombreItem varchar, fecha timestamp,
				  precioInicial numeric, precioFinal numeric, nombreGanador varchar, 
				  detallesItem varchar,detallesEntrega varchar, fotoI varchar) 
    LANGUAGE 'plpgsql'

AS $BODY$

	DECLARE 
		UsuarioId integer;
		compradorId integer;

BEGIN

		UsuarioId := (SELECT DISTINCT vendedor."ID"
		FROM subasta
		INNER JOIN vendedor ON subasta.vendedorid = vendedor."ID"
		INNER JOIN participante ON vendedor."participanteId"= participante."ID"
		INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
		WHERE usuario.docident = docIden);
	
		
		RETURN QUERY
		SELECT comentarios.comentario, comentarios.puntuacion, item.nombre, fechafinal,
		subasta.precioinicial, subasta."mejorMonto", nombreapellidos, item.descripcion, subasta.detallesentrega, item.foto
		FROM subasta
		INNER JOIN comentarios ON comentarios.subastaid = subasta."ID" AND comentarios."esVendedor" = FALSE
		INNER JOIN item ON subasta.itemid = item."ID"
		INNER JOIN comprador ON comentarios.compradorid = comprador."ID"
		INNER JOIN participante ON comprador."participanteId" = participante."ID"
		INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
		WHERE subasta.activa = FALSE AND subasta.vendedorid = UsuarioId ;

END;
$BODY$;

--SELECT * FROM subastasPorVendedor('314597')
SELECT DISTINCT vendedor."ID"
		FROM subasta
		INNER JOIN vendedor ON subasta.vendedorid = vendedor."ID"
		INNER JOIN participante ON vendedor."participanteId"= participante."ID"
		INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
		WHERE usuario.docident = '964736'

SELECT comentarios.comentario, comentarios.puntuacion, item.nombre, fechafinal,
		subasta.precioinicial, subasta."mejorMonto", nombreapellidos, item.descripcion, subasta.detallesentrega, item.foto
		FROM subasta
		INNER JOIN comentarios ON comentarios.subastaid = subasta."ID" AND comentarios."esVendedor" = FALSE
		INNER JOIN item ON subasta.itemid = item."ID"
		INNER JOIN comprador ON comentarios.compradorid = comprador."ID"
		INNER JOIN participante ON comprador."participanteId" = participante."ID"
		INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
		WHERE subasta.activa = FALSE AND subasta.vendedorid = 2

SELECT * FROM comentarios
SELECT * FROM subasta WHERE activa  = FALSE

SELECT * FROM usuario

