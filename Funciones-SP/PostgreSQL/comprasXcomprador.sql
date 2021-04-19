CREATE OR REPLACE FUNCTION public.comprasXcomprador(ident varchar)
    RETURNS TABLE(itemNombre varchar, 
				  precioBase numeric, 
				  precioFinal numeric, 
				  fecha timestamp,
				  vendedorNom varchar, 
				  coment varchar,
				  punt int,
				  detallesItem varchar, 
				  detallesEntrega varchar, 
				  fotoI varchar)
    LANGUAGE 'plpgsql'

AS $BODY$
	
DECLARE 
	dato RECORD;		
	
BEGIN
	
		FOR dato IN 
			SELECT item.nombre as itemN, subasta.precioinicial as precioI, puja.oferta as precioF, 
			puja."fechaHora" as fechaC, comentarios.comentario as come, comentarios.puntuacion as puntu, 
			item.descripcion as descp, subasta.detallesentrega as envioI, item.foto as pic
			FROM historialcomprador
			INNER JOIN subasta ON historialcomprador.subastaid = subasta."ID"
			INNER JOIN comentarios ON comentarios.subastaid = subasta."ID" AND comentarios."esVendedor" = TRUE
			INNER JOIN item ON subasta.itemid = item."ID"
			INNER JOIN puja ON subasta."mejorMonto" = puja.oferta
			INNER JOIN participante ON historialcomprador."compradorId" = participante."ID"
			INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
			AND usuario.docident = ident LOOP
			
				itemNombre :=  dato.itemN;
			  	precioBase := dato.precioI;
			  	precioFinal := dato.precioF; 
				fecha := dato.fechaC; 
			    vendedorNom := (SELECT nombreapellidos FROM subasta
							   INNER JOIN vendedor ON subasta.vendedorid = vendedor."ID"
							   INNER JOIN participante ON vendedor."participanteId" = participante."ID"
							   INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
							   WHERE subasta.itemid = (SELECT "ID" FROM item WHERE nombre = dato.itemN)); 
				coment := dato.come;
				punt := dato.puntu;
				detallesItem := dato.descp;
				detallesEntrega := dato.envioI;
				fotoI := dato.pic;
			
			RETURN NEXT;
		END LOOP;
		
END;
$BODY$;

SELECT * FROM usuario

--SELECT * FROM comprasXcomprador('123457') 
--DROP FUNCTION comprasXcomprador(varchar)

