create or replace NONEDITIONABLE PROCEDURE comprasXcomprador(ident IN varchar2, cursor OUT SYS_REFCURSOR)

IS

BEGIN

		OPEN CURSOR FOR
		SELECT item.nombre, subasta.precioinicial, puja.monto, puja.fechaHora
		FROM historialcomprador
		INNER JOIN subasta ON historialcomprador.subastaid = subasta.ID
		INNER JOIN item ON subasta.itemid = item.ID
		INNER JOIN puja ON subasta.montoActual = puja.monto
		INNER JOIN participante ON historialcomprador.compradorId = participante.ID
		INNER JOIN usuario ON participante.usuarioId = usuario.ID
		AND usuario.docidentidad = ident;

END;