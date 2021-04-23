
CREATE OR REPLACE FUNCTION public.pujar(
	aliasp character varying,
	pasw character varying,
	monto numeric,
	itemnom character varying)
    RETURNS integer
    LANGUAGE 'plpgsql'
	
AS $BODY$
DECLARE 
subastaId int;
montoMinimo numeric(19,4);
compId int;
userId int;
pujaMejor int;
BEGIN
	
	subastaId := (SELECT "ID" FROM subasta WHERE itemId = (SELECT "ID" FROM item WHERE nombre = itemNom));
	montoMinimo := (SELECT "minimoSistema" FROM subasta WHERE "ID" = subastaId);
	userId := (SELECT "ID" FROM usuario WHERE "Alias" = aliasP and contrasenna = pasw);
	compId := (SELECT "ID" FROM comprador WHERE "participanteId" = (SELECT "ID" FROM participante WHERE "usuarioId" = userId));
	
	IF (SELECT activa FROM subasta WHERE "ID" = subastaId)
	THEN
		
		IF montoMinimo + (SELECT "mejorMonto" FROM subasta WHERE "ID" = subastaId) <= monto 
		THEN
			
			INSERT INTO puja("oferta", "subastaId", "compradorId", "fechaHora")
			VALUES(monto, subastaId, compId, (SELECT now()::timestamp))
			RETURNING puja."ID" INTO pujaMejor;
			
			RETURN 1;
			
			
		ELSE
			RETURN 2;
		
		END IF;
			
	ELSE
		RETURN 3;
	END IF;

END;
$BODY$;

	
