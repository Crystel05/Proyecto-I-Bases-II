create or replace NONEDITIONABLE procedure subastasUsuario(docIdent in varchar2, cursor out SYS_REFCURSOR)
IS
  
    UsuarioId INTEGER;

BEGIN 

    SELECT vendedor.ID INTO UsuarioId
    FROM subasta
    INNER JOIN vendedor ON subasta.vendedorID= vendedor.ID
    INNER JOIN participante ON vendedor.participanteId= participante.ID
    INNER JOIN usuario ON participante.usuarioId = usuario.ID
    WHERE usuario.docidentidad = docIdent;

    OPEN CURSOR FOR SELECT comentario.comentario, comentario.puntaje, item.nombre, fechafinal,
    subasta.precioinicial, subasta.montoActual, usuario.nombreapellidos, item.descripcion, subasta.detallesentrega, item.imagen
    FROM subasta
    INNER JOIN comentario ON comentario.subastaid = subasta.ID AND comentario.esVendedor = 0
    INNER JOIN item ON subasta.itemid = item.ID
    INNER JOIN comprador ON comentario.compradorid = comprador.ID
    INNER JOIN participante ON comprador.participanteId = participante.ID
    INNER JOIN usuario ON participante.usuarioId = usuario.ID
    WHERE subasta.activo = 0 AND subasta.vendedorid = UsuarioId ;

END;