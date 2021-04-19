CREATE OR REPLACE FUNCTION public.subastasUsuario(
docIdent varchar)

RETURNS INTEGER
LANGUAGE 'plpgsql'
	
AS $BODY$

DECLARE
	UsuarioId integer;
	
BEGIN
	
	UsuarioId := (SELECT vendedor."ID"
	FROM subasta
	INNER JOIN vendedor ON subasta.vendedorid= vendedor."ID"
	INNER JOIN participante ON vendedor."participanteId"= participante."ID"
	INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
	WHERE usuario.docident = docIdent);
	
	SELECT
	comentarios.comentario,
	comentarios.puntaje,
	item.nombre, 
	subasta.precioinicial,
	subasta."mejorMonto"
	FROM subasta
	INNER JOIN comentario ON comentario.subastaid = subasta."ID"
	INNER JOIN item ON subasta.itemid = item."ID"
	WHERE subasta.activa = FALSE 
	AND subasta.vendedorid = UsuarioId
	AND comentario."esVendedor" = FALSE; 

END;
$BODY$;