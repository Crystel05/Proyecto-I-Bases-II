create or replace NONEDITIONABLE PROCEDURE mostSub(cursor OUT SYS_REFCURSOR)
IS

BEGIN


 OPEN CURSOR FOR
    SELECT nombre, fechafinal, montoactual
    FROM subasta
    INNER JOIN item on subasta.itemid = item.id
    WHERE activo = 1;

END;