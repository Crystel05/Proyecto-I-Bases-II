create or replace NONEDITIONABLE PROCEDURE mostCategorias(cursor OUT SYS_REFCURSOR)
IS

BEGIN

    OPEN CURSOR FOR SELECT nombre FROM categoria;

END;