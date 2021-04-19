create or replace NONEDITIONABLE PROCEDURE pujasXsubasta(nombreItem IN varchar2, cursor OUT SYS_REFCURSOR)

IS

BEGIN

	OPEN CURSOR FOR
	SELECT puja.monto, puja.fechaHora
	FROM puja
	INNER JOIN subasta ON puja.subastaId = subasta.ID
	INNER JOIN item ON subasta.itemid = Item.ID 
    WHERE item.nombre = nombreItem;

END;