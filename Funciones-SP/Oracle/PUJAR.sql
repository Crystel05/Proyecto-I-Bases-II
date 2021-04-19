create or replace NONEDITIONABLE PROCEDURE pujar(aliasp IN VARCHAR2, pasw IN VARCHAR2, monto IN DECIMAL, itemNom IN VARCHAR2, retorno OUT INTEGER)

IS

    subastaId INTEGER;
    montoMinimo decimal(19,4);
    compId INTEGER;
    userId INTEGER;
    pujaMejor decimal;
    esActivo INTEGER;
    montAct decimal(19,4);

BEGIN

	SELECT ID INTO subastaId FROM subasta WHERE itemId = (SELECT ID FROM item WHERE nombre = itemNom);
	SELECT agregadominimo INTO montoMinimo FROM subasta WHERE ID = subastaId;
	SELECT ID INTO userId FROM usuario WHERE Alias = aliasP and contrasenna = pasw;
	SELECT ID INTO compId FROM comprador WHERE participanteId = (SELECT ID FROM participante WHERE usuarioId = userId);
    SELECT activo INTO esActivo FROM subasta WHERE ID = subastaId;

	IF esActivo = 1 THEN

        SELECT montoActual INTO montAct FROM subasta WHERE ID = subastaId;
		IF (montoMinimo + montAct) <= monto 
		THEN

			INSERT INTO puja(monto, subastaId, compradorId, fechaHora)
			VALUES(monto, subastaId, compId, SYSDATE);


        END IF;

	END IF;

END;